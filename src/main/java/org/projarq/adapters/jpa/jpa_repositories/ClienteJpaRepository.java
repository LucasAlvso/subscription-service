package org.projarq.adapters.jpa.jpa_repositories;

import org.projarq.adapters.jpa.entities.ClienteJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteJpaEntity, Long>
{
}
