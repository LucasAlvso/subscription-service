package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.projarq.domain.entities.assinatura.Assinatura;
import org.projarq.domain.entities.assinatura.EStatusAssinatura;
import org.projarq.adapters.jpa.ConvertibleToDomainEntity;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "assinaturas")
public class AssinaturaJpaEntity implements ConvertibleToDomainEntity<Assinatura>
{
	@NonNull
	@Override
	public Assinatura toDomainEntity()
	{
		return new Assinatura
				       (
							   codigo,
						       aplicativo.toDomainEntity(),
						       cliente.toDomainEntity(),
							   inicioVigencia,
							   fimVigencia,
							   getStatus()
				       );
	}

	public @NonNull EStatusAssinatura getStatus()
	{
        return LocalDate.now().isAfter(fimVigencia) ? EStatusAssinatura.CANCELLED
												: EStatusAssinatura.ACTIVE;
	}



	public static @NonNull AssinaturaJpaEntity fromDomainEntity(@NonNull Assinatura assinatura)
	{
		return new AssinaturaJpaEntity(
			assinatura.codigo(),
			AplicativoJpaEntity.fromDomainEntity(assinatura.aplicativo()),
			ClienteJpaEntity.fromDomainEntity(assinatura.cliente()),
			assinatura.inicioVigencia(),
			assinatura.fimVigencia()
		);
	}

    public AssinaturaJpaEntity(AplicativoJpaEntity aplicativo, ClienteJpaEntity cliente, LocalDate inicioVigencia, LocalDate fimVigencia)
	{
		this.aplicativo = aplicativo;
		this.cliente = cliente;
		this.inicioVigencia = inicioVigencia;
		this.fimVigencia = fimVigencia;
	}

	protected AssinaturaJpaEntity(Long codigo, AplicativoJpaEntity aplicativo, ClienteJpaEntity cliente, LocalDate inicioVigencia, LocalDate fimVigencia)
	{
		this
		(
				aplicativo,
				cliente,
				inicioVigencia,
				fimVigencia
		);
		this.codigo = codigo;
	}

	protected AssinaturaJpaEntity()
	{
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

    @Getter
    @ManyToOne
	@JoinColumn(name = "aplicativo_codigo")
	AplicativoJpaEntity aplicativo;

	@ManyToOne()
	@JoinColumn(name = "cliente_codigo")
	ClienteJpaEntity cliente;

	@Column()
	LocalDate inicioVigencia;

	@Setter
    @Column
	LocalDate fimVigencia;
}
