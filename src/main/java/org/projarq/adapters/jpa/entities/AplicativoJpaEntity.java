package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import lombok.Setter;
import org.projarq.adapters.jpa.ParseableToDomainEntity;
import org.projarq.domain.entities.Aplicativo;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "aplicativos")
public class AplicativoJpaEntity implements ParseableToDomainEntity<Aplicativo>
{
	@NonNull
	@Override
	public Aplicativo parseParaDomainEntity()
	{
		return new Aplicativo(codAplicativo, nome, custoMensal);
	}

	public static @NonNull AplicativoJpaEntity parseDeDomainEntity(@NonNull Aplicativo aplicativo)
	{
		return new AplicativoJpaEntity(aplicativo.codigo(), aplicativo.nome(), aplicativo.custoMensal());
	}

	protected AplicativoJpaEntity()
	{
	}

	protected AplicativoJpaEntity(Long codAplicativo, String nome, Double custoMensal)
	{
		this.codAplicativo = codAplicativo;
		this.nome = nome;
		this.custoMensal = custoMensal;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codAplicativo;

	@Column(nullable = false)
	private String nome;

	@Setter
    @Column(nullable = false)
	private Double custoMensal;
}
