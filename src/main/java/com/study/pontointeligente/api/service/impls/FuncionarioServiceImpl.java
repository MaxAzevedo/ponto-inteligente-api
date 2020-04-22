package com.study.pontointeligente.api.service.impls;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.pontointeligente.api.entities.Funcionario;
import com.study.pontointeligente.api.repositories.FuncionarioRepository;
import com.study.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{
	
	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {
		log.info("Buscando funcionário de cpf {}", cpf);
		return Optional.ofNullable(funcionarioRepository.findByCpf(cpf));
	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {
		log.info("Buscando funcionário de email {}", email);
		return Optional.ofNullable(funcionarioRepository.findByEmail(email));
	}

	@Override
	public Optional<Funcionario> buscarPorCpfEmail(String cpf, String email) {
		log.info("Buscando funcionário de cpf {} e email {}", cpf, email);
		return Optional.ofNullable(funcionarioRepository.findByCpfOrEmail(cpf, email));
	}

	@Override
	public Funcionario salvar(Funcionario funcionario) {
		log.info("Salvando funcionário {}", funcionario);
		return funcionarioRepository.save(funcionario);
	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {
		log.info("Buscando funcionário por id {}", id);
		return funcionarioRepository.findById(id);
	}

}
