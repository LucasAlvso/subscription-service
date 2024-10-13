package org.projarq.adapters.repositories;

import org.projarq.domain.entities.assinatura.Assinatura;
import org.projarq.adapters.jpa.entities.AplicativoJpaEntity;
import org.projarq.adapters.jpa.entities.ClienteJpaEntity;
import org.projarq.adapters.jpa.entities.AssinaturaJpaEntity;
import org.projarq.adapters.jpa.jpa_repositories.AplicativoJpaRepository;
import org.projarq.adapters.jpa.jpa_repositories.ClienteJpaRepository;
import org.projarq.adapters.jpa.jpa_repositories.AssinaturaJpaRepository;
import org.projarq.domain.data_access.CriarAssinaturaDataAccess;
import org.projarq.domain.data_access.AtualizarAssinaturaDataAccess;
import org.projarq.domain.data_access.assinaturas.ESubscriptionStatusFilter;
import org.projarq.domain.data_access.assinaturas.BuscarAssinaturasDataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class AssinaturaRepository implements CriarAssinaturaDataAccess, BuscarAssinaturasDataAccess, AtualizarAssinaturaDataAccess
{
    @Autowired
    public AssinaturaRepository(AssinaturaJpaRepository assinaturaJpaRepository, ClienteJpaRepository clienteJpaRepository, AplicativoJpaRepository aplicativoJpaRepository)
    {
        this.assinaturaJpaRepository = assinaturaJpaRepository;
        this.clienteJpaRepository = clienteJpaRepository;
        this.aplicativoJpaRepository = aplicativoJpaRepository;
    }

    @Override
    public @NonNull Assinatura criarAssinatura(long customerId, long applicationId, LocalDate startDate, LocalDate endDate)
    {
        Optional<AplicativoJpaEntity> aplicativo = aplicativoJpaRepository.findById(applicationId);

        if (aplicativo.isEmpty())
        {
            throw new NoSuchElementException(String.valueOf(applicationId));
        }

        Optional<ClienteJpaEntity> cliente = clienteJpaRepository.findById(customerId);

        if (cliente.isEmpty())
        {
            throw new NoSuchElementException(String.valueOf(customerId));
        }

        AssinaturaJpaEntity assinatura = new AssinaturaJpaEntity
        (
            aplicativo.get(),
            cliente.get(),
            startDate,
            endDate
        );

        return assinaturaJpaRepository.save(assinatura).toDomainEntity();
    }

    @Override
    public @NonNull List<Assinatura> getAssinaturas(@NonNull ESubscriptionStatusFilter filtro)
    {
        return assinaturaJpaRepository.querySubscriptions(filtro.toString())
                                        .stream()
                                        .map(AssinaturaJpaEntity::toDomainEntity)
                                        .toList();
    }

    @Override
    public @NonNull List<Assinatura> getAssinaturasPorCliente(long codCliente)
    {
        return assinaturaJpaRepository.getSubscriptionsForCustomer(codCliente)
                                        .stream()
                                        .map(AssinaturaJpaEntity::toDomainEntity)
                                        .toList();
    }

    @Override
    public @NonNull List<Assinatura> findAll()
    {
        return assinaturaJpaRepository.findAll()
                                        .stream()
                                        .map(AssinaturaJpaEntity::toDomainEntity)
                                        .toList();
    }

    @Override
    public @NonNull Optional<Assinatura> findById(long codAssinatura)
    {
        return assinaturaJpaRepository.findById(codAssinatura).map(AssinaturaJpaEntity::toDomainEntity);
    }

    private final AssinaturaJpaRepository assinaturaJpaRepository;
    private final ClienteJpaRepository clienteJpaRepository;
    private final AplicativoJpaRepository aplicativoJpaRepository;

    @Override
    public @NonNull Assinatura atualizarFimVigencia(long codAssinatura, LocalDate novaVigencia)
    {
        Optional<AssinaturaJpaEntity> existingSubscription = assinaturaJpaRepository.findById(codAssinatura);
        if (existingSubscription.isEmpty())
        {
            throw new NoSuchElementException(String.valueOf(codAssinatura));
        }

        AssinaturaJpaEntity subscription = existingSubscription.get();
        subscription.setFimVigencia(novaVigencia);

        return assinaturaJpaRepository.save(subscription).toDomainEntity();
    }
}
