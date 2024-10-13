package org.projarq.domain.entities.assinatura;

import org.projarq.domain.entities.Aplicativo;
import org.projarq.domain.entities.Cliente;

import java.time.LocalDate;

public record Assinatura
(
	long codigo,
	Aplicativo aplicativo,
	Cliente cliente,
	LocalDate inicioVigencia,
	LocalDate fimVigencia,
	EStatusAssinatura status
)
{ }
