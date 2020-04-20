package com.study.pontointeligente.api.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import com.study.pontointeligente.api.entities.Empresa;
import com.study.pontointeligente.api.entities.Funcionario;
import com.study.pontointeligente.api.entities.Lancamento;
import com.study.pontointeligente.api.entities.utils.PasswordUtils;
import com.study.pontointeligente.api.enums.PerfilEnum;
import com.study.pontointeligente.api.enums.TipoEnum;

@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	
	private static final String RAZAO_SOCIAL = "Empresa teste";
	private static final String CNPJ = "123456789";
	private static final String CPF = "07344984412";
	private static final String EMAIL = "max.azvd@gmail.com";
	private Long funcionarioId;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	EmpresaRepository empresaRepository;
	
	@Autowired
	LancamentoRepository lancamentoRepository;
	
	@BeforeEach
	public void setUp(){
		
		Empresa empresa = empresaRepository.save(getEmpresa());
		
		Funcionario funcionario = funcionarioRepository.save(getFuncionario(empresa));
		funcionarioId = funcionario.getId();
		
		lancamentoRepository.save(getLancamento(funcionario));
		lancamentoRepository.save(getLancamento(funcionario));

	}
	
	@Test
	public void testBiscarLancamentosPorFuncionarioId() {
		List<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionarioId);
		
		assertEquals(2, lancamentos.size());
	}
	
	@Test
	public void testBiscarLancamentosPaginadosPorFuncionarioId() {
		PageRequest page = PageRequest.of(0, 10);
		Page<Lancamento> lancamentos = lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		
		assertEquals(2, lancamentos.getTotalElements());
	}
	
	@AfterEach
	public final void tearDown() {
		empresaRepository.deleteAll();
	}
	
	private Lancamento getLancamento(Funcionario funcionario) {
		Lancamento lancamento = new Lancamento();
		
		lancamento.setFuncionario(funcionario);
		lancamento.setData(new Date());
		lancamento.setTipo(TipoEnum.INICIO_TRABALHO);
		
		return lancamento;
		
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
