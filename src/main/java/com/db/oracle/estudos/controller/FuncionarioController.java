package com.db.oracle.estudos.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.oracle.estudos.entity.dto.FuncionarioDTO;
import com.db.oracle.estudos.service.FuncionarioService;
import com.db.oracle.estudos.util.DataUtils;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	/**
	 * Busca todos os funcionários
	 *
	 * @return Todos os funcionários como DTO
	 */
	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> getAllFuncionario() {
		return service.getAllFuncionario();
	}
	
	/**
	 * Cria um novo usuário da empresa
	 *
	 * @param dto É passado com as informações do novo funcionário
	 * @return O funcionário que foi salvo
	 */
	@PostMapping
	public ResponseEntity<FuncionarioDTO> createNewFuncionario(@RequestBody FuncionarioDTO dto) {
		return service.createNewFuncionario(dto);
	}
	
	/**
	 * Gera o calculo de idade do funcionário
	 *
	 * @param dataNasc É a data de nascimento do funcionário
	 * @return A idade do funcionário
	 */
	@GetMapping("/calculo-idade")
	public ResponseEntity<String> calculoIdadeFuncionario(@RequestParam String dataNasc) throws ParseException {
		return service.calculoIdade(DataUtils.stringToDateOtherFormat(dataNasc));
	}
	
	/**
	 * Cria um novo usuário da empresa por meio de lista
	 *
	 * @param dtos É a lista de novos funcionários que serão criados
	 * @return A lista de funcionarios salvos na demanda
	 */
	@PostMapping("/create-by-list")
	public ResponseEntity<List<FuncionarioDTO>> createNewFuncionarioList(@RequestBody List<FuncionarioDTO> dtos) {
		return service.createNewFuncionarioList(dtos);
	}
	
	/**
	 * Atualização de informações de um funcionário
	 *
	 * @param id É o identificador do funcionário
	 * @param dto É o funcionario com os atributos que devem ser atualizados
	 * @return O funcionário com as novas informações
	 */
	@PutMapping("/update-by-id")
	public ResponseEntity<FuncionarioDTO> createNewFuncionarioList(@RequestParam Long id, @RequestBody FuncionarioDTO dto) {
		return service.updateFuncionario(id, dto);
	}
}
