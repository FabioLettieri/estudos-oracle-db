package com.db.oracle.estudos.enums;

import lombok.Getter;

@Getter
public enum CargoEnum {
	GERENTE("Gerente", 1),
	ANALISTA("Analista", 2),
	ASSISTENTE("Assistente", 3),
	COORDENADOR("Coordenador", 4),
	SUPERVISOR("Supervisor", 5),
	ESTAGIARIO("Estagiario", 6),
	DIRETOR("Diretor", 7),
	CEO("Diretor Executivo", 8),
	CFO("Diretor Financeiro", 9),
	CTO("Diretor Tecnologia", 10),
	DESENVOLVEDOR("Desenvolvedor", 11)
	;
	

	private String tipo;
	private Integer codigo;

	CargoEnum(String tipo, Integer codigo) {
		this.tipo = tipo;
		this.codigo = codigo;
	}
}
