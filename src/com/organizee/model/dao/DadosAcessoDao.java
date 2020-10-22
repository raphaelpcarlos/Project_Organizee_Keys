package com.organizee.model.dao;

import java.util.List;

import com.organizee.model.entities.DadosAcesso;
import com.organizee.model.entities.UsuarioSeguranca;

public interface DadosAcessoDao {

	void insert(DadosAcesso obj);

	void update(DadosAcesso obj);

	void deleteById(Integer id);

	DadosAcesso findbyId(Integer id);

	List<DadosAcesso> findAll(UsuarioSeguranca usuarioSeguranca);
	
	List<DadosAcesso> findByUsuarioSeguranca(UsuarioSeguranca usuarioSeguranca);
	
	List<DadosAcesso> findBuscaNome(String nome, UsuarioSeguranca usuarioSeguranca);
	
	List<DadosAcesso> findBuscaTipoNegociacao(String tipoNegociacao,UsuarioSeguranca usuarioSeguranca);
	
	List<DadosAcesso> findBuscaInstituicao(String instituicao,UsuarioSeguranca usuarioSeguranca);

}
