package com.organizee.model.entities;

import java.io.Serializable;

import com.organizee.model.entities.enums.StatusUsuarioSeguranca;

public class UsuarioSeguranca implements Serializable {

	private static final long serialVersionUID = 1L;
	// Atributos
	private Integer idUsuarioSeguranca;
	private String nomeUsuarioSeguranca;
	private String pass;
	private StatusUsuarioSeguranca statusUsuarioSeguranca;

	public UsuarioSeguranca() {
	}

	public UsuarioSeguranca(Integer idUsuarioSeguranca, String nomeUsuarioSeguranca, String pass,
			StatusUsuarioSeguranca statusUsuarioSeguranca) {
		this.idUsuarioSeguranca = idUsuarioSeguranca;
		this.nomeUsuarioSeguranca = nomeUsuarioSeguranca;
		this.pass = pass;
		this.statusUsuarioSeguranca = statusUsuarioSeguranca;
	}

	public Integer getIdUsuarioSeguranca() {
		return idUsuarioSeguranca;
	}

	public void setIdUsuarioSeguranca(Integer idUsuarioSeguranca) {
		this.idUsuarioSeguranca = idUsuarioSeguranca;
	}

	public String getNomeUsuarioSeguranca() {
		return nomeUsuarioSeguranca;
	}

	public void setNomeUsuarioSeguranca(String nomeUsuarioSeguranca) {
		this.nomeUsuarioSeguranca = nomeUsuarioSeguranca;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public StatusUsuarioSeguranca getStatusUsuarioSeguranca() {
		return statusUsuarioSeguranca;
	}

	public void setStatusUsuarioSeguranca(StatusUsuarioSeguranca statusUsuarioSeguranca) {
		this.statusUsuarioSeguranca = statusUsuarioSeguranca;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUsuarioSeguranca == null) ? 0 : idUsuarioSeguranca.hashCode());
		result = prime * result + ((nomeUsuarioSeguranca == null) ? 0 : nomeUsuarioSeguranca.hashCode());
		result = prime * result + ((pass == null) ? 0 : pass.hashCode());
		result = prime * result + ((statusUsuarioSeguranca == null) ? 0 : statusUsuarioSeguranca.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioSeguranca other = (UsuarioSeguranca) obj;
		if (idUsuarioSeguranca == null) {
			if (other.idUsuarioSeguranca != null)
				return false;
		} else if (!idUsuarioSeguranca.equals(other.idUsuarioSeguranca))
			return false;
		if (nomeUsuarioSeguranca == null) {
			if (other.nomeUsuarioSeguranca != null)
				return false;
		} else if (!nomeUsuarioSeguranca.equals(other.nomeUsuarioSeguranca))
			return false;
		if (pass == null) {
			if (other.pass != null)
				return false;
		} else if (!pass.equals(other.pass))
			return false;
		if (statusUsuarioSeguranca != other.statusUsuarioSeguranca)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n==Usuario de segurança==" + "\n\nID: " + idUsuarioSeguranca 
				+ "\nNome do usuário: " + nomeUsuarioSeguranca
				+ "\nSenha de acesso: " + pass + "\nStatus: " + statusUsuarioSeguranca;
	}

}
