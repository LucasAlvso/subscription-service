package org.projarq.adapters.controllers.aplicativos;

import org.projarq.application.use_cases.aplicativos.BuscarAplicativos;
import org.projarq.domain.entities.Aplicativo;
import org.projarq.application.use_cases.aplicativos.AtualizarAplicativos;
import org.projarq.domain.entities.Assinatura;
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

	@GetMapping("/aplicativos")
	public List<Aplicativo> getAllAplicativos()
	{
		return buscarAplicativos.findAll();
	}

	@PostMapping("/aplicativos/atualizacusto/{codAplicativo}")
	@ResponseStatus(HttpStatus.CREATED)
	public @NonNull Aplicativo atualizarCustoDeAplicativo(@PathVariable long codAplicativo, @NonNull @RequestBody AtualizarCustoDTO novoCusto)
	{
		return atualizarAplicativos.atualizarCustoAplicativo(codAplicativo, novoCusto.custo());
	}

	@GetMapping("/assapp/{codAplicativo}")
	List<Assinatura> getAssinaturasPorAplicativo(@PathVariable long codAplicativo)
	{
		return buscarAplicativos.getAssinaturasPorAplicativo(codAplicativo);
	}
}
