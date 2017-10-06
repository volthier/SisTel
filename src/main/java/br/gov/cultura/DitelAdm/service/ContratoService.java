package br.gov.cultura.DitelAdm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.gov.cultura.DitelAdm.model.Contrato;
import br.gov.cultura.DitelAdm.repository.Contratos;

@Service
@Transactional
public class ContratoService {

		@Autowired
		private Contratos contratos;
		
		public void salvar(Contrato contrato){
			contratos.save(contrato);
		}
		
		public List<Contrato> getContratos() {
			return contratos.findAll();
		}
		public Contrato getContratoId(Integer id){
			return contratos.findOne(id);
		}
		public void excluir(Integer idContrato){
			contratos.delete(idContrato);
		}
}

