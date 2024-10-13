package org.projarq.adapters.controllers.pagamentos;

import org.projarq.application.use_cases.assinaturas.BuscarAssinaturas;
import org.projarq.application.use_cases.pagamentos.RegistrarPagamento;
import org.projarq.domain.entities.Assinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class PagamentoController
{

	private final RegistrarPagamento registrarPagamento;
	private final BuscarAssinaturas buscarAssinaturas;

	@Autowired
    public PagamentoController(RegistrarPagamento registrarPagamento, BuscarAssinaturas buscarAssinaturas)
    {
        this.registrarPagamento = registrarPagamento;
	    this.buscarAssinaturas = buscarAssinaturas;
    }

    @PostMapping(path = "/registrarpagamento")
    @ResponseStatus(HttpStatus.CREATED)
    public @NonNull RespostaPagamentoDTO registrarPagamento(@RequestBody @NonNull RegistrarPagamentoDTO registrarPagamentoDTO)
    {
	    try
	    {
		    LocalDate newEndDate = registrarPagamento.registrarPagamento(LocalDate.of(registrarPagamentoDTO.ano(), registrarPagamentoDTO.mes(), registrarPagamentoDTO.dia()), registrarPagamentoDTO.codass(), registrarPagamentoDTO.valorPago());
			return new RespostaPagamentoDTO("PAGAMENTO_OK", newEndDate,0.0);
	    }
	    catch (IllegalArgumentException e)
	    {
		    Optional<Assinatura> subscription = buscarAssinaturas.findAssinaturaById(registrarPagamentoDTO.codass());

			if (subscription.isEmpty())
		    {
				throw new NoSuchElementException(String.valueOf(registrarPagamentoDTO.codass()));
		    }

		    return new RespostaPagamentoDTO("VALOR_INCORRETO", subscription.get().fimVigencia(), 0.0);
        }
        catch (IllegalStateException e)
        {
	        Optional<Assinatura> subscription = buscarAssinaturas.findAssinaturaById(registrarPagamentoDTO.codass());

	        if (subscription.isEmpty())
	        {
		        throw new NoSuchElementException(String.valueOf(registrarPagamentoDTO.codass()));
	        }
            return new RespostaPagamentoDTO("VALOR_INCORRETO", subscription.get().fimVigencia(), registrarPagamentoDTO.valorPago());
        }
    }
}
