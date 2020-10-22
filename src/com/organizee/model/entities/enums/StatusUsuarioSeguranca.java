package com.organizee.model.entities.enums;

public enum StatusUsuarioSeguranca {

	ATIVO(0), INATIVO(1);

	private int valor;

	private StatusUsuarioSeguranca(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}
	
	public static StatusUsuarioSeguranca setStatus(int valor) {
		
		if(valor == 0) {
			return StatusUsuarioSeguranca.ATIVO;
		}else {
			
			return StatusUsuarioSeguranca.INATIVO;
		}
	}

}
