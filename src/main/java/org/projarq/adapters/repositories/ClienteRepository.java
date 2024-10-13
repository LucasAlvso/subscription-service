package org.projarq.adapters.repositories;

import org.projarq.domain.entities.Cliente;
import org.projarq.adapters.jpa.entities.ClienteJpaEntity;
import org.projarq.adapters.jpa.jpa_repositories.ClienteJpaRepository;
import org.projarq.domain.data_access.clientes.BuscarClientesDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteRepository implements BuscarClientesDataAccess
{

	private final ClienteJpaRepository clienteJpaRepository;
	@Autowired
	public ClienteRepository(ClienteJpaRepository clienteJpaRepository)
	{
		this.clienteJpaRepository = clienteJpaRepository;
	}

	@Override
	public @NonNull List<Cliente> findAll()
	{
		return clienteJpaRepository.findAll()
								    .stream()
								    .map(ClienteJpaEntity::parseParaDomainEntity)
								    .toList();
	}

}
