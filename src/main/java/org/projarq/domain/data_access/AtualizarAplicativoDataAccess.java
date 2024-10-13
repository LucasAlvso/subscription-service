package org.projarq.domain.data_access;

import org.projarq.domain.entities.Aplicativo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface AtualizarAplicativoDataAccess
{
	@NonNull
	Aplicativo atualizarCustoDoAplicativo(long codAplicativo, Double custoNovo);
}
