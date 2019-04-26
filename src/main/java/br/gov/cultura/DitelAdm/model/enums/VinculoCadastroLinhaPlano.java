package br.gov.cultura.DitelAdm.model.enums;

public enum VinculoCadastroLinhaPlano {

	Voz("Voz"),
	Voz3G("Voz e Dados"),
	Dados("Somente Dados");

	private String descricao;

		VinculoCadastroLinhaPlano(String descricao){
			this.descricao = descricao;

		}
		public String getDescricao(){
		 return descricao;
		}
}
