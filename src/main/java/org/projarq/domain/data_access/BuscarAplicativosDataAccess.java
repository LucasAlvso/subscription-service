package org.projarq.domain.data_access;

import org.projarq.domain.entities.Aplicativo;
import org.projarq.domain.entities.assinatura.Assinatura;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface BuscarAplicativosDataAccess
{
	@NonNull
	List<Assinatura> getAssinaturasPorAplicativo(long codAplicativo);
	List<Aplicativo> findAll();
}
