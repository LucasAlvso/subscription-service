package org.projarq.domain.data_access;

import org.projarq.domain.entities.assinatura.Assinatura;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface AtualizarAssinaturaDataAccess
{
    Assinatura atualizarFimVigencia(long codAssinatura, LocalDate novaVigencia);
}
