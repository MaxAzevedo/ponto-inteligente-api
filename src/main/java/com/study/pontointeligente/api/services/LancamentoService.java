package com.study.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.study.pontointeligente.api.entities.Lancamento;

public interface LancamentoService {
	
	/**
	 * 
	 * Buscar lancamentos do funcionario por id
	 * 
	 * @param funcionarioId
	 * @param pagenPageRequest
	 * @return Page<Lancamento>
	 */
	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pagenPageRequest);
	
	/**
	 * 
	 * Buscar lancamento por id
	 * 
	 * @param lancamentoId
	 * @return Optional<Funcionario>
	 */
	Optional<Lancamento> buscar(Long lancamentoId);
	
	/**
	 * 
	 * Salvar um lancamento
	 * 
	 * @param lancamento
	 * @return Lancamento
	 */
	Lancamento salvar(Lancamento lancamento);
	
	/**
	 * 
	 * Remover um Lancamento
	 * 
	 * @param id
	 */
	void remover(Long id);

}