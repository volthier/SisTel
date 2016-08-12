package br.gov.cultura.DitelAdm.model.enums;

public enum VinculoCadastroChipTipo {
	
	SimCard("Sim Card"),
	MicroSim("Micro Sim"),
	NanoSim("Nano Sim");

	private String descricao;
	
		VinculoCadastroChipTipo(String descricao){
			this.descricao = descricao;
						
		}
		public String getDescricao(){
		 return descricao;
		}
}
