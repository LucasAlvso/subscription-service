package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import org.projarq.adapters.jpa.ParseableToDomainEntity;
import org.projarq.domain.entities.Usuario;

@Entity
@Table(name = "usuarios")
public class UsuarioJpaEntity
{
	//Classe apenas para a criação automática do DDL
	@Column(nullable = false)
	@Id
	private String usuario;

	@Column(nullable = false)
	private String senha;
}
