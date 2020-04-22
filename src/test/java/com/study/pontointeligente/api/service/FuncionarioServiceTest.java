package com.study.pontointeligente.api.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.study.pontointeligente.api.entities.Funcionario;
import com.study.pontointeligente.api.repositories.FuncionarioRepository;
import com.study.pontointeligente.api.services.FuncionarioService;

@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioServiceTest {
	
	private static final String CPF = "01234567890";

	@MockBean
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(funcionarioRepository.findByCpf(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(funcionarioRepository.findByEmail(Mockito.anyString())).willReturn(new Funcionario());
		BDDMockito.given(funcionarioRepository.findById(Mockito.anyLong())).willReturn(Optional.of(new Funcionario()));
		BDDMockito.given(funcionarioRepository.save(Mockito.any(Funcionario.class))).willReturn(new Funcionario());
	}
	
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorCpf(CPF);
		
		assertTrue(funcionario.isPresent());	
	}
	
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorEmail("teste@mail.com");
		
		assertTrue(funcionario.isPresent());	
	}
	
	@Test
	public void testBuscarFuncionarioPorId() {
		Optional<Funcionario> funcionario = funcionarioService.buscarPorId(1234l);
		
		assertTrue(funcionario.isPresent());	
	}
	
	@Test
	public void testSalvarFuncionario() {
		Funcionario funcionario = funcionarioService.salvar(new Funcionario());
		
		assertNotNull(funcionario);
	}
	
}
