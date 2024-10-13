package org.projarq.application.use_cases.pagamentos;

import org.projarq.domain.data_access.assinaturas.BuscarAssinaturasDataAccess;
import org.projarq.domain.data_access.pagamentos.RegistrarPagamentoDataAccess;
import org.projarq.domain.data_access.assinaturas.AtualizarAssinaturaDataAccess;
import org.projarq.domain.entities.Assinatura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RegistrarPagamento
{
	private final RegistrarPagamentoDataAccess registrarPagamentoDataAccess;
	private final AtualizarAssinaturaDataAccess atualizarAssinaturaDataAccess;
	private final BuscarAssinaturasDataAccess buscarAssinaturasDataAccess;

	@Autowired
	public RegistrarPagamento(RegistrarPagamentoDataAccess registrarPagamentoDataAccess, AtualizarAssinaturaDataAccess atualizarAssinaturaDataAccess, BuscarAssinaturasDataAccess buscarAssinaturasDataAccess)
	{
        this.registrarPagamentoDataAccess = registrarPagamentoDataAccess;
		this.atualizarAssinaturaDataAccess = atualizarAssinaturaDataAccess;
		this.buscarAssinaturasDataAccess = buscarAssinaturasDataAccess;
	}

	public @NonNull LocalDate registrarPagamento(LocalDate data, long codAssinatura, @NonNull Double quantidadePaga)
	{
		Optional<Assinatura> assinaturaEncontrada = buscarAssinaturasDataAccess.getAssinaturaById(codAssinatura);

		if (assinaturaEncontrada.isEmpty())
        {
            throw new NoSuchElementException(String.valueOf(codAssinatura));
        }

		Assinatura assinatura = assinaturaEncontrada.get();

		if (quantidadePaga <= 0)
		{
			throw new IllegalArgumentException(String.valueOf(quantidadePaga.doubleValue()));
		}

		Double custoMensal = assinatura.aplicativo().custoMensal();
		if (!quantidadePaga.equals(custoMensal))
		{
			throw new IllegalStateException(String.valueOf(custoMensal));
		}

		registrarPagamentoDataAccess.registrarPagamento(data, assinatura, quantidadePaga);

		LocalDate vigenciaAtual = assinatura.fimVigencia().isAfter(data) ? assinatura.fimVigencia() : data;
		LocalDate novaVigencia = vigenciaAtual.plusMonths(1);
		atualizarAssinaturaDataAccess.atualizarFimVigencia(codAssinatura, novaVigencia);

		return novaVigencia;
	}

}
