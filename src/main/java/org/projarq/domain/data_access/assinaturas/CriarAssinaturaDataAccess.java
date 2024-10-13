package org.projarq.domain.data_access.assinaturas;

import org.projarq.domain.entities.Assinatura;

import java.time.LocalDate;

public interface CriarAssinaturaDataAccess
{
    Assinatura criarAssinatura(long codCliente, long codAplicaitivo, LocalDate inicioVigencia, LocalDate fimVigencia);
}
