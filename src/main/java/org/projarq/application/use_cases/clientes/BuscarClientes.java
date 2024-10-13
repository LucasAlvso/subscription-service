package org.projarq.application.use_cases.clientes;

import org.projarq.domain.data_access.clientes.BuscarClientesDataAccess;
import org.projarq.domain.entities.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuscarClientes
{
	private final BuscarClientesDataAccess buscarClientesDataAccess;

	public BuscarClientes(BuscarClientesDataAccess buscarClientesDataAccess)
	{
		this.buscarClientesDataAccess = buscarClientesDataAccess;
	}

	public List<Cliente> getTodosClientes()
	{
		return buscarClientesDataAccess.findAll();
	}

}
