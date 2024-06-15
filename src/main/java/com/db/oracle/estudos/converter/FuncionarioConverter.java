package com.db.oracle.estudos.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.db.oracle.estudos.dto.FuncionarioDTO;
import com.db.oracle.estudos.entity.Funcionario;
import com.db.oracle.estudos.util.DataUtils;

@Component
public class FuncionarioConverter {
	
	public List<FuncionarioDTO> toDTOs (List<Funcionario> list) {
		List<FuncionarioDTO> dtos = new ArrayList<FuncionarioDTO>();
		list.stream().forEach(funcionario -> {
			FuncionarioDTO dto = toDTO(funcionario);
			dtos.add(dto);
		});
		
		return dtos;
	}

	public List<Funcionario> toEntities (List<FuncionarioDTO> listDTO) {
		List<Funcionario> entities = new ArrayList<Funcionario>();
		listDTO.stream().forEach(funcionario -> {
			Funcionario dto = toEntity(funcionario);
			entities.add(dto);
		});
		
		return entities;
	}

	public FuncionarioDTO toDTO(Funcionario funcionario) {
		FuncionarioDTO dto = FuncionarioDTO.builder()
				.nome(funcionario.getNome())
				.sobrenome(funcionario.getSobrenome())
				.matricula(funcionario.getMatricula())
				.endereco(funcionario.getEndereco())
				.cidade(funcionario.getCidade())
				.pais(funcionario.getPais())
				.dataNascimento(DataUtils.ajustarHorario(funcionario.getDataNascimento()))
				.cargo(funcionario.getCargo())
				.build();
		return dto;
	}

	public Funcionario toEntity(FuncionarioDTO dto) {
		Funcionario entity = Funcionario.builder()
				.nome(dto.getNome())
				.sobrenome(dto.getSobrenome())
				.matricula(dto.getMatricula())
				.endereco(dto.getEndereco())
				.cidade(dto.getCidade())
				.pais(dto.getPais())
				.dataNascimento(DataUtils.ajustarHorario(dto.getDataNascimento()))
				.cargo(dto.getCargo())
				.build();
		return entity;
	}

}
