package com.study.pontointeligente.api.service.impls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.pontointeligente.api.entities.Empresa;
import com.study.pontointeligente.api.repositories.EmpresaRepository;
import com.study.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{
	
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@Override
	public Optional<Empresa> buscar(String cnpj) {
		log.info("Buscando empresa de CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa salvar(Empresa empresa) {
		log.info("Salvando empresa {}", empresa);
		return empresaRepository.save(empresa);
	}

}
