package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.projarq.adapters.jpa.ParseableToDomainEntity;
import org.projarq.domain.entities.Assinatura;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "assinaturas")
public class AssinaturaJpaEntity implements ParseableToDomainEntity<Assinatura>
{
	@NonNull
	@Override
	public Assinatura parseParaDomainEntity()
	{
		return new Assinatura
				       (
							   codigo,
						       aplicativo.parseParaDomainEntity(),
						       cliente.parseParaDomainEntity(),
							   inicioVigencia,
							   fimVigencia,
							   getStatus()
				       );
	}

	public @NonNull String getStatus()
	{
        return LocalDate.now().isAfter(fimVigencia) ? "CANCELADA" : "ATIVA";
	}



	public static @NonNull AssinaturaJpaEntity parseDeDomainEntity(@NonNull Assinatura assinatura)
	{
		return new AssinaturaJpaEntity(
			assinatura.codigo(),
			AplicativoJpaEntity.parseDeDomainEntity(assinatura.aplicativo()),
			ClienteJpaEntity.parseDeDomainEntity(assinatura.cliente()),
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
