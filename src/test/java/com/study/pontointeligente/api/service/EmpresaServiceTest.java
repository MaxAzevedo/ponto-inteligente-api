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

import com.study.pontointeligente.api.entities.Empresa;
import com.study.pontointeligente.api.repositories.EmpresaRepository;

@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {
	
	@MockBean
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private EmpresaService empresaService;
	
	@BeforeEach
	public void setUp() {
		BDDMockito.given(empresaRepository.findByCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());
	}
	
	@Test
	public void testBuscarempresaPorCnpj() {
		Optional<Empresa> empresa = empresaService.buscar("123456");
		
		assertTrue(empresa.isPresent());	
	}
	
	@Test
	public void testSalvarEmpresa() {
		Empresa empresa = empresaService.salvar(new Empresa());
		
		assertNotNull(empresa);
	}
	
}
