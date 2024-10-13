package org.projarq.adapters.controllers.aplicativos;

import org.projarq.domain.entities.assinatura.Assinatura;
import org.projarq.application.use_cases.aplicativos.query_applications.BuscarAplicativos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "servcad/assapp")
public class ApplicationsSubscriptionsController
{
	@Autowired
	public ApplicationsSubscriptionsController(BuscarAplicativos buscarAplicativos)
	{
		this.buscarAplicativos = buscarAplicativos;
	}

	@GetMapping("/servcad/assapp/{applicationId}")
	List<Assinatura> getSubscriptionsForApplication(@PathVariable long applicationId)
	{
		return buscarAplicativos.getSubscriptionsForApplication(applicationId);
	}

	private final BuscarAplicativos buscarAplicativos;
}
