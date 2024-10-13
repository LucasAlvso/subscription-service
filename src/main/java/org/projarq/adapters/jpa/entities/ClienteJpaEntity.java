package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import org.projarq.adapters.jpa.ParseableToDomainEntity;
import org.projarq.domain.entities.Cliente;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "clientes")
public class ClienteJpaEntity implements ParseableToDomainEntity<Cliente>
{
	@Override
	public @NonNull Cliente parseParaDomainEntity()
	{
		return new Cliente
				       (
							   codCliente,
							   nome,
						       email
				       );
	}

	public static @NonNull ClienteJpaEntity parseDeDomainEntity(@NonNull Cliente cliente)
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
