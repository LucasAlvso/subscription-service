package org.projarq.domain.data_access;

import org.projarq.domain.entities.assinatura.Assinatura;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface RegistrarPagamentoDataAccess
{
    void registrarPagamento(LocalDate dataPagamento, Assinatura assinatura, Double quantidadePaga);
}
