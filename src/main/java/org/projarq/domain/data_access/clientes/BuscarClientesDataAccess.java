package org.projarq.domain.data_access.clientes;

import org.projarq.domain.entities.Cliente;

import java.util.List;

public interface BuscarClientesDataAccess
{
	List<Cliente> findAll();
}
