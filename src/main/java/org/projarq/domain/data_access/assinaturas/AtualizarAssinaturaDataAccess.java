package org.projarq.domain.data_access.assinaturas;

import org.projarq.domain.entities.Assinatura;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public interface AtualizarAssinaturaDataAccess
{
    Assinatura atualizarFimVigencia(long codAssinatura, LocalDate novaVigencia);
}
