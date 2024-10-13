package org.projarq.adapters.controllers.assinaturas;

import org.projarq.domain.entities.assinatura.Assinatura;
import org.projarq.application.use_cases.assinaturas.manage_subscriptions.AtualizarAssinaturas;
import org.projarq.domain.data_access.assinaturas.ESubscriptionStatusFilter;
import org.projarq.application.use_cases.assinaturas.query_subscription.BuscarAssinaturas;
import org.projarq.domain.entities.assinatura.EStatusAssinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping
public class AssinaturaController
{
	private final AtualizarAssinaturas atualizarAssinaturas;
	private final BuscarAssinaturas buscarAssinaturas;

	@Autowired
	public AssinaturaController(AtualizarAssinaturas atualizarAssinaturas, BuscarAssinaturas buscarAssinaturas)
	{
		this.atualizarAssinaturas = atualizarAssinaturas;
		this.buscarAssinaturas = buscarAssinaturas;
	}

	@PostMapping("/servcad/assinaturas")
	@ResponseStatus(HttpStatus.CREATED)
	public @NonNull Assinatura createSubscription(@RequestBody @NonNull CriarAssinaturaDTO criarAssinaturaDTO)
	{
		return atualizarAssinaturas.criarAssinatura(criarAssinaturaDTO.codigoCliente(), criarAssinaturaDTO.codigoAplicativo());
	}

	@GetMapping()
	public List<Assinatura> getAllSubscriptions()
	{
		return buscarAssinaturas.findAll();
	}

	@GetMapping("/servcad/assinaturas/{filterType}")
	public List<Assinatura> getAllSubscriptionsByFilter(@PathVariable @NonNull String filterType)
	{
		return buscarAssinaturas.findAllByFilter(ESubscriptionStatusFilter.convertFromLocalized(filterType));
	}

	@GetMapping("/servcad/asscli/{customerId}")
	List<Assinatura> getSubscriptionsForCustomer(@PathVariable long customerId)
	{
		return buscarAssinaturas.getAssinaturasPorCliente(customerId);
	}

	@GetMapping("/assinvalida/{subscriptionId}")
	public boolean isSubscriptionValid(@PathVariable long subscriptionId)
	{
		Optional<Assinatura> foundSubscription = buscarAssinaturas.findAssinaturaById(subscriptionId);

		if (foundSubscription.isEmpty())
		{
			throw new NoSuchElementException(String.valueOf(subscriptionId));
		}

		Assinatura assinatura = foundSubscription.get();

		return assinatura.status() == EStatusAssinatura.ACTIVE;
	}


}
