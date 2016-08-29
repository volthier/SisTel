package br.gov.cultura.DitelAdm.controller;

import org.springframework.beans.factory.annotation.Autowired;

import br.gov.cultura.DitelAdm.model.LimiteAtesto;
import br.gov.cultura.DitelAdm.service.LimiteAtestoService;

public class DasLimitesBase {

	@Autowired
	private LimiteAtestoService limiteAtestoService;

	public void baseInicio() {

		final LimiteAtesto limiteAtesto = new LimiteAtesto();

		int i = limiteAtesto.getIdLimiteAtesto();

		if (i < 1 ) {

			limiteAtesto.setDasAtesto("Sem DAS");
			limiteAtesto.setValorLimite("150.00");
			limiteAtestoService.salvar(limiteAtesto);
			limiteAtesto.setDasAtesto("1");
			limiteAtesto.setValorLimite("150.00");
			limiteAtestoService.salvar(limiteAtesto);
			limiteAtesto.setDasAtesto("2");
			limiteAtesto.setValorLimite("150.00");
			limiteAtestoService.salvar(limiteAtesto);
			limiteAtesto.setDasAtesto("3");
			limiteAtesto.setValorLimite("150.00");
			limiteAtestoService.salvar(limiteAtesto);
			limiteAtesto.setDasAtesto("4");
			limiteAtesto.setValorLimite("150.00");
			limiteAtestoService.salvar(limiteAtesto);
			limiteAtesto.setDasAtesto("5");
			limiteAtesto.setValorLimite("200.00");
			limiteAtestoService.salvar(limiteAtesto);
			limiteAtesto.setDasAtesto("6");
			limiteAtesto.setValorLimite("300.00");
			limiteAtestoService.salvar(limiteAtesto);
			limiteAtesto.setDasAtesto("NE");
			limiteAtesto.setValorLimite("500.00");
			limiteAtestoService.salvar(limiteAtesto);

		}
	}
}