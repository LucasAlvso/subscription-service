package org.projarq.application.use_cases.assinaturas.manage_subscriptions;

import org.projarq.domain.data_access.CriarAssinaturaDataAccess;
import org.projarq.domain.entities.assinatura.Assinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AtualizarAssinaturas

{
	private final CriarAssinaturaDataAccess criarAssinaturaDataAccess;

	@Autowired
	public AtualizarAssinaturas(CriarAssinaturaDataAccess criarAssinaturaDataAccess)
	{
		this.criarAssinaturaDataAccess = criarAssinaturaDataAccess;
	}

	public Assinatura criarAssinatura(long codCliente, long codAplicativo)
	{
        LocalDate now = LocalDate.now();

        return criarAssinaturaDataAccess.criarAssinatura(codCliente, codAplicativo, now, now.plusDays(7));
	}

}
