package com.organizee.model.entities;

import java.io.Serializable;

public class DadosAcesso implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idAcesso;
	private String nomeAcesso;
	private String senhaAcesso;

	private Instituicao instituicao;

	private UsuarioSeguranca usuarioSeguranca;

	public DadosAcesso() {
	}

	public DadosAcesso(Integer idAcesso, String nomeAcesso, String senhaAcesso, Instituicao instituicao,
			UsuarioSeguranca usuarioSeguranca) {
		this.idAcesso = idAcesso;
		this.nomeAcesso = nomeAcesso;
		this.senhaAcesso = senhaAcesso;
		this.instituicao = instituicao;
		this.usuarioSeguranca = usuarioSeguranca;
	}

	public Integer getIdAcesso() {
		return idAcesso;
	}

	public void setIdAcesso(Integer idAcesso) {
		this.idAcesso = idAcesso;
	}

	public String getNomeAcesso() {
		return nomeAcesso;
	}

	public void setNomeAcesso(String nomeAcesso) {
		this.nomeAcesso = nomeAcesso;
	}

	public String getSenhaAcesso() {
		return senhaAcesso;
	}

	public void setSenhaAcesso(String senhaAcesso) {
		this.senhaAcesso = senhaAcesso;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}

	public UsuarioSeguranca getUsuarioSeguranca() {
		return usuarioSeguranca;
	}

	public void setUsuarioSeguranca(UsuarioSeguranca usuarioSeguranca) {
		this.usuarioSeguranca = usuarioSeguranca;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAcesso == null) ? 0 : idAcesso.hashCode());
		result = prime * result + ((instituicao == null) ? 0 : instituicao.hashCode());
		result = prime * result + ((nomeAcesso == null) ? 0 : nomeAcesso.hashCode());
		result = prime * result + ((senhaAcesso == null) ? 0 : senhaAcesso.hashCode());
		result = prime * result + ((usuarioSeguranca == null) ? 0 : usuarioSeguranca.hashCode());
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
		DadosAcesso other = (DadosAcesso) obj;
		if (idAcesso == null) {
			if (other.idAcesso != null)
				return false;
		} else if (!idAcesso.equals(other.idAcesso))
			return false;
		if (instituicao == null) {
			if (other.instituicao != null)
				return false;
		} else if (!instituicao.equals(other.instituicao))
			return false;
		if (nomeAcesso == null) {
			if (other.nomeAcesso != null)
				return false;
		} else if (!nomeAcesso.equals(other.nomeAcesso))
			return false;
		if (senhaAcesso == null) {
			if (other.senhaAcesso != null)
				return false;
		} else if (!senhaAcesso.equals(other.senhaAcesso))
			return false;
		if (usuarioSeguranca == null) {
			if (other.usuarioSeguranca != null)
				return false;
		} else if (!usuarioSeguranca.equals(other.usuarioSeguranca))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n===DadosAcesso===" + "\nNome Usuário: " + nomeAcesso + "\nSenha acesso: " + senhaAcesso + "\n"
				+ instituicao;
	}

}
