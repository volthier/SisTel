package br.gov.cultura.DitelAdm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cultura.DitelAdm.model.Alocacao;
import br.gov.cultura.DitelAdm.model.AlocacaoFatura;
import br.gov.cultura.DitelAdm.model.AlocacaoSei;
import br.gov.cultura.DitelAdm.model.DocumentoSei;
import br.gov.cultura.DitelAdm.model.Linha;
import br.gov.cultura.DitelAdm.model.Usuario;
import br.gov.cultura.DitelAdm.repository.Usuarios;
import br.gov.cultura.DitelAdm.repository.alocacoes.Alocacoes;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesFaturas;
import br.gov.cultura.DitelAdm.repository.alocacoes.AlocacoesSei;
import br.gov.cultura.DitelAdm.repository.alocacoes.DocumentosSei;
import br.gov.cultura.DitelAdm.repository.filtro.FiltroPesquisa;

@Service
@Transactional
public class AlocacaoService {
	
	@Autowired
	private Alocacoes alocacoes;

	@Autowired
	private AlocacoesFaturas alocacoesFaturas;
	
	@Autowired
	private DocumentosSei documentosSei;
	
	@Autowired
	private AlocacoesSei alocacaoSeis;
	
	@Autowired
	private Usuarios usuarios;
	
	public void salvar(List<AlocacaoSei> alocacoes){
		alocacaoSeis.save(alocacoes);
	}
	
	public void salvar(AlocacaoSei alocacao){
		alocacaoSeis.saveAndFlush(alocacao);
	}

	public void salvar(Alocacao alocacao){
		alocacoes.saveAndFlush(alocacao);
	}
	
	public void salvar(AlocacaoFatura alocacaoFatura){
		alocacoesFaturas.saveAndFlush(alocacaoFatura);
	}

	public void salvar(DocumentoSei documentoSei){
		documentosSei.saveAndFlush(documentoSei);
	}
	
	public void excluir(Integer id) {
		alocacoes.delete(id);
	}

	public List<AlocacaoSei> getAlocacaoSei(){
		return alocacaoSeis.findAll();
	}
	
	public List<Alocacao> getIdAlocacao(){
		return alocacoes.findAll();
	}
	
	public List<AlocacaoFatura> getIdAlocacaoFatura(){
		return alocacoesFaturas.findAll();
	}
	
	public List<DocumentoSei> getIdDocumentoSei(){
		return documentosSei.findAll(); 
	}
	
	public AlocacaoSei getAlocacaoSei(Integer idAlocacaoSei){
		return alocacaoSeis.findOne(idAlocacaoSei);
	}
	
	public Alocacao getAlocacao(Integer idAlocacao){
		return alocacoes.findOne(idAlocacao);
	}

	public AlocacaoFatura getAlocacaoFatura(Integer idAlocacaoFatura){
		return alocacoesFaturas.findOne(idAlocacaoFatura);
	}
	
	public DocumentoSei getDocumentoSei(Integer idDocumento){
		return documentosSei.findOne(idDocumento);
	}

	public List<DocumentoSei> getDocumentoSeiListaAlocacao(Alocacao alocacao){
		return documentosSei.findByAlocacao(alocacao);
	}
	public List<Alocacao> getAlocacaoUsuarioLinha(Linha linha) {
		return alocacoes.getAlocacaoByLinha(linha);
	}
	
	public List<Alocacao> getDevolucao(){
		return alocacoes.getAlocacaoDevolucao();
	}

	public List<Alocacao> getAlocacoesUsuario(Usuario usuario){
		return alocacoes.getAlocacoesUsuario(usuario);
	}
	
	public List<Alocacao> filtroPesquisa(FiltroPesquisa filtro){
		String filtroPesquisa =  filtro.getNumeroSerie() == null ? "%" : filtro.getNumeroSerie();
		List<Usuario> usuario = usuarios.findByNomeUsuarioContaining(filtroPesquisa);
		List<Alocacao> aloc = new ArrayList<Alocacao>();
		for(Usuario u : usuario){
			aloc.addAll(alocacoes.getAlocacoesUsuario(u));
		}	 
		return aloc;
	}
	
}