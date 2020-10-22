package com.organizee.model.dao;

import com.organizee.db.DB;
import com.organizee.model.dao.impl.DadosAcessoDaoJDBC;
import com.organizee.model.dao.impl.InstituicaoDaoJDBC;
import com.organizee.model.dao.impl.TipoNegociacaoJDBC;
import com.organizee.model.dao.impl.UsuarioSegurancaDaoJDBC;

public class DaoFactory {
	
	public static DadosAcessoDao createDadosAcessoDao() {
		return new DadosAcessoDaoJDBC(DB.getConnection());
		
	}
	
	public static UsuarioSegurancaDao createUsuarioSegurancaDao() {
		return new UsuarioSegurancaDaoJDBC(DB.getConnection());
	}

	
	public static TipoNegociacaoDao createTipoNegociacaoDao() {
		return new TipoNegociacaoJDBC(DB.getConnection());
	}
	
	public static InstituicaoDao createInstituicaoDao() {
		return new InstituicaoDaoJDBC(DB.getConnection());
	}
	
}
