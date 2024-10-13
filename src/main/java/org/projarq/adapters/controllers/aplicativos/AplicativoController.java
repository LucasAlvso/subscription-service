package org.projarq.adapters.controllers.aplicativos;

import org.projarq.application.use_cases.aplicativos.query_applications.BuscarAplicativos;
import org.projarq.domain.entities.Aplicativo;
import org.projarq.application.use_cases.aplicativos.manage_applications.AtualizarAplicativos;
import org.projarq.domain.entities.assinatura.Assinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/servcad")
public class AplicativoController
{
	private final BuscarAplicativos buscarAplicativos;
	private final AtualizarAplicativos atualizarAplicativos;

	@Autowired
	public AplicativoController(BuscarAplicativos buscarAplicativos, AtualizarAplicativos atualizarAplicativos)
	{
		this.buscarAplicativos = buscarAplicativos;
		this.atualizarAplicativos = atualizarAplicativos;
	}

	@GetMapping()
	public List<Aplicativo> getAllApplications()
	{
		return buscarAplicativos.findAll();
	}

	@PostMapping("/aplicativos/atualizacusto/{applicationId}")
	@ResponseStatus(HttpStatus.CREATED)
	public @NonNull Aplicativo updateApplicationCost(@PathVariable long applicationId, @NonNull @RequestBody AtualizarCustoDTO newCost)
	{
		return atualizarAplicativos.atualizarCustoAplicativo(applicationId, newCost.custo());
	}

	@GetMapping("/assapp/{applicationId}")
	List<Assinatura> getSubscriptionsForApplication(@PathVariable long applicationId)
	{
		return buscarAplicativos.getSubscriptionsForApplication(applicationId);
	}
}
