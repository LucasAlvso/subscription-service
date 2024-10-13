package org.projarq.adapters.controllers.pagamentos;

public record RegistrarPagamentoDTO(
        int dia,
        int mes ,
        int ano,
        long codass,
        double valorPago
)
{
}
