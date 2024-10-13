package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import org.projarq.domain.entities.Cliente;
import org.projarq.adapters.jpa.ConvertibleToDomainEntity;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "clientes")
public class ClienteJpaEntity implements ConvertibleToDomainEntity<Cliente>
{
	@Override
	public @NonNull Cliente toDomainEntity()
	{
		return new Cliente
				       (
							   codCliente,
							   nome,
						       email
				       );
	}

	public static @NonNull ClienteJpaEntity fromDomainEntity(@NonNull Cliente cliente)
	{
		return new ClienteJpaEntity(cliente.codigo(), cliente.nome(), cliente.email());
	}

	protected ClienteJpaEntity()
	{
	}

	protected ClienteJpaEntity(Long codCliente, String nome, String email)
	{
		this.codCliente = codCliente;
		this.nome = nome;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codCliente;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;
}
