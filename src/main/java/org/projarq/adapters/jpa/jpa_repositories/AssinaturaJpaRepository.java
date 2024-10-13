package org.projarq.adapters.jpa.jpa_repositories;

import org.projarq.adapters.jpa.entities.AssinaturaJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssinaturaJpaRepository extends JpaRepository<AssinaturaJpaEntity, Long>
{
	@Query("SELECT s FROM AssinaturaJpaEntity s WHERE s.cliente.codCliente = :codCliente")
	List<AssinaturaJpaEntity> getSubscriptionsForCustomer(long codCliente);

	@Query("SELECT s FROM AssinaturaJpaEntity s " +
	       "WHERE s.fimVigencia >= CURRENT_DATE AND s.inicioVigencia <= CURRENT_DATE AND :filtro = 'ACTIVE' " +
	       "OR s.fimVigencia < CURRENT_DATE AND :filtro = 'CANCELLED' " +
	       "OR :filtro = 'ALL'")
	List<AssinaturaJpaEntity> querySubscriptions(String filtro);
}
