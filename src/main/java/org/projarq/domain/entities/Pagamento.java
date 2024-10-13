package org.projarq.domain.entities;

import java.time.LocalDate;

public record Pagamento
(
	long codigo,
	Assinatura assinatura,
	double valorPago,
	LocalDate dataPagamento,
	String promocao
)
{ }
