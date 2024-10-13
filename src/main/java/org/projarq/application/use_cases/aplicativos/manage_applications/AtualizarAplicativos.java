package org.projarq.application.use_cases.aplicativos.manage_applications;

import org.projarq.domain.entities.Aplicativo;
import org.projarq.domain.data_access.AtualizarAplicativoDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AtualizarAplicativos
{
	@Autowired
	public AtualizarAplicativos(AtualizarAplicativoDataAccess atualizarAplicativoDataAccess)
	{
		this.atualizarAplicativoDataAccess = atualizarAplicativoDataAccess;
	}

	public @NonNull Aplicativo atualizarCustoAplicativo(long codAplicativo, Double novoCusto)
	{
		return atualizarAplicativoDataAccess.atualizarCustoDoAplicativo(codAplicativo, novoCusto);
	}

	private final AtualizarAplicativoDataAccess atualizarAplicativoDataAccess;
}
