package org.projarq.adapters.jpa.jpa_repositories;

import org.projarq.adapters.jpa.entities.AssinaturaJpaEntity;
import org.projarq.adapters.jpa.entities.AplicativoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AplicativoJpaRepository extends JpaRepository<AplicativoJpaEntity, Long>
{
	@Query("SELECT s FROM AssinaturaJpaEntity s WHERE s.aplicativo.codAplicativo = :codAplicativo")
	List<AssinaturaJpaEntity> getAssinaturasPorAplicativo(long codAplicativo);
}
