package br.gov.cultura.DitelAdm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.service.CadastroUsuarioService;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@RequestMapping(value="/usuarios")
	public ResponseEntity<Usuario> get(){
		Usuario user = cadastroUsuarioService.getId();
		return new ResponseEntity<Usuario>(user,HttpStatus.OK); 
	}


/*	@RequestMapping(value="/usuarios", method = RequestMethod.POST)
	public ResponseEntity<List<Usuario>> update(@RequestBody List<Usuario> users) {
		return new ResponseEntity<List<Usuario>>(users, HttpStatus.OK);
	}*/

}
