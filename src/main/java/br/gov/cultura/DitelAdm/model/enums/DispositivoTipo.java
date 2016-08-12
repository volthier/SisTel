package br.gov.cultura.DitelAdm.model.enums;

public enum DispositivoTipo {
	
	FIXO("Fixo"),
	CELULAR("Celular"),
	TABLET("Tablet"),
	MODEM("Modem");

	private String descricao;
	
		DispositivoTipo(String descricao){
			this.descricao = descricao;
						
		}
		public String getDescricao(){
		 return descricao;
		}
}
