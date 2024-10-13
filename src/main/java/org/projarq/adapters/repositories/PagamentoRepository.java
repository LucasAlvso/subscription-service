package org.projarq.adapters.repositories;

import org.projarq.domain.data_access.pagamentos.RegistrarPagamentoDataAccess;
import org.projarq.adapters.jpa.entities.PagamentoJpaEntity;
import org.projarq.adapters.jpa.entities.AssinaturaJpaEntity;
import org.projarq.adapters.jpa.jpa_repositories.PagamentoJpaRepository;
import org.projarq.domain.entities.Assinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PagamentoRepository implements RegistrarPagamentoDataAccess
{

    private final PagamentoJpaRepository pagamentoJpaRepository;

    @Autowired
    public PagamentoRepository(PagamentoJpaRepository pagamentoJpaRepository)
    {
        this.pagamentoJpaRepository = pagamentoJpaRepository;
    }

    @Override
    public void registrarPagamento(LocalDate paymentDate, @NonNull Assinatura assinatura, @NonNull Double quantidadePaga)
    {
        if (quantidadePaga <= 0)
        {
            throw new IllegalArgumentException(String.valueOf(quantidadePaga));
        }

        PagamentoJpaEntity payment = new PagamentoJpaEntity(AssinaturaJpaEntity.parseDeDomainEntity(assinatura), quantidadePaga, paymentDate, "none");
        pagamentoJpaRepository.save(payment);
    }

}
