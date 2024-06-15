package com.db.oracle.estudos.entity;

import java.util.Date;

import com.db.oracle.estudos.enums.CargoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "FUNCIONARIO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_func")
	@SequenceGenerator(name = "id_func", sequenceName = "id_func", allocationSize = 1)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;
	
	@Column(name = "SOBRENOME", nullable = false, length = 50)
	private String sobrenome;
	
	@Column(name = "MATRICULA", nullable = false)
	private Integer matricula;
	
	@Column(name = "ENDERECO", length = 30)
	private String endereco;
	
	@Column(name = "CIDADE", length = 50)
	private String cidade;
	
	@Column(name = "PAIS", length = 25)
	private String pais;
	
	@Column(name = "DATA_NASC")
	private Date dataNascimento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CARGO", length = 50)
	private CargoEnum cargo;
}
