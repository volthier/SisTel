package br.gov.cultura.DitelAdm.model;

public enum StatusCadastro {
	
	RECEBIDO("Recebido"),
	PENDENTE("Pendente");

	private String descricao;
	
		StatusCadastro(String descricao){
			this.descricao = descricao;
						
		}
		public String getDescricao(){
		 return descricao;
		}
}
