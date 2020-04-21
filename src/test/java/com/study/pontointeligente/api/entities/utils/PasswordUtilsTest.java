package com.study.pontointeligente.api.entities.utils;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtilsTest {
	
	private static final String SENHA = "123456";
	private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	
	@Test
	public void testSenhaNull() {
		assertNull(PasswordUtils.gerarByCrypt(null));
	}
	
	@Test
	public void testGerarSenha() {
		String hash = PasswordUtils.gerarByCrypt(SENHA);
		
		assertTrue(bCrypt.matches(SENHA, hash));
	}

}
