/**
 * SeiPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package br.gov.cultura.DitelAdm.wsdl;

public interface SeiPortType extends java.rmi.Remote {

    /**
     * Geracao de processos
     */
    public br.gov.cultura.DitelAdm.wsdl.RetornoGeracaoProcedimento gerarProcedimento(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, br.gov.cultura.DitelAdm.wsdl.Procedimento procedimento, br.gov.cultura.DitelAdm.wsdl.Documento[] documentos, java.lang.String[] procedimentosRelacionados, java.lang.String[] unidadesEnvio, java.lang.String sinManterAbertoUnidade, java.lang.String sinEnviarEmailNotificacao, java.lang.String dataRetornoProgramado, java.lang.String diasRetornoProgramado, java.lang.String sinDiasUteisRetornoProgramado, java.lang.String idMarcador, java.lang.String textoMarcador) throws java.rmi.RemoteException;

    /**
     * Geracao de documentos
     */
    public br.gov.cultura.DitelAdm.wsdl.RetornoInclusaoDocumento incluirDocumento(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, br.gov.cultura.DitelAdm.wsdl.Documento documento) throws java.rmi.RemoteException;

    /**
     * Lista de unidades disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.Unidade[] listarUnidades(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idTipoProcedimento, java.lang.String idSerie) throws java.rmi.RemoteException;

    /**
     * Lista de tipos de processo disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.TipoProcedimento[] listarTiposProcedimento(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idSerie) throws java.rmi.RemoteException;

    /**
     * Lista de series disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.Serie[] listarSeries(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idTipoProcedimento) throws java.rmi.RemoteException;

    /**
     * Lista de contatos
     */
    public br.gov.cultura.DitelAdm.wsdl.Contato[] listarContatos(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idTipoContato, java.lang.String paginaRegistros, java.lang.String paginaAtual, java.lang.String sigla, java.lang.String nome, java.lang.String cpf, java.lang.String cnpj, java.lang.String matricula, java.lang.String[] idContatos) throws java.rmi.RemoteException;

    /**
     * Atualizacao de contatos
     */
    public java.lang.String atualizarContatos(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, br.gov.cultura.DitelAdm.wsdl.Contato[] contatos) throws java.rmi.RemoteException;

    /**
     * Consulta de processos
     */
    public br.gov.cultura.DitelAdm.wsdl.RetornoConsultaProcedimento consultarProcedimento(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String sinRetornarAssuntos, java.lang.String sinRetornarInteressados, java.lang.String sinRetornarObservacoes, java.lang.String sinRetornarAndamentoGeracao, java.lang.String sinRetornarAndamentoConclusao, java.lang.String sinRetornarUltimoAndamento, java.lang.String sinRetornarUnidadesProcedimentoAberto, java.lang.String sinRetornarProcedimentosRelacionados, java.lang.String sinRetornarProcedimentosAnexados) throws java.rmi.RemoteException;

    /**
     * Consulta de processos individuais por usuario interessado
     */
    public br.gov.cultura.DitelAdm.wsdl.ProcedimentoResumido consultarProcedimentoIndividual(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idOrgaoProcedimento, java.lang.String idTipoProcedimento, java.lang.String idOrgaoUsuario, java.lang.String siglaUsuario) throws java.rmi.RemoteException;

    /**
     * Consulta de documentos
     */
    public br.gov.cultura.DitelAdm.wsdl.RetornoConsultaDocumento consultarDocumento(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloDocumento, java.lang.String sinRetornarAndamentoGeracao, java.lang.String sinRetornarAssinaturas, java.lang.String sinRetornarPublicacao, java.lang.String sinRetornarCampos) throws java.rmi.RemoteException;

    /**
     * Cancelamento de documentos
     */
    public java.lang.String cancelarDocumento(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloDocumento, java.lang.String motivo) throws java.rmi.RemoteException;

    /**
     * Geracao de bloco
     */
    public java.lang.String gerarBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String tipo, java.lang.String descricao, java.lang.String[] unidadesDisponibilizacao, java.lang.String[] documentos, java.lang.String sinDisponibilizar) throws java.rmi.RemoteException;

    /**
     * Consulta de bloco
     */
    public br.gov.cultura.DitelAdm.wsdl.RetornoConsultaBloco consultarBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco, java.lang.String sinRetornarProtocolos) throws java.rmi.RemoteException;

    /**
     * Exclusao de bloco
     */
    public java.lang.String excluirBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco) throws java.rmi.RemoteException;

    /**
     * Disponibilizacao de bloco
     */
    public java.lang.String disponibilizarBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco) throws java.rmi.RemoteException;

    /**
     * Cancelamento de disponibilizacao de bloco
     */
    public java.lang.String cancelarDisponibilizacaoBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco) throws java.rmi.RemoteException;

    /**
     * Inclusao de documento em bloco
     */
    public java.lang.String incluirDocumentoBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco, java.lang.String protocoloDocumento, java.lang.String anotacao) throws java.rmi.RemoteException;

    /**
     * Remocao de documento de bloco
     */
    public java.lang.String retirarDocumentoBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco, java.lang.String protocoloDocumento) throws java.rmi.RemoteException;

    /**
     * Inclusao de processo em bloco
     */
    public java.lang.String incluirProcessoBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco, java.lang.String protocoloProcedimento, java.lang.String anotacao) throws java.rmi.RemoteException;

    /**
     * Remocao de processo de bloco
     */
    public java.lang.String retirarProcessoBloco(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idBloco, java.lang.String protocoloProcedimento) throws java.rmi.RemoteException;

    /**
     * Reabertura de processo
     */
    public java.lang.String reabrirProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento) throws java.rmi.RemoteException;

    /**
     * Conclusao de processo
     */
    public java.lang.String concluirProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento) throws java.rmi.RemoteException;

    /**
     * Lista de extensoes de arquivos permitidas
     */
    public br.gov.cultura.DitelAdm.wsdl.ArquivoExtensao[] listarExtensoesPermitidas(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idArquivoExtensao) throws java.rmi.RemoteException;

    /**
     * Movimentacao de processo entre unidades
     */
    public java.lang.String enviarProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String[] unidadesDestino, java.lang.String sinManterAbertoUnidade, java.lang.String sinRemoverAnotacao, java.lang.String sinEnviarEmailNotificacao, java.lang.String dataRetornoProgramado, java.lang.String diasRetornoProgramado, java.lang.String sinDiasUteisRetornoProgramado, java.lang.String sinReabrir) throws java.rmi.RemoteException;

    /**
     * Lista de usuarios com permissao na unidade
     */
    public br.gov.cultura.DitelAdm.wsdl.Usuario[] listarUsuarios(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idUsuario) throws java.rmi.RemoteException;

    /**
     * Atribuicao de processo para usuario na unidade
     */
    public java.lang.String atribuirProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String idUsuario, java.lang.String sinReabrir) throws java.rmi.RemoteException;

    /**
     * Lista de hipoteses legais disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.HipoteseLegal[] listarHipotesesLegais(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String nivelAcesso) throws java.rmi.RemoteException;

    /**
     * Lista de tipos de conferencia disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.TipoConferencia[] listarTiposConferencia(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade) throws java.rmi.RemoteException;

    /**
     * Lista de paises disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.Pais[] listarPaises(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade) throws java.rmi.RemoteException;

    /**
     * Lista de estados disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.Estado[] listarEstados(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idPais) throws java.rmi.RemoteException;

    /**
     * Lista de cidades disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.Cidade[] listarCidades(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idPais, java.lang.String idEstado) throws java.rmi.RemoteException;

    /**
     * Lista de cargos disponiveis
     */
    public br.gov.cultura.DitelAdm.wsdl.Cargo[] listarCargos(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idCargo) throws java.rmi.RemoteException;

    /**
     * Adiciona um arquivo no repositorio
     */
    public java.lang.String adicionarArquivo(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String nome, java.lang.String tamanho, java.lang.String hash, java.lang.String conteudo) throws java.rmi.RemoteException;

    /**
     * Adiciona conteudo em um arquivo do repositorio
     */
    public java.lang.String adicionarConteudoArquivo(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String idArquivo, java.lang.String conteudo) throws java.rmi.RemoteException;

    /**
     * Lanca andamento em processo
     */
    public br.gov.cultura.DitelAdm.wsdl.Andamento lancarAndamento(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String idTarefa, java.lang.String idTarefaModulo, br.gov.cultura.DitelAdm.wsdl.AtributoAndamento[] atributos) throws java.rmi.RemoteException;

    /**
     * Lista andamentos do processo
     */
    public br.gov.cultura.DitelAdm.wsdl.Andamento[] listarAndamentos(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String sinRetornarAtributos, java.lang.String[] andamentos, java.lang.String[] tarefas, java.lang.String[] tarefasModulos) throws java.rmi.RemoteException;

    /**
     * Bloqueia processo
     */
    public java.lang.String bloquearProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento) throws java.rmi.RemoteException;

    /**
     * Desbloqueia processo
     */
    public java.lang.String desbloquearProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento) throws java.rmi.RemoteException;

    /**
     * Relaciona processos
     */
    public java.lang.String relacionarProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento1, java.lang.String protocoloProcedimento2) throws java.rmi.RemoteException;

    /**
     * Remove relacionamento entre processos
     */
    public java.lang.String removerRelacionamentoProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento1, java.lang.String protocoloProcedimento2) throws java.rmi.RemoteException;

    /**
     * Sobrestar processo
     */
    public java.lang.String sobrestarProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String protocoloProcedimentoVinculado, java.lang.String motivo) throws java.rmi.RemoteException;

    /**
     * Remover sobrestamento processo
     */
    public java.lang.String removerSobrestamentoProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento) throws java.rmi.RemoteException;

    /**
     * Anexar processo
     */
    public java.lang.String anexarProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimentoPrincipal, java.lang.String protocoloProcedimentoAnexado) throws java.rmi.RemoteException;

    /**
     * Desanexar processo
     */
    public java.lang.String desanexarProcesso(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimentoPrincipal, java.lang.String protocoloProcedimentoAnexado, java.lang.String motivo) throws java.rmi.RemoteException;

    /**
     * Lista marcadores da unidade
     */
    public br.gov.cultura.DitelAdm.wsdl.Marcador[] listarMarcadoresUnidade(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade) throws java.rmi.RemoteException;

    /**
     * Define marcador em processos
     */
    public java.lang.String definirMarcador(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, br.gov.cultura.DitelAdm.wsdl.DefinicaoMarcador[] definicoes) throws java.rmi.RemoteException;

    /**
     * Lista andamentos de marcadores do processo
     */
    public br.gov.cultura.DitelAdm.wsdl.AndamentoMarcador[] listarAndamentosMarcadores(java.lang.String siglaSistema, java.lang.String identificacaoServico, java.lang.String idUnidade, java.lang.String protocoloProcedimento, java.lang.String[] marcadores) throws java.rmi.RemoteException;
}
