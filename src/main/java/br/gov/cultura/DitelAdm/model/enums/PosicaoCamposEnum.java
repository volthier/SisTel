package br.gov.cultura.DitelAdm.model.enums;

public enum PosicaoCamposEnum {

	CAMPO_HEADER_FATURA_INDCONTA(14, 39), 
	CAMPO_HEADER_FATURA_DATAEMISSAO(39, 47);
	


	private Integer posicaoInicial;
	private Integer posicaoFinal;
	
	
	private PosicaoCamposEnum(Integer posicaoInicial, Integer posicaoFinal) {
		this.posicaoInicial = posicaoInicial;
		this.posicaoFinal = posicaoFinal;
	}
	
	
	public Integer getPosicaoInicial() {
		return posicaoInicial;
	}


	public Integer getPosicaoFinal() {
		return posicaoFinal;
	}
	
	
}
