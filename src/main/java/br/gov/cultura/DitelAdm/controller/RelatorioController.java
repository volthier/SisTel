package br.gov.cultura.DitelAdm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.service.AlocacaoService;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	@Autowired
	private AlocacaoService alocacaoService;
	
	@RequestMapping(value="/usuarios",headers = "Accept=application/json")
	public List<Usuario> getAllUsuarios(){
		return cadastroUsuarioService.getId();
	}

/*	@RequestMapping(value="/usuarios", method = RequestMethod.POST)
	public ResponseEntity<List<Usuario>> update(@RequestBody List<Usuario> users) {
		return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	}*/

}
