package org.projarq.adapters.controllers.assinaturas;

import org.projarq.domain.entities.Assinatura;
import org.projarq.application.use_cases.assinaturas.AtualizarAssinaturas;
import org.projarq.application.use_cases.assinaturas.BuscarAssinaturas;
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
	public @NonNull Assinatura criarAssinatura(@RequestBody @NonNull CriarAssinaturaDTO criarAssinaturaDTO)
	{
		return atualizarAssinaturas.criarAssinatura(criarAssinaturaDTO.codigoCliente(), criarAssinaturaDTO.codigoAplicativo());
	}

	@GetMapping()
	public List<Assinatura> getAllAssinaturas()
	{
		return buscarAssinaturas.findAll();
	}

	@GetMapping("/servcad/assinaturas/{status}")
	public List<Assinatura> getAssinaturasPorStatus(@PathVariable @NonNull String status)
	{
		return buscarAssinaturas.findAllByFilter(status);
	}

	@GetMapping("/servcad/asscli/{codCliente}")
	List<Assinatura> getAssinaturasPorCliente(@PathVariable long codCliente)
	{
		return buscarAssinaturas.getAssinaturasPorCliente(codCliente);
	}

	@GetMapping("/assinvalida/{codAssinatura}")
	public boolean isAssinaturaValida(@PathVariable long codAssinatura)
	{
		Optional<Assinatura> foundSubscription = buscarAssinaturas.findAssinaturaById(codAssinatura);

		if (foundSubscription.isEmpty())
		{
			throw new NoSuchElementException(String.valueOf(codAssinatura));
		}

		Assinatura assinatura = foundSubscription.get();

		return "ATIVA".equalsIgnoreCase(assinatura.status());
	}


}
