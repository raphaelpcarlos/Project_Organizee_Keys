package com.organizee.model.dao;

import java.util.List;

import com.organizee.model.entities.UsuarioSeguranca;

public interface UsuarioSegurancaDao {
	
	void insert (UsuarioSeguranca obj);
	
	void update (UsuarioSeguranca obj);
	
	void deleteById(Integer id);
	
	UsuarioSeguranca findbyNome(String nome);
	
	List<UsuarioSeguranca> findAll();
	
}
