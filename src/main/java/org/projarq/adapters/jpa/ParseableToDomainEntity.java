package org.projarq.adapters.jpa;

import org.springframework.lang.NonNull;

public interface ParseableToDomainEntity<DomainEntityType>
{
	@NonNull
	DomainEntityType parseParaDomainEntity();
}
