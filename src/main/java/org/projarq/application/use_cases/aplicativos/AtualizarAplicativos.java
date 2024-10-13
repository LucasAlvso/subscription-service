package org.projarq.application.use_cases.aplicativos;

import org.projarq.domain.entities.Aplicativo;
import org.projarq.domain.data_access.aplicativos.AtualizarAplicativoDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AtualizarAplicativos
{
	private final AtualizarAplicativoDataAccess atualizarAplicativoDataAccess;

	@Autowired
	public AtualizarAplicativos(AtualizarAplicativoDataAccess atualizarAplicativoDataAccess)
	{
		this.atualizarAplicativoDataAccess = atualizarAplicativoDataAccess;
	}

	public @NonNull Aplicativo atualizarCustoAplicativo(long codAplicativo, Double novoCusto)
	{
		return atualizarAplicativoDataAccess.atualizarCustoDoAplicativo(codAplicativo, novoCusto);
	}

}
