package org.projarq.domain.data_access.assinaturas;

import org.springframework.lang.NonNull;

public enum ESubscriptionStatusFilter
{
	ALL,
	ACTIVE, CANCELLED;

	public static @NonNull ESubscriptionStatusFilter convertFromLocalized(@NonNull String filterType) throws IllegalArgumentException
	{
		return switch (filterType)
		{
			case "TODAS" -> ALL;
			case "ATIVAS" -> ACTIVE;
			case "CANCELADAS" -> CANCELLED;
			default -> throw new IllegalArgumentException(filterType);
		};
	}
}
