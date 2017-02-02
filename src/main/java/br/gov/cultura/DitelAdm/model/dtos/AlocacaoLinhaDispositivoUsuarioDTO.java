package br.gov.cultura.DitelAdm.model.dtos;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.gov.cultura.DitelAdm.model.Dispositivo;
import br.gov.cultura.DitelAdm.model.Usuario;

public class AlocacaoLinhaDispositivoUsuarioDTO {

	private Integer idDispositivo;
	private Integer idAlocacaoUsuarioLinha;
	private Date dtDevolucao;
	private Date dtRecebido;
	private Integer idLinha;
	private Integer idUsuario;
	private Dispositivo dispositivo;
	private Usuario usuario;
	private SimpleDateFormat df;
	
	public AlocacaoLinhaDispositivoUsuarioDTO() {
	}
	
	public AlocacaoLinhaDispositivoUsuarioDTO(Object idDispositivo, Object idAlocacaoUsuarioLinha, 
			Object dtDevolucao, Object dtRecebido, Object idLinha, Object idUsuario){
		
		df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.idDispositivo = Integer.parseInt(idDispositivo.toString());
		this.idAlocacaoUsuarioLinha = Integer.parseInt(idAlocacaoUsuarioLinha.toString());
		if(dtDevolucao != null){
			this.dtDevolucao = new Date(Date.parse(dtDevolucao.toString()));
		}
		if(dtRecebido != null){
			//System.out.println(dtRecebido.toString());
			//System.out.println(dtRecebido.toString().substring(0, 19));
			
			try {
				this.dtRecebido = df.parse(dtRecebido.toString().substring(0, 19));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.idLinha = Integer.parseInt(idLinha.toString());
		this.idUsuario = Integer.parseInt(idUsuario.toString());
	}
	
}
