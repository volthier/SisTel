package br.gov.cultura.DitelAdm.model;

public enum VinculoCadastroPessoa {
	
	ESTAGIARIO("Estagiário"),
	TERCEIRIZADO("Terceirizado"),
	SERVIDOR("Servidor");
	
	private String descricao;
	
		VinculoCadastroPessoa(String descricao){
			this.descricao = descricao;
						
		}
		public String getDescricao(){
		 return descricao;
		}
}
