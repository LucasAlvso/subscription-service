package org.projarq.adapters.repositories;

import org.projarq.domain.entities.Assinatura;
import org.projarq.adapters.jpa.entities.AplicativoJpaEntity;
import org.projarq.adapters.jpa.entities.ClienteJpaEntity;
import org.projarq.adapters.jpa.entities.AssinaturaJpaEntity;
import org.projarq.adapters.jpa.jpa_repositories.AplicativoJpaRepository;
import org.projarq.adapters.jpa.jpa_repositories.ClienteJpaRepository;
import org.projarq.adapters.jpa.jpa_repositories.AssinaturaJpaRepository;
import org.projarq.domain.data_access.assinaturas.CriarAssinaturaDataAccess;
import org.projarq.domain.data_access.assinaturas.AtualizarAssinaturaDataAccess;
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

    private final AssinaturaJpaRepository assinaturaJpaRepository;
    private final ClienteJpaRepository clienteJpaRepository;
    private final AplicativoJpaRepository aplicativoJpaRepository;

    @Autowired
    public AssinaturaRepository(AssinaturaJpaRepository assinaturaJpaRepository, ClienteJpaRepository clienteJpaRepository, AplicativoJpaRepository aplicativoJpaRepository)
    {
        this.assinaturaJpaRepository = assinaturaJpaRepository;
        this.clienteJpaRepository = clienteJpaRepository;
        this.aplicativoJpaRepository = aplicativoJpaRepository;
    }

    @Override
    public @NonNull Assinatura criarAssinatura(long customerId, long applicationId, LocalDate vigenciaInicial, LocalDate vigenciaFinal)
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

        AssinaturaJpaEntity assinatura = new AssinaturaJpaEntity(aplicativo.get(), cliente.get(), vigenciaInicial, vigenciaFinal);

        return assinaturaJpaRepository.save(assinatura).parseParaDomainEntity();
    }

    @Override
    public @NonNull List<Assinatura> getAssinaturas(@NonNull String status)
    {
        return assinaturaJpaRepository.getAssinaturasPorStatus(status)
                                        .stream()
                                        .map(AssinaturaJpaEntity::parseParaDomainEntity)
                                        .toList();
    }

    @Override
    public @NonNull List<Assinatura> getAssinaturasPorCliente(long codCliente)
    {
        return assinaturaJpaRepository.getAssinaturasPorCliente(codCliente)
                                        .stream()
                                        .map(AssinaturaJpaEntity::parseParaDomainEntity)
                                        .toList();
    }

    @Override
    public @NonNull List<Assinatura> findAll()
    {
        return assinaturaJpaRepository.findAll()
                                        .stream()
                                        .map(AssinaturaJpaEntity::parseParaDomainEntity)
                                        .toList();
    }

    @Override
    public @NonNull Optional<Assinatura> getAssinaturaById(long codAssinatura)
    {
        return assinaturaJpaRepository.findById(codAssinatura).map(AssinaturaJpaEntity::parseParaDomainEntity);
    }

    @Override
    public @NonNull Assinatura atualizarFimVigencia(long codAssinatura, LocalDate novaVigencia)
    {
        Optional<AssinaturaJpaEntity> assinaturaExistente = assinaturaJpaRepository.findById(codAssinatura);
        if (assinaturaExistente.isEmpty())
        {
            throw new NoSuchElementException(String.valueOf(codAssinatura));
        }

        AssinaturaJpaEntity assinaturaNova = assinaturaExistente.get();
        assinaturaNova.setFimVigencia(novaVigencia);

        return assinaturaJpaRepository.save(assinaturaNova).parseParaDomainEntity();
    }
}
