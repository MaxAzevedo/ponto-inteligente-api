package com.study.pontointeligente.api.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.study.pontointeligente.api.entities.Lancamento;
import com.study.pontointeligente.api.repositories.LancamentoRepository;
import com.study.pontointeligente.api.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService{
	
	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);
	
	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Override
	public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pagenPageRequest) {
		log.info("Buscando lancamento por funcionario id {}", funcionarioId);
		return lancamentoRepository.findByFuncionarioId(funcionarioId, pagenPageRequest);
	}

	@Override
	public Optional<Lancamento> buscar(Long lancamentoId) {
		log.info("Buscando lancamento por id {}", lancamentoId);
		return lancamentoRepository.findById(lancamentoId);
	}

	@Override
	public Lancamento salvar(Lancamento lancamento) {
		log.info("Salvando lancamento {}", lancamento);
		return lancamentoRepository.save(lancamento);
	}

	@Override
	public void remover(Long id) {
		log.info("Removendo lancamento de id {}", id);
		lancamentoRepository.deleteById(id);
	}

}
