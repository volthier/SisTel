package br.gov.cultura.DitelAdm.model.dtos;

public class LotacaoSeiDTO {
	 private String idUnidade;

	    private String sigla;

	    private String descricao;

		public LotacaoSeiDTO() {		
		}

		public LotacaoSeiDTO(String idUnidade, String sigla, String descricao) {
			super();
			this.idUnidade = idUnidade;
			this.sigla = sigla;
			this.descricao = descricao;
		}

		public String getIdUnidade() {
			return idUnidade;
		}

		public void setIdUnidade(String idUnidade) {
			this.idUnidade = idUnidade;
		}

		public String getSigla() {
			return sigla;
		}

		public void setSigla(String sigla) {
			this.sigla = sigla;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	    
	    

}
