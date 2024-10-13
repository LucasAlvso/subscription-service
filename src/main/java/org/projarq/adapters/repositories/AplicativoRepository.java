package org.projarq.adapters.repositories;

import org.projarq.adapters.jpa.entities.AssinaturaJpaEntity;
import org.projarq.domain.entities.Aplicativo;
import org.projarq.domain.entities.Assinatura;
import org.projarq.adapters.jpa.entities.AplicativoJpaEntity;
import org.projarq.adapters.jpa.jpa_repositories.AplicativoJpaRepository;
import org.projarq.domain.data_access.aplicativos.AtualizarAplicativoDataAccess;
import org.projarq.domain.data_access.aplicativos.BuscarAplicativosDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class AplicativoRepository implements AtualizarAplicativoDataAccess, BuscarAplicativosDataAccess
{

	private final AplicativoJpaRepository aplicativoJpaRepository;

	@Autowired
	public AplicativoRepository(AplicativoJpaRepository aplicativoJpaRepository)
	{
		this.aplicativoJpaRepository = aplicativoJpaRepository;
	}

	@Override
	public @NonNull Aplicativo atualizarCustoDoAplicativo(long codAplicativo, @NonNull Double custoNovo) {
		if (custoNovo <= 0.0)
		{
			throw new IllegalArgumentException(String.valueOf(custoNovo));
		}
        Optional<AplicativoJpaEntity> aplicativoExistente = aplicativoJpaRepository.findById(codAplicativo);

        if (aplicativoExistente.isEmpty())
        {
            throw new NoSuchElementException(String.valueOf(codAplicativo));
        }
        else
        {
			AplicativoJpaEntity aplicativo = aplicativoExistente.get();
            aplicativo.setCustoMensal(custoNovo);

            return aplicativoJpaRepository.save(aplicativo).parseParaDomainEntity();
        }
    }

	@Override
	public @NonNull List<Assinatura> getAssinaturasPorAplicativo(long applicationId)
	{
		return aplicativoJpaRepository.getAssinaturasPorAplicativo(applicationId).stream()
									   .map(AssinaturaJpaEntity::parseParaDomainEntity)
									   .toList();
	}

	@Override
	public @NonNull List<Aplicativo> findAll()
	{
		return aplicativoJpaRepository.findAll()
									   .stream()
									   .map(AplicativoJpaEntity::parseParaDomainEntity)
									   .toList();
	}
}
