package br.gov.cultura.DitelAdm.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.repository.LimitesAtestos;
import br.gov.cultura.DitelAdm.repository.filtro.CadastroFiltroPesquisa;

@Service
public class LimiteAtestoService {

		@Autowired
		private LimitesAtestos limitesAtestos;
		
		public void salvar(LimiteAtesto limiteAtesto){
			limitesAtestos.save(limiteAtesto);
		}
		
		
		public List<LimiteAtesto> filtrar(CadastroFiltroPesquisa filtro){
			String dasAtesto = filtro.getDasAtesto() == null ? "%" : filtro.getDasAtesto();
			return limitesAtestos.findByDasAtestoContaining(dasAtesto);
		}
}
