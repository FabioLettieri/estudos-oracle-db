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
	
	@GetMapping
	public ResponseEntity<List<FuncionarioDTO>> getAllFuncionario() {
		return service.getAllFuncionario();
	}
	
	@PostMapping
	public ResponseEntity<FuncionarioDTO> createNewFuncionario(@RequestBody FuncionarioDTO dto) {
		return service.createNewFuncionario(dto);
	}
	
	@GetMapping("/calculo-idade")
	public ResponseEntity<String> calculoIdadeFuncionario(@RequestParam String dataNasc) throws ParseException {
		return service.calculoIdade(DataUtils.stringToDateOtherFormat(dataNasc));
	}
	
	@PostMapping("/create-by-list")
	public ResponseEntity<List<FuncionarioDTO>> createNewFuncionarioList(@RequestBody List<FuncionarioDTO> dtos) {
		return service.createNewFuncionarioList(dtos);
	}
	
	@PutMapping("/update-by-id")
	public ResponseEntity<FuncionarioDTO> createNewFuncionarioList(@RequestParam Long id, @RequestBody FuncionarioDTO dto) {
		return service.updateFuncionario(id, dto);
	}
}
