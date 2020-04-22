package com.study.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.pontointeligente.api.dtos.CadastroPJDTO;
import com.study.pontointeligente.api.entities.Empresa;
import com.study.pontointeligente.api.entities.Funcionario;
import com.study.pontointeligente.api.entities.utils.PasswordUtils;
import com.study.pontointeligente.api.enums.PerfilEnum;
import com.study.pontointeligente.api.response.Response;
import com.study.pontointeligente.api.services.EmpresaService;
import com.study.pontointeligente.api.services.FuncionarioService;

@RestController
@RequestMapping("/api/cadastrar-pj")
@CrossOrigin(origins = "*")
public class CadastroPJController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);
	
	@Autowired
	private EmpresaService empresaService;
	
	private FuncionarioService funcionarioService;
	
	public CadastroPJController() {}
	
	@GetMapping
	public ResponseEntity<String> teste() {		
		return ResponseEntity.ok("Foi");
	}
	
	@PostMapping
	public ResponseEntity<Response<CadastroPJDTO>> cadastrar(@Valid @RequestBody CadastroPJDTO cadastroPJDTO, 
			BindingResult result) throws NoSuchAlgorithmException {
		log.info("Cadastro PJ {}", cadastroPJDTO.toString());
		
		Response<CadastroPJDTO> response = new Response<>();
		
		validaeDadosExistentes(cadastroPJDTO, result);
		
		Empresa empresa = converterDtoParaEmpresa(cadastroPJDTO);
		Funcionario funcionario = converterDtoParaFuncionario(cadastroPJDTO, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de cadastro PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.empresaService.salvar(empresa);
		funcionario.setEmpresa(empresa);
		this.funcionarioService.salvar(funcionario);

		response.setData(this.converterCadastroPJDto(funcionario));
		return ResponseEntity.ok(response);
		
		
	}
	
	/**
	 * 
	 * Verifica se a empresa ou funcionário existem na base de dados
	 * 
	 * @param cadastroPJDTO
	 * @param result
	 */
	private void validaeDadosExistentes(CadastroPJDTO cadastroPJDTO, BindingResult result) {
		this.empresaService.buscar(cadastroPJDTO.getCnpj())
		.ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente")));

		this.funcionarioService.buscarPorCpf(cadastroPJDTO.getCpf())
		.ifPresent(func -> result.addError(new ObjectError("funcionario", "CPF já existente")));

		this.funcionarioService.buscarPorEmail(cadastroPJDTO.getEmail())
		.ifPresent(func -> result.addError(new ObjectError("funcionario", "Email já existente")));
	}
	
	/**
	 * Converte os dados do DTO para empresa.
	 * 
	 * @param cadastroPJDto
	 * @return Empresa
	 */
	private Empresa converterDtoParaEmpresa(CadastroPJDTO cadastroPJDto) {
		Empresa empresa = new Empresa();
		empresa.setCnpj(cadastroPJDto.getCnpj());
		empresa.setRazaoSocial(cadastroPJDto.getRazaoSocial());

		return empresa;
	}

	/**
	 * Converte os dados do DTO para funcionário.
	 * 
	 * @param cadastroPJDto
	 * @param result
	 * @return Funcionario
	 * @throws NoSuchAlgorithmException
	 */
	private Funcionario converterDtoParaFuncionario(CadastroPJDTO cadastroPJDto, BindingResult result)
			throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(cadastroPJDto.getNome());
		funcionario.setEmail(cadastroPJDto.getEmail());
		funcionario.setCpf(cadastroPJDto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarByCrypt(cadastroPJDto.getSenha()));

		return funcionario;
	}

	/**
	 * Popula o DTO de cadastro com os dados do funcionário e empresa.
	 * 
	 * @param funcionario
	 * @return CadastroPJDto
	 */
	private CadastroPJDTO converterCadastroPJDto(Funcionario funcionario) {
		CadastroPJDTO cadastroPJDto = new CadastroPJDTO();
		cadastroPJDto.setId(funcionario.getId());
		cadastroPJDto.setNome(funcionario.getNome());
		cadastroPJDto.setEmail(funcionario.getEmail());
		cadastroPJDto.setCpf(funcionario.getCpf());
		cadastroPJDto.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		cadastroPJDto.setCnpj(funcionario.getEmpresa().getCnpj());

		return cadastroPJDto;
	}
}
