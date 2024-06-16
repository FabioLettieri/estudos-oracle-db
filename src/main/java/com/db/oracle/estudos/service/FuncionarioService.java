package com.db.oracle.estudos.service;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.db.oracle.estudos.controller.FuncionarioController;
import com.db.oracle.estudos.converter.FuncionarioConverter;
import com.db.oracle.estudos.entity.Funcionario;
import com.db.oracle.estudos.entity.dto.FuncionarioDTO;
import com.db.oracle.estudos.respository.FuncionarioRepository;
import com.db.oracle.estudos.util.DataUtils;

@Service
public class FuncionarioService {

	private static final String GET_ALL_FUNCIONARIO = "getAllFuncionario()";
	private static final String CREATE_NEW_FUNCIONARIO = "createNewFuncionario()";
	private static final String CREATE_NEW_FUNCIONARIO_LIST = "createNewFuncionarioList()";
	private static final String CALCULO_IDADE = "calculoIdade()";
	private static final String GET_FUNCIONARIO_BY_ID = "getFuncionarioById()";
	private static final String UPDATE_FUNCIONARIO = "updateFuncionario()";
	private static final String UPDATE_FIELDS = "updateFields()";

	private static final Logger LOG = LoggerFactory.getLogger(FuncionarioController.class);

	@Autowired
	FuncionarioRepository repository;

	@Autowired
	FuncionarioConverter converter;

	public ResponseEntity<List<FuncionarioDTO>> getAllFuncionario() {
		LOG.info("START - " + GET_ALL_FUNCIONARIO + " - " + new Date());
		List<Funcionario> listFuncionario = repository.findAll();
		List<FuncionarioDTO> response = new ArrayList<FuncionarioDTO>();
		
		if (listFuncionario.isEmpty()) {
			LOG.info("FINISH - " + GET_ALL_FUNCIONARIO + " - " + new Date() + " " + response);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else {
			response = converter.toDTOs(listFuncionario);
			LOG.info("FINISH - " + GET_ALL_FUNCIONARIO + " - " + new Date() + " " + response);
			return ResponseEntity.ok(response);
		}
		
	}

	public ResponseEntity<FuncionarioDTO> createNewFuncionario(FuncionarioDTO dto) {
		LOG.info("START - " + CREATE_NEW_FUNCIONARIO + " - " + new Date());
		try {
			Funcionario entity = repository.save(converter.toEntity(dto));
			FuncionarioDTO dtoToReturn = converter.toDTO(entity);
			LOG.info("FINISH - " + CREATE_NEW_FUNCIONARIO + " - " + new Date() + " " + dtoToReturn);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(dtoToReturn);
		} catch (Exception error) {
			LOG.error("FINISH - " + CREATE_NEW_FUNCIONARIO + " - " + new Date() + " " + error.getMessage());
			return ResponseEntity.badRequest().build();
		}

	}

	public ResponseEntity<String> calculoIdade(Date dataNascimento) throws ParseException {
		LOG.info("START - " + CALCULO_IDADE + " - " + new Date());
		try {
			Integer response = DataUtils.calculoIdade(dataNascimento);
			
			LOG.info("FINISH - " + CALCULO_IDADE + " - " + new Date() + " " + response);
			return ResponseEntity.ok("Idade atual do Funcionario é: " + response);
		} catch (Exception error) {
			LOG.error("FINISH - " + CALCULO_IDADE + " - " + new Date() + " " + error.getMessage());
			return ResponseEntity.badRequest().build();
		}

	}

	public ResponseEntity<List<FuncionarioDTO>> createNewFuncionarioList(List<FuncionarioDTO> dtos) {
		LOG.info("START - " + CREATE_NEW_FUNCIONARIO_LIST + " - " + new Date());
		try {
			List<FuncionarioDTO> listDTOs = new ArrayList<FuncionarioDTO>();
			dtos.stream().forEach(dto -> {
				listDTOs.add(createNewFuncionario(dto).getBody());
			});
			
			LOG.info("FINISH - " + CREATE_NEW_FUNCIONARIO_LIST + " - " + new Date() + " " + listDTOs);
			return ResponseEntity.ok(listDTOs);
		} catch (Exception error) {
			LOG.error("FINISH - " + CREATE_NEW_FUNCIONARIO_LIST + " - " + new Date() + " " + error.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	public ResponseEntity<FuncionarioDTO> updateFuncionario(Long id, FuncionarioDTO dto) {
		LOG.info("START - " + UPDATE_FUNCIONARIO + " - " + new Date());
		try {
			Funcionario funcionario = getFuncionarioById(id);
			updateFields(funcionario, dto);
			FuncionarioDTO response = createNewFuncionario(converter.toDTO(funcionario)).getBody();
			LOG.info("FINISH - " + UPDATE_FUNCIONARIO + " - " + new Date() + " " + response);
			
			return ResponseEntity.ok(response);
		} catch (Exception error) {
			LOG.error("FINISH - " + UPDATE_FUNCIONARIO + " - " + new Date() + " " + error.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	private Funcionario getFuncionarioById(Long id) {
		LOG.info("START - " + GET_FUNCIONARIO_BY_ID + " - " + new Date());
		try {
			Funcionario response = repository.findById(id).get();
			LOG.info("FINISH - " + GET_FUNCIONARIO_BY_ID + " - " + new Date() + " " + response);
			
			return response;
		} catch (Exception error) {
			LOG.error("FINISH - " + GET_FUNCIONARIO_BY_ID + " - " + new Date() + " " + error.getMessage());
			return null;
		}
	}
	
	private void updateFields(Funcionario toUpdate, FuncionarioDTO dto) {
		LOG.info("START - " + UPDATE_FIELDS + " - " + new Date());
		Field[] fields = FuncionarioDTO.class.getDeclaredFields();
		
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				Object value = field.get(dto);
				
				if (value != null) {
					Field dtoField = Funcionario.class.getDeclaredField(field.getName());
					dtoField.setAccessible(true);
					dtoField.set(toUpdate, value);
				}
			} catch (NoSuchFieldException | IllegalAccessException error) {
				error.printStackTrace();
				LOG.error("FINISH - " + UPDATE_FIELDS + " - " + new Date() + " " + error.getMessage());
			}
		}
		LOG.info("FINISH - " + UPDATE_FIELDS + " - " + new Date() + " " + fields);
	}
}
