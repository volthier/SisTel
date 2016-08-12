package br.gov.cultura.DitelAdm.model.enums;

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
