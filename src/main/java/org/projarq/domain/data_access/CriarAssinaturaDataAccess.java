package org.projarq.domain.data_access;

import org.projarq.domain.entities.assinatura.Assinatura;

import java.time.LocalDate;

public interface CriarAssinaturaDataAccess
{
    Assinatura criarAssinatura(long codCliente, long codAplicaitivo, LocalDate inicioVigencia, LocalDate fimVigencia);
}
