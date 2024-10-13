package org.projarq.adapters.controllers;

import org.projarq.application.use_cases.clientes.BuscarClientes;
import org.projarq.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/servcad/clientes")
public class ClienteController
{
	@Autowired
	public ClienteController(BuscarClientes buscarClientes)
	{
		this.buscarClientes = buscarClientes;
	}

	@GetMapping()
	public List<Cliente> getTodosClientes()
	{
		return buscarClientes.getTodosClientes();
	}

	private final BuscarClientes buscarClientes;
}
