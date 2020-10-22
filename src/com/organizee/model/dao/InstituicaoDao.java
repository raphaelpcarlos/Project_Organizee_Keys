package com.organizee.model.dao;

import java.util.List;

import com.organizee.model.entities.Instituicao;

public interface InstituicaoDao {
	
	void insert (Instituicao obj);
	
	void update (Instituicao obj);
	
	void deleteById(Integer id);
	
	Instituicao findbyId(Integer id);
	
	List<Instituicao> findAll();

}
