package com.study.pontointeligente.api.service;

import java.util.Optional;

import com.study.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {
	
	/**
	 * 
	 * Salvar funcionário
	 * 
	 * @param funcionario
	 * @return Funcionario
	 */
	Funcionario salvar(Funcionario funcionario);
	
	/**
	 * 
	 * Retorna um funcionario por id
	 * 
	 * @param id
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorId(Long id);
	
	/**
	 * 
	 * Retorna um funcionario por cpf
	 * 
	 * @param cpf
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCpf(String cpf);
	
	/**
	 * 
	 * Retorna um funcionario por email
	 * 
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorEmail(String email);
	
	/**
	 * 
	 * Retorna um funcionario por email
	 * 
	 * @param cpf
	 * @param email
	 * @return Optional<Funcionario>
	 */
	Optional<Funcionario> buscarPorCpfEmail(String cpf, String email);

}
