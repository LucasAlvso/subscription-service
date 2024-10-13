package org.projarq.application.use_cases.assinaturas;

import org.projarq.domain.data_access.assinaturas.CriarAssinaturaDataAccess;
import org.projarq.domain.entities.Assinatura;
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
        return criarAssinaturaDataAccess.criarAssinatura(codCliente, codAplicativo, LocalDate.now(), LocalDate.now().plusDays(7));
	}

}
