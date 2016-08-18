package br.gov.cultura.DitelAdm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.gov.cultura.DitelAdm.Service.CadastroChipService;
import br.gov.cultura.DitelAdm.Service.CadastroDispositivoService;
import br.gov.cultura.DitelAdm.Service.CadastroLinhaService;
import br.gov.cultura.DitelAdm.Service.CadastroPessoaService;
import br.gov.cultura.DitelAdm.model.CadastroDispositivo;
import br.gov.cultura.DitelAdm.model.CadastroLinha;
import br.gov.cultura.DitelAdm.model.CadastroPessoa;
import br.gov.cultura.DitelAdm.model.Chip;
import br.gov.cultura.DitelAdm.model.enums.StatusCadastro;
import br.gov.cultura.DitelAdm.model.enums.TipoChip;
import br.gov.cultura.DitelAdm.model.enums.VinculoCadastroPessoa;
import br.gov.cultura.DitelAdm.model.enums.VinculoCadastroPessoaCargo;
import br.gov.cultura.DitelAdm.model.enums.VinculoCadastroPessoaDAS;
import br.gov.cultura.DitelAdm.model.enums.VinculoDispositivoTipo;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Controller
@RequestMapping("/consulta")
public class ConsultaController {

	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;

	@Autowired
	private CadastroPessoaService cadastroPessoaService;

	@Autowired
	private CadastroChipService cadastroChipService;

	@Autowired
	private CadastroLinhaService cadastroLinhaService;

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") CadastroFiltroPesquisa filtro) {
		List<CadastroDispositivo> todosDispositivos = cadastroDispositivoService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("PesquisaDispositivos");
		mv.addObject("dispositivos", todosDispositivos);
		List<CadastroPessoa> todasPessoas = cadastroPessoaService.filtrar(filtro);
		mv.addObject("pessoas", todasPessoas);
		List<Chip> todosChips = cadastroChipService.filtrar(filtro);
		mv.addObject("chips", todosChips);
		List<CadastroLinha> todasLinhas = cadastroLinhaService.filtrar(filtro);
		mv.addObject("linhas", todasLinhas);
		return mv;
	}

	@ModelAttribute("todosStatusCadastro")
	public List<StatusCadastro> todosStatusCadastro() {
		return Arrays.asList(StatusCadastro.values());
	}

	@ModelAttribute("todosVinculoCadastroPessoa")
	public List<VinculoCadastroPessoa> todosVinculoCadastroPessoa() {
		return Arrays.asList(VinculoCadastroPessoa.values());
	}

	@ModelAttribute("todosVinculoCadastroPessoaDAS")
	public List<VinculoCadastroPessoaDAS> todosVinculoCadastroPessoaDAS() {
		return Arrays.asList(VinculoCadastroPessoaDAS.values());
	}

	@ModelAttribute("todosVinculoCadastroPessoaCargo")
	public List<VinculoCadastroPessoaCargo> todosVinculoCadastroPessoaCargo() {
		return Arrays.asList(VinculoCadastroPessoaCargo.values());
	}

	@ModelAttribute("todosCadastroChipTipo")
	public List<TipoChip> todosCadastroChipTipo() {
		return Arrays.asList(TipoChip.values());
	}

	@ModelAttribute("todosDispositivoTipo")
	public List<VinculoDispositivoTipo> todosDispositivoTipo() {
		return Arrays.asList(VinculoDispositivoTipo.values());
	}

}
