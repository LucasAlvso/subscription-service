package org.projarq.domain.data_access.assinaturas;

import org.springframework.lang.NonNull;

public enum StatusAssinaturaFilter
{
	TODAS,
	ATIVAS,
	CANCELADAS;

	public static @NonNull StatusAssinaturaFilter convertFromLocalized(@NonNull String filterType) throws IllegalArgumentException
	{
		return switch (filterType)
		{
			case "TODAS" -> TODAS;
			case "ATIVAS" -> ATIVAS;
			case "CANCELADAS" -> CANCELADAS;
			default -> throw new IllegalArgumentException(filterType);
		};
	}
}
