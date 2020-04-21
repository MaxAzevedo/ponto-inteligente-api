package com.study.pontointeligente.api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.study.pontointeligente.api.entities.Lancamento;
import com.study.pontointeligente.api.repositories.LancamentoRepository;

@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {
	

	@MockBean
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
		.willReturn(new PageImpl<Lancamento>(Arrays.asList(new Lancamento())));
		BDDMockito.given(lancamentoRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Lancamento()));
		BDDMockito.given(lancamentoRepository.save(Mockito.any(Lancamento.class))).willReturn(new Lancamento());

	}
	
	@Test
	public void testBuscarLancamentoPorFuncionarioId() {
		Page<Lancamento> lancamento = lancamentoService.buscarPorFuncionarioId(1L, PageRequest.of(0, 10	));
		assertNotNull(lancamento);
	}
	
	@Test
	public void testBuscarLancamentoPorId() {
		Optional<Lancamento> lancamento = lancamentoService.buscar(1L);
		assertNotNull(lancamento.isPresent());
	}
	
	@Test
	public void testSalvarLancamento() {
		Lancamento lancamento = lancamentoService.salvar(new Lancamento());
		assertNotNull(lancamento);
	}
	
	
	
}
