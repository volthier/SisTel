package br.gov.cultura.DitelAdm.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.cultura.DitelAdm.modelo.Ajustes;
import br.gov.cultura.DitelAdm.modelo.AjustesId;
import br.gov.cultura.DitelAdm.modelo.Chamadas;
import br.gov.cultura.DitelAdm.modelo.ChamadasId;
import br.gov.cultura.DitelAdm.modelo.Cliente;
import br.gov.cultura.DitelAdm.modelo.ClienteId;
import br.gov.cultura.DitelAdm.modelo.Descontos;
import br.gov.cultura.DitelAdm.modelo.DescontosId;
import br.gov.cultura.DitelAdm.modelo.Enderecos;
import br.gov.cultura.DitelAdm.modelo.EnderecosId;
import br.gov.cultura.DitelAdm.modelo.Fatura;
import br.gov.cultura.DitelAdm.modelo.FaturaId;
import br.gov.cultura.DitelAdm.modelo.Notafiscal;
import br.gov.cultura.DitelAdm.modelo.NotafiscalId;
import br.gov.cultura.DitelAdm.modelo.Operadora;
import br.gov.cultura.DitelAdm.modelo.Planos;
import br.gov.cultura.DitelAdm.modelo.PlanosId;
import br.gov.cultura.DitelAdm.modelo.Resumo;
import br.gov.cultura.DitelAdm.modelo.ResumoId;
import br.gov.cultura.DitelAdm.modelo.Servicos;
import br.gov.cultura.DitelAdm.modelo.ServicosId;
import br.gov.cultura.DitelAdm.modelo.Trailler;
import br.gov.cultura.DitelAdm.modelo.TraillerId;
import br.gov.cultura.DitelAdm.repository.Operadoras;

@Service
public class FaturaService {
	/**
	@Autowired
	private static Ajustes ajustes; 
	@Autowired
	private static AjustesId ajustesId;
	@Autowired
	private static Chamadas chamadas;
	@Autowired
	private static ChamadasId chamadaId;
	@Autowired
	private static Cliente cliente;
	@Autowired
	private static ClienteId clienteId;
	@Autowired
	private static Descontos descontos;
	@Autowired
	private static DescontosId descontosId;
	@Autowired
	private static Enderecos enderecos;
	@Autowired
	private static EnderecosId enderecosId;
	@Autowired
	private static Fatura fatura;
	@Autowired
	private static FaturaId faturaId;
	@Autowired
	private static Notafiscal notafiscal;
	@Autowired
	private static NotafiscalId notafiscalId; */
	@Autowired
	private Operadoras operadoras;
/**
	@Autowired
	private static Planos planos;
	@Autowired
	private static PlanosId planosId;
	@Autowired
	private static Resumo resumo;
	@Autowired
	private static ResumoId resumoId;
	@Autowired
	private static Servicos servicos;
	@Autowired
	private static ServicosId servicosId;
	@Autowired
	private static Trailler trailler;
	@Autowired
	private static TraillerId traillerId;
	*/
	public void salvarOp(Operadora operadora) {
		operadoras.save(operadora);
	}

	
}
