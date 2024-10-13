package org.projarq.domain.entities;

import org.projarq.domain.entities.assinatura.Assinatura;

import java.time.LocalDate;

public record Pagamento
(
	long codigo,
	Assinatura assinatura,
	double valorPago,
	LocalDate dataPagamento,
	String promocao
)
{
}
