package org.projarq.application.use_cases.assinaturas.query_subscription;

import org.projarq.domain.data_access.assinaturas.ESubscriptionStatusFilter;
import org.projarq.domain.entities.assinatura.Assinatura;
import org.projarq.domain.data_access.assinaturas.BuscarAssinaturasDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BuscarAssinaturas
{
	private final BuscarAssinaturasDataAccess buscarAssinaturasDataAccess;
	
	@Autowired
	public BuscarAssinaturas(BuscarAssinaturasDataAccess buscarAssinaturasDataAccess)
	{
		this.buscarAssinaturasDataAccess = buscarAssinaturasDataAccess;
	}

	public List<Assinatura> findAll()
	{
		return buscarAssinaturasDataAccess.findAll();
	}

	public Optional<Assinatura> findAssinaturaById(long codAssinatura)
	{
		return buscarAssinaturasDataAccess.findById(codAssinatura);
	}

	public List<Assinatura> findAllByFilter(ESubscriptionStatusFilter filter)
	{
		return buscarAssinaturasDataAccess.getAssinaturas(filter);
	}

	public List<Assinatura> getAssinaturasPorCliente(long codCliente)
	{
		return buscarAssinaturasDataAccess.getAssinaturasPorCliente(codCliente);
	}

}
