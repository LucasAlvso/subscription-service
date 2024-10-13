package org.projarq.domain.data_access.assinaturas;

import org.projarq.domain.entities.assinatura.Assinatura;

import java.util.List;
import java.util.Optional;

public interface BuscarAssinaturasDataAccess
{
	List<Assinatura> getAssinaturas(StatusAssinaturaFilter filtro);
	List<Assinatura> getAssinaturasPorCliente(long codCliente);

	List<Assinatura> findAll();

	Optional<Assinatura> getAssinaturaById(long codAssinatura);
}
