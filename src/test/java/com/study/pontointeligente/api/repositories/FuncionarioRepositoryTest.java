package com.study.pontointeligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.study.pontointeligente.api.entities.Empresa;
import com.study.pontointeligente.api.entities.Funcionario;
import com.study.pontointeligente.api.entities.utils.PasswordUtils;
import com.study.pontointeligente.api.enums.PerfilEnum;

@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	
	private static final String RAZAO_SOCIAL = "Empresa teste";
	private static final String CNPJ = "123456789";
	private static final String CPF = "07344984412";
	private static final String EMAIL = "max.azvd@gmail.com";
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	@Autowired
	EmpresaRepository empresaRepository;
	
	@BeforeEach
	public void setUp(){
		
		Empresa empresa = empresaRepository.save(getEmpresa());
		
		funcionarioRepository.save(getFuncionario(empresa));
	}
	
	@AfterEach
	public final void tearDown() {
		funcionarioRepository.deleteAll();
	}
	
	@Test
	public void testBscarFuncionarioPorCPF() {
		Funcionario funcionario = funcionarioRepository.findByCpf(CPF);
		
		assertEquals(funcionario.getEmail(), EMAIL);
	}
	
	@Test
	public void testBscarFuncionarioPorEMAIL() {
		Funcionario funcionario = funcionarioRepository.findByEmail(EMAIL);
		
		assertEquals(funcionario.getCpf(), CPF);
	}
	
	@Test
	public void buscarFuncionarioEmailOuCpf() {
		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void buscarFuncionarioEmailOuCpfEMailInvalido() {
		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail(CPF, "invalido@gmail.com");
		
		assertNotNull(funcionario);
	}
	
	@Test
	public void buscarFuncionarioEmailOuCpfECpfInvalido() {
		Funcionario funcionario = funcionarioRepository.findByCpfOrEmail("000001", EMAIL);
		
		assertNotNull(funcionario);
	}
	
	private Empresa getEmpresa() {
		Empresa empresa = new Empresa();
		
		empresa.setCnpj(CNPJ);
		empresa.setRazaoSocial(RAZAO_SOCIAL);
		
		return empresa;
	}
	
	private Funcionario getFuncionario(Empresa empresa) {
		Funcionario funcionario = new Funcionario();
		
		funcionario.setNome("Max");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setQtdHorasAlmoco(12.5f);
		funcionario.setQtdHorasTrabalhoDia(44.0f);
		funcionario.setSenha(PasswordUtils.gerarByCrypt("12345"));
		funcionario.setEmail(EMAIL);
		funcionario.setCpf(CPF);
		funcionario.setEmpresa(empresa);
		
		return funcionario;
	}
}
