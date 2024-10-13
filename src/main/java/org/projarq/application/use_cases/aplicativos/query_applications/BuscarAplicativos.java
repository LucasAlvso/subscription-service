package org.projarq.application.use_cases.aplicativos.query_applications;

import org.projarq.domain.entities.Aplicativo;
import org.projarq.domain.entities.assinatura.Assinatura;
import org.projarq.domain.data_access.BuscarAplicativosDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarAplicativos
{
	@Autowired
	public BuscarAplicativos(BuscarAplicativosDataAccess buscarAplicativosDataAccess)
	{
		this.buscarAplicativosDataAccess = buscarAplicativosDataAccess;
	}

	public @NonNull List<Assinatura> getSubscriptionsForApplication(long applicationId)
	{
		return buscarAplicativosDataAccess.getAssinaturasPorAplicativo(applicationId);
	}

	public List<Aplicativo> findAll()
	{
		return buscarAplicativosDataAccess.findAll();
	}

	private final BuscarAplicativosDataAccess buscarAplicativosDataAccess;
}
