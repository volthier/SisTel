package br.gov.cultura.DitelAdm.model.enums;

public enum VinculoCadastroPessoa {
	
	Estagiário("Estagiário"),
	Terceirizado("Terceirizado"),
	Servidor("Servidor");
	
	private String descricao;
	
		VinculoCadastroPessoa(String descricao){
			this.descricao = descricao;
						
		}
		public String getDescricao(){
		 return descricao;
		}
}
