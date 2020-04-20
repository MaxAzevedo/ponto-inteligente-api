package com.study.pontointeligente.api.entities.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	private static final Logger log = LoggerFactory.getLogger(PasswordUtils.class);
	
	/**
	 * Gerar Hash utilizando o BCrypt.
	 * 
	 * @param senha
	 * @return
	 */
	public static String gerarByCrypt(String senha){
		if (senha == null) {
			return senha;
		}
		log.info("Gerando hash com script");
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(senha);
	}
}
