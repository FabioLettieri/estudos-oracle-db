package com.db.oracle.estudos.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.oracle.estudos.dto.FuncionarioDTO;
import com.db.oracle.estudos.service.FuncionarioService;
import com.db.oracle.estudos.util.DataUtils;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@GetMapping
	public List<FuncionarioDTO> getAllFuncionario() {
		return service.getAllFuncionario();
	}
	
	@PostMapping
	public FuncionarioDTO createNewFuncionario(@RequestBody FuncionarioDTO dto) {
		return service.createNewFuncionario(dto);
	}
	
	@GetMapping("/calculo-idade")
	public String calculoIdadeFuncionario(@RequestParam String dataNasc) throws ParseException {
		return "Idade atual do Funcionario é: " + service.calculoIdade(DataUtils.stringToDateOtherFormat(dataNasc));
	}
}
