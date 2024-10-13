package org.projarq.adapters.controllers.pagamentos;

import java.time.LocalDate;

public record RespostaPagamentoDTO(EStatusPagamento status, LocalDate endDate, double returnedValue)
{
}
