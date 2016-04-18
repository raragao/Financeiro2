package com.algaworks.model;

import java.util.Date;

import javax.inject.Inject;

import com.algaworks.repository.Lancamentos;
import com.algaworks.util.Transactional;

public class CadastroLancamentos {

	@Inject
	private Lancamentos lancamentos;


	@Transactional
	public void salvar(Lancamento lancamento) throws NegocioException {

		if (lancamento.getDataPagamento() != null
				&& lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException(
					"Data de pagamento não pode ser uma data futura.");
		}
		this.lancamentos.guardar(lancamento);
	}
	
	@Transactional
	public void excluir(Lancamento lancamento) throws NegocioException{
		
		lancamento = this.lancamentos.porId(lancamento.getId());
		if(lancamento.getDataPagamento() != null){
			throw new NegocioException("Não é possivel excluir um lancamento pago!");
			
		}
		
		this.lancamentos.remover(lancamento);
	}
}
