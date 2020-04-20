package com.study.pontointeligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.study.pontointeligente.api.entities.Empresa;

@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {

	private static final String CNPJ = "51463645000100";
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@BeforeEach
	public void setUp() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa Exemplo");
		empresa.setCnpj(CNPJ);
		this.empresaRepository.save(empresa);
	}
	
	@AfterEach
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}
	
	@Test
	public void buscarPorCNPJ() {
		Empresa empresa = empresaRepository.findByCnpj(CNPJ);
		
		assertEquals(CNPJ, empresa.getCnpj());
	}
}
