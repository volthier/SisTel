package br.gov.cultura.DitelAdm.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.gov.cultura.DitelAdm.Service.AlocacaoService;
import br.gov.cultura.DitelAdm.Service.CadastroChipService;
import br.gov.cultura.DitelAdm.Service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.Service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.Service.CadastroPessoaService;
import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.CadastroChip;
import br.gov.cultura.DitelAdm.model.CadastroDispositivo;
import br.gov.cultura.DitelAdm.model.CadastroLinha;
import br.gov.cultura.DitelAdm.model.CadastroPessoa;
import br.gov.cultura.DitelAdm.model.VinculoCadastroChipTipo;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Controller
@RequestMapping("/alocacoes")
public class AlocacaoController extends UrlController {

	private final String CADASTRO_VIEW = "AlocacaoDisponibilizar";

	@Autowired
	private AlocacaoService alocacaoService;
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	@Autowired
	private CadastroChipService cadastroChipService;
	@Autowired
	private CadastroLinhaService cadastroLinhaService;

	@RequestMapping("/disponibilizar")
	public @ResponseBody ModelAndView novo(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Alocacao());
		List<CadastroDispositivo> todosDispositivos = cadastroDispositivoService.filtrar(filtro);
		mv.addObject("dispositivos", todosDispositivos);
		List<CadastroPessoa> todasPessoas = cadastroPessoaService.filtrar(filtro);
		mv.addObject("pessoas", todasPessoas);
		List<CadastroChip> todosChips = cadastroChipService.filtrar(filtro);
		mv.addObject("chips", todosChips);
		List<CadastroLinha> todasLinhas = cadastroLinhaService.filtrar(filtro);
		mv.addObject("linhas", todasLinhas);
		return mv;
	}
	 @RequestMapping(method = RequestMethod.POST) 
	 public String salvar(@Validated Alocacao alocacao, Errors errors, RedirectAttributes attributes){
		 if(errors.hasErrors()){ 
			 return CADASTRO_VIEW; 
	 }
	 try{
		 
	 AlocacaoService.salvar(alocacao);
	 attributes.addFlashAttribute("mensagem","Registrado vinculo!"); 
	 return "redirect:/alocacao/disponibilizar"; 
   	  }
	 catch(IllegalArgumentException e) { 
		 
		 errors.rejectValue("dataVencimento", null, e.getMessage()); 
		 return	CADASTRO_VIEW;
		 }
	 }

	 @RequestMapping(value="{id}", method = RequestMethod.DELETE) 
	 public String excluir(@PathVariable Long id, RedirectAttributes attributes){
	 alocacaoService.excluir(id); 
	 attributes.addFlashAttribute("mensagem","Fornecimento CANCELADO com sucesso!"); 
	 return "redirect:/inicio"; 
	 }
	 
	 @ModelAttribute("todosCadastroChipTipo")
		public List<VinculoCadastroChipTipo> todosCadastroChipTipo(){
			return Arrays.asList(VinculoCadastroChipTipo.values());
		}
}