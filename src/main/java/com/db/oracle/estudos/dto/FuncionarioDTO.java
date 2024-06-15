package com.db.oracle.estudos.dto;

import java.util.Date;

import com.db.oracle.estudos.enums.CargoEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuncionarioDTO {
	private String nome;
	private String sobrenome;
	private Integer matricula;
	private String endereco;
	private String cidade;
	private String pais;
	private Date dataNascimento;
	private CargoEnum cargo;
}
