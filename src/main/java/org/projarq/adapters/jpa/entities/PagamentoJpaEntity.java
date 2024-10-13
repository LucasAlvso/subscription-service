package org.projarq.adapters.jpa.entities;

import jakarta.persistence.*;
import org.projarq.adapters.jpa.ParseableToDomainEntity;
import org.projarq.domain.entities.Pagamento;
import org.springframework.lang.NonNull;

import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
public class PagamentoJpaEntity implements ParseableToDomainEntity<Pagamento>
{
	@NonNull
	@Override
	public Pagamento parseParaDomainEntity()
	{
		return new Pagamento(codigo, assinatura.parseParaDomainEntity(), valorPago, dataPagamento, promocao);
	}

	public PagamentoJpaEntity(AssinaturaJpaEntity assinatura, Double valorPago, LocalDate dataPagamento, String promocao)
	{
		this.assinatura = assinatura;
		this.valorPago = valorPago;
		this.dataPagamento = dataPagamento;
		this.promocao = promocao;
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
