package com.study.pontointeligente.api.service;

import java.util.Optional;

import com.study.pontointeligente.api.entities.Empresa;

public interface EmpresaService {
	
	/**
	 * 
	 * Retorna uma empresa
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscar(String cnpj);
	
	/**
	 * 
	 * Salvar empresa
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa salvar(Empresa empresa);

}
