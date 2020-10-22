package com.organizee.model.entities;

import java.io.Serializable;

public class TipoNegociacao implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer idNegociacao;
	private String tipoNegociacao;
	
	public TipoNegociacao() {
	}

	public TipoNegociacao(Integer idNegociacao, String tipoNegociacao) {
		this.idNegociacao = idNegociacao;
		this.tipoNegociacao = tipoNegociacao;
	}

	public Integer getIdNegociacao() {
		return idNegociacao;
	}

	public void setIdNegociacao(Integer idNegociacao) {
		this.idNegociacao = idNegociacao;
	}

	public String getTipoNegociacao() {
		return tipoNegociacao;
	}

	public void setTipoNegociacao(String tipoNegociacao) {
		this.tipoNegociacao = tipoNegociacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idNegociacao == null) ? 0 : idNegociacao.hashCode());
		result = prime * result + ((tipoNegociacao == null) ? 0 : tipoNegociacao.hashCode());
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
		TipoNegociacao other = (TipoNegociacao) obj;
		if (idNegociacao == null) {
			if (other.idNegociacao != null)
				return false;
		} else if (!idNegociacao.equals(other.idNegociacao))
			return false;
		if (tipoNegociacao == null) {
			if (other.tipoNegociacao != null)
				return false;
		} else if (!tipoNegociacao.equals(other.tipoNegociacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return tipoNegociacao;
	}


}
