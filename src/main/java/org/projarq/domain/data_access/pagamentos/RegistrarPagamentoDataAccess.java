package org.projarq.domain.data_access.pagamentos;

import org.projarq.domain.entities.Assinatura;

import java.time.LocalDate;

public interface RegistrarPagamentoDataAccess
{
    void registrarPagamento(LocalDate dataPagamento, Assinatura assinatura, Double quantidadePaga);
}
