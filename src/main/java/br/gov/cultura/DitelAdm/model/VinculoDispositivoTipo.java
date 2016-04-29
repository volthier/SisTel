package br.gov.cultura.DitelAdm.model;

public enum VinculoDispositivoTipo {
	
	Fixo("Fixo"),
	Celular("Celular"),
	Tablet("Tablet"),
	Modem("Modem");

	private String descricao;
	
		VinculoDispositivoTipo(String descricao){
			this.descricao = descricao;
						
		}
		public String getDescricao(){
		 return descricao;
		}
}
