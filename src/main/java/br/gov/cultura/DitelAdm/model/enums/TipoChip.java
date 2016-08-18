package br.gov.cultura.DitelAdm.model.enums;

public enum TipoChip {
	
	SimCard("Sim Card"),
	MicroSim("Micro Sim"),
	NanoSim("Nano Sim");

	private String descricao;
	
		private TipoChip(String descricao) {
			this.descricao = descricao;
		}
		@Override
		public String toString(){
		 return descricao;
		}
}
