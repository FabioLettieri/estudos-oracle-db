package com.db.oracle.estudos.entity.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.db.oracle.estudos.enums.CargoEnum;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FuncionarioDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8459672703802570440L;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String sobrenome;
	
	@NotNull
	private Integer matricula;
	
	@NotBlank
	private String endereco;
	
	@NotBlank
	private String cidade;
	
	@NotBlank
	private String pais;
	
	@NotNull
	private Date dataNascimento;
	
	@NotBlank
	private CargoEnum cargo;
	
}
