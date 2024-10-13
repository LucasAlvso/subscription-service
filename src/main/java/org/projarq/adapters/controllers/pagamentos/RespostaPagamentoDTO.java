package org.projarq.adapters.controllers.pagamentos;

import java.time.LocalDate;

public record RespostaPagamentoDTO(String status, LocalDate endDate, double returnedValue)
{
}
