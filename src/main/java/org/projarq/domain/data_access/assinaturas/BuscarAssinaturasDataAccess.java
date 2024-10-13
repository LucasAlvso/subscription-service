package org.projarq.domain.data_access.assinaturas;

import org.projarq.domain.entities.assinatura.Assinatura;

import java.util.List;
import java.util.Optional;

public interface BuscarAssinaturasDataAccess
{
	List<Assinatura> getAssinaturas(ESubscriptionStatusFilter filtro);
	List<Assinatura> getAssinaturasPorCliente(long codCliente);

	List<Assinatura> findAll();

	Optional<Assinatura> findById(long codAssinatura);
}
