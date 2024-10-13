package org.projarq.domain.data_access;

import org.projarq.domain.entities.Cliente;

import java.util.List;

public interface BuscarClientesDataAccess
{
	List<Cliente> findAll();
}
