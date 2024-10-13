package org.projarq.application.use_cases.aplicativos;

import org.projarq.domain.entities.Aplicativo;
import org.projarq.domain.entities.Assinatura;
import org.projarq.domain.data_access.aplicativos.BuscarAplicativosDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarAplicativos
{
	private final BuscarAplicativosDataAccess buscarAplicativosDataAccess;
	@Autowired
	public BuscarAplicativos(BuscarAplicativosDataAccess buscarAplicativosDataAccess)
	{
		this.buscarAplicativosDataAccess = buscarAplicativosDataAccess;
	}

	public @NonNull List<Assinatura> getAssinaturasPorAplicativo(long codAplicativo)
	{
		return buscarAplicativosDataAccess.getAssinaturasPorAplicativo(codAplicativo);
	}

	public List<Aplicativo> findAll()
	{
		return buscarAplicativosDataAccess.findAll();
	}

}
