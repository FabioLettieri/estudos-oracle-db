package com.db.oracle.estudos.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.oracle.estudos.controller.FuncionarioController;
import com.db.oracle.estudos.converter.FuncionarioConverter;
import com.db.oracle.estudos.dto.FuncionarioDTO;
import com.db.oracle.estudos.entity.Funcionario;
import com.db.oracle.estudos.respository.FuncionarioRepository;
import com.db.oracle.estudos.util.DataUtils;

@Service
public class FuncionarioService {
	
	 private static final String GET_ALL_FUNCIONARIO = "getAllFuncionario()";
	 private static final String CREATE_NEW_FUNCIONARIO = "createNewFuncionario()";
	 private static final String CALCULO_IDADE = "calculoIdade()";

	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioController.class);

	@Autowired
	FuncionarioRepository repository;
	
	@Autowired
    FuncionarioConverter converter;

    public List<FuncionarioDTO> getAllFuncionario() {
    	LOG.info("START - " + GET_ALL_FUNCIONARIO + " - " + new Date());
        List<Funcionario> listFuncionario = repository.findAll();
        LOG.info("FINISH - " + GET_ALL_FUNCIONARIO + " - " + new Date() + " " + listFuncionario);
        return converter.toDTOs(listFuncionario);
    }

	public FuncionarioDTO createNewFuncionario(FuncionarioDTO dto) {
		LOG.info("START - " + CREATE_NEW_FUNCIONARIO + " - " + new Date());
		Funcionario entity = repository.save(converter.toEntity(dto));
		FuncionarioDTO dtoToReturn = converter.toDTO(entity);
		LOG.info("FINISH - " + CREATE_NEW_FUNCIONARIO + " - " + new Date() + " " + dtoToReturn);
		
		return dtoToReturn;
	}
	
	public Integer calculoIdade(Date dataNascimento) throws ParseException {
		LOG.info("START - " + CALCULO_IDADE + " - " + new Date());
		Integer response = DataUtils.calculoIdade(dataNascimento);
		LOG.info("FINISH - " + CALCULO_IDADE + " - " + new Date() + " " + response);
		
		return response;
	}
}
