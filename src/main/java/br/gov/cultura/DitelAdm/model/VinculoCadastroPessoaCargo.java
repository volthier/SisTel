package br.gov.cultura.DitelAdm.model;

public enum VinculoCadastroPessoaCargo {

	Desconsiderado("-------"), 
	Assessor("Assessor(a)"), 
	Assessor_Do_Ministro("Assessor(a) do Ministro(a)"), 
	Assessor_Especial("Assessor(a) Especial"), 
	Assessor_Técnico("Assessor(a) Técnico(a)"), 
	Assistente("Assistente"), 
	Chefe_De_Assessoria("Chefe de Assessoria"), 
	Chefe_De_Divisão("Chefe de Divisão"), 
	Chefe_De_Gabinete("Chefe de Gabinete"), 
	Chefe_De_Representação("Chefe de Representação"), 
	Chefe_De_Representação_Substituto("Chefe de Representação Substituto"), 
	Chefe_Do_Gabinete("Chefe do Gabinete"), 
	Consultor_Juridica("Consultor(a) Juridica"), 
	Coordenador("Coordenador(a)"), 
	Coordenador_Geral("Coordenador(a) Geral"), 
	Coordenador_Interino("Coordenadora(a) Interiano(a)"),
	Coordenador_Geral_De_Cerimonial("Coordenador-Geral de Cerimonial"), 
	Diretor("Diretor(a)"), 
	Gerente("Gerente"), 
	Gerente_De_Projetos("Gerente de Projetos"), 
	Ministro("Ministro"), 
	Motorista("Motorista"), 
	Motorista_GM("Motorista GM"), 
	Ouvidoria("Ouvidora"), 
	Secretário("Secretário(a)"), 
	Secretário_Executivo("Secretário(a) Executivo"), 
	Subsecretário("Subsecretário(a)"), 
	Técnico("Técnico(a)");
	
	private String descricao;

	VinculoCadastroPessoaCargo(String descricao) {
		this.descricao = descricao;

	}

	public String getDescricao() {
		return descricao;
	}
}
