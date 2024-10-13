package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import org.projarq.domain.entities.Pagamento;
import org.projarq.adapters.jpa.ConvertibleToDomainEntity;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
public class PagamentoJpaEntity implements ConvertibleToDomainEntity<Pagamento>
{
	@NonNull
	@Override
	public Pagamento toDomainEntity()
	{
		return new Pagamento
		(
				codigo,
			assinatura.toDomainEntity(),
				valorPago,
				dataPagamento,
			promocao
		);
	}

	public static @NonNull PagamentoJpaEntity fromDomainEntity(@NonNull Pagamento pagamento)
	{
		return new PagamentoJpaEntity(pagamento.codigo(), AssinaturaJpaEntity.fromDomainEntity(pagamento.assinatura()), pagamento.valorPago(), pagamento.dataPagamento(), pagamento.promocao());
	}

	public Long getCodigo()
	{
		return codigo;
	}

	public PagamentoJpaEntity(AssinaturaJpaEntity assinatura, Double valorPago, LocalDate dataPagamento, String promocao)
	{
		this.assinatura = assinatura;
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
		this.promocao = promocao;
	}

	protected PagamentoJpaEntity(Long codigo, AssinaturaJpaEntity assinatura, Double valorPago, LocalDate dataPagamento, String promocao)
	{
		this
		(
				assinatura,
				valorPago,
				dataPagamento,
				promocao
		);
		this.codigo = codigo;
	}

	protected PagamentoJpaEntity() {}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	@ManyToOne(optional = false)
	@JoinColumn(name = "cod_assinatura")
	private AssinaturaJpaEntity assinatura;

	@Column(nullable = false)
	private Double valorPago;

	@Column(nullable = false)
	private LocalDate dataPagamento;

	@Column()
	private String promocao;
}
