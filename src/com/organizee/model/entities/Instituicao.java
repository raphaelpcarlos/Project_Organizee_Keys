package com.organizee.model.entities;

import java.io.Serializable;

public class Instituicao implements Serializable{

	private static final long serialVersionUID = 1L;
	//Atributos
	private Integer idInstituicao;
	private String nomeInstituicao;
	private String site;
	
	//composição
	private TipoNegociacao tipoNegociacao;
	
	//Construtor
	public Instituicao() {
	}

	//Construtor
	public Instituicao(Integer idInstituicao, String nomeInstituicao, String site, TipoNegociacao tipoNegociacao) {
		this.idInstituicao = idInstituicao;
		this.nomeInstituicao = nomeInstituicao;
		this.site = site;
		this.tipoNegociacao = tipoNegociacao;
	}
	
	
	public Integer getIdInstituicao() {
		return idInstituicao;
	}

	public void setIdInstituicao(Integer idInstituicao) {
		this.idInstituicao = idInstituicao;
	}

	public String getNomeInstituicao() {
		return nomeInstituicao;
	}

	public void setNomeInstituicao(String nomeInstituicao) {
		this.nomeInstituicao = nomeInstituicao;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public TipoNegociacao getTipoNegociacao() {
		return tipoNegociacao;
	}

	public void setTipoNegociacao(TipoNegociacao tipoNegociacao) {
		this.tipoNegociacao = tipoNegociacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idInstituicao == null) ? 0 : idInstituicao.hashCode());
		result = prime * result + ((nomeInstituicao == null) ? 0 : nomeInstituicao.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode());
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
		Instituicao other = (Instituicao) obj;
		if (idInstituicao == null) {
			if (other.idInstituicao != null)
				return false;
		} else if (!idInstituicao.equals(other.idInstituicao))
			return false;
		if (nomeInstituicao == null) {
			if (other.nomeInstituicao != null)
				return false;
		} else if (!nomeInstituicao.equals(other.nomeInstituicao))
			return false;
		if (site == null) {
			if (other.site != null)
				return false;
		} else if (!site.equals(other.site))
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
		return nomeInstituicao;
	}

	
}
