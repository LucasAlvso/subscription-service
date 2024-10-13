package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import org.projarq.domain.entities.Usuario;
import org.projarq.adapters.jpa.ConvertibleToDomainEntity;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "usuarios")
public class UsuarioJpaEntity implements ConvertibleToDomainEntity<Usuario>
{
	@NonNull
	@Override
	public Usuario toDomainEntity()
	{
		return new Usuario(usuario, senha);
	}

	protected UsuarioJpaEntity() {}


	@Column(nullable = false)
	@Id
	private String usuario;

	@Column(nullable = false)
	private String senha;
}
