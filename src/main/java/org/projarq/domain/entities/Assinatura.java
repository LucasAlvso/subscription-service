package org.projarq.domain.entities;

import java.time.LocalDate;

public record Assinatura
(
	long codigo,
	Aplicativo aplicativo,
	Cliente cliente,
	LocalDate inicioVigencia,
	LocalDate fimVigencia,
	String status
)
{ }
