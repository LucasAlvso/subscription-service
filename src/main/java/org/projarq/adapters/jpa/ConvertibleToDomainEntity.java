package org.projarq.adapters.jpa;

import org.springframework.lang.NonNull;

public interface ConvertibleToDomainEntity<DomainEntityType>
{
	@NonNull
	DomainEntityType toDomainEntity();
}
