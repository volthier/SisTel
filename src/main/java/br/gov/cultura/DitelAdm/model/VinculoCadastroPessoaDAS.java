package br.gov.cultura.DitelAdm.model;

public enum VinculoCadastroPessoaDAS {
	
	Sem_DAS("Não"),
	Um("1"),
	Dois("2"),
	Três("3"),
	Quatro("4"),
	Cinco("5"),
	Seis("6"),
	NE("Natureza Especial");
	
	private String descricao;
	
		VinculoCadastroPessoaDAS(String descricao){
			this.descricao = descricao;
						
		}
		public String getDescricao(){
		 return descricao;
		}
}
