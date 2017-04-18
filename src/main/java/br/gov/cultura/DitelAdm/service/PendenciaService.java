package br.gov.cultura.DitelAdm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.dtos.AlocacaoLinhaDispositivoUsuarioDTO;

@Service
public class PendenciaService {
	
	@Autowired
	private AlocacaoService alocacaoService;
	
	@Autowired
	private CadastroDispositivoService cadastroDispositivoService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService; 
	
	@Autowired
	private CadastroLinhaService cadastroLinhaService;
	
	@Autowired
	private CadastroCategoriaService cadastroCategoriaService; 
	
	public List<AlocacaoLinhaDispositivoUsuarioDTO> listaPendencia(){
		List<Object[]> linhaDispo =  alocacaoService.getAlocacaoUsuarioLinhaList();
		List<AlocacaoLinhaDispositivoUsuarioDTO> lista = new ArrayList<AlocacaoLinhaDispositivoUsuarioDTO>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for (Object[] item : linhaDispo) {
	    	Date dtDevolucao = null;
	    	Date dtRecebido = null;
	    	
			if(item[2] != null){
				try {
					dtDevolucao = df.parse(item[2].toString().substring(0, 19));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if(item[3] != null){
				try {
					dtRecebido = df.parse(item[3].toString().substring(0, 19));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(item[4]==null && item[5]==null){
					item[4] = "null";
					item[5] = "null";	
				}
			}
	    	
	    	AlocacaoLinhaDispositivoUsuarioDTO alocacao = 
	    			new AlocacaoLinhaDispositivoUsuarioDTO(
	    					Integer.parseInt(item[0].toString()),
	    					Integer.parseInt(item[1].toString()),
	    					dtDevolucao,
	    					dtRecebido,
	    					item[4].toString(),
	    					item[5].toString(),
	    					Integer.parseInt(item[7].toString()),
	    					Integer.parseInt(item[8].toString()),
	    					Integer.parseInt(item[9].toString()));
	    	lista.add(alocacao);
	    }
		
		for (AlocacaoLinhaDispositivoUsuarioDTO dto : lista) {
			dto.setDispositivo(cadastroDispositivoService.getDispositivoById(dto.getIdDispositivo()));
			dto.setUsuario(cadastroUsuarioService.getUsuarioById(dto.getIdUsuario()));
			dto.setLinha(cadastroLinhaService.getLinhaById(dto.getIdLinha()));
			dto.setCategoria(cadastroCategoriaService.getCadastroById(dto.getIdCategoria()));
		}
		
		return lista;
	}	
}