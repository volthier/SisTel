package br.gov.cultura.DitelAdm.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.cultura.DitelAdm.model.dtos.FaturaArquivoDTO;
import br.gov.cultura.DitelAdm.model.ldap.UsuarioLdap;
import br.gov.cultura.DitelAdm.service.FaturaService;
import br.gov.cultura.DitelAdm.service.LeitorFebrabanV3;
import br.gov.cultura.DitelAdm.service.ldap.ConsultaLdapService;

@Controller
public class FaturaUploadController {

	public static final String ROOT = "upload-dir";

	private final ResourceLoader resourceLoader;

	@Autowired
	private FaturaService faturaService;

	@Autowired
	private LeitorFebrabanV3 leitorFebrabanV3;
	
	@Autowired
	private ConsultaLdapService ldap;

	@Autowired
	public FaturaUploadController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/faturas/nova")
	public String provideUploadInfo(Model model) throws IOException {

		UsuarioLdap user = ldap.userInfoUploadFatura();
		model.addAttribute("usuarioInfos",user);
		model.addAttribute("files",
				Files.walk(Paths.get(ROOT)).filter(path -> !path.equals(Paths.get(ROOT)))
						.map(path -> Paths.get(ROOT).relativize(path))
						.map(path -> linkTo(methodOn(FaturaUploadController.class).getFile(path.toString()))
								.withRel(path.toString()))
						.collect(Collectors.toList()));

		return "CadastroFaturas";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/faturas/nova/{filename:.+}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String filename) {

		try {
			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(ROOT, filename).toString()));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/faturas/nova")
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
			throws IOException {

		if (!file.isEmpty()) {
			try {
				Files.copy(file.getInputStream(), Paths.get(ROOT, file.getOriginalFilename()));
				redirectAttributes.addFlashAttribute("messageSucesso",
						"Fatura carregada com sucesso: " + file.getOriginalFilename() + "!");

				File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator")
						+ file.getOriginalFilename());

				file.transferTo(tmpFile);

				FaturaArquivoDTO faturaArquivoDTO = leitorFebrabanV3.read(tmpFile);
				faturaService.salvarOperadora(faturaArquivoDTO);
				faturaService.salvarCliente(faturaArquivoDTO);
				faturaService.salvarFatura(faturaArquivoDTO);
				faturaService.salvarResumo(faturaArquivoDTO);
				faturaService.salvarEnderecos(faturaArquivoDTO);
				faturaService.salvarCategoriasChamadas(faturaArquivoDTO);
				faturaService.salvarChamadas(faturaArquivoDTO);
				faturaService.salvarCategoriaServicos(faturaArquivoDTO);
				faturaService.salvarServicos(faturaArquivoDTO);
				faturaService.salvarCategoriasDescontos(faturaArquivoDTO);
				faturaService.salvarDescontos(faturaArquivoDTO);

				if (faturaArquivoDTO.getCategoriaPlano() != null)
					faturaService.salvarCategoriasPlanos(faturaArquivoDTO);

				if (faturaArquivoDTO.getPlanos() != null)
					faturaService.SalvarPlanos(faturaArquivoDTO);

				faturaService.salvarCategoriasAjustes(faturaArquivoDTO);
				faturaService.salvarAjustes(faturaArquivoDTO);
				faturaService.salvarNotaFiscal(faturaArquivoDTO);
				faturaService.salvarTrailler(faturaArquivoDTO);

			} catch (IOException | RuntimeException e) {
				redirectAttributes.addFlashAttribute("messageErro",
						"Falha ao carregar fatura! Erro:" + file.getOriginalFilename() + " => " + e);
				throw e;
			}
		} else {
			redirectAttributes.addFlashAttribute("messageErro",
					"Falha ao carregar fatura! Erro: " + file.getOriginalFilename() + " because it was empty");
		}
		return "redirect:/faturas/nova";
	}
}