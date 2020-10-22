package com.organizee.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.organizee.db.DB;
import com.organizee.db.DbException;
import com.organizee.model.dao.DadosAcessoDao;
import com.organizee.model.entities.DadosAcesso;
import com.organizee.model.entities.Instituicao;
import com.organizee.model.entities.TipoNegociacao;
import com.organizee.model.entities.UsuarioSeguranca;
import com.organizee.model.entities.enums.StatusUsuarioSeguranca;

public class DadosAcessoDaoJDBC implements DadosAcessoDao {

	private Connection conn;

	public DadosAcessoDaoJDBC(Connection conn) {

		this.conn = conn;
	}

	@Override
	public void insert(DadosAcesso obj) {
		PreparedStatement st = null;
		try {

			st = conn.prepareStatement("INSERT INTO `organize_keys`.`dados_acesso` "
					+ "(`nome_usuario`, `senha_usuario`, `id_usuario_seguranca`, `id_instituicao`) VALUES (?, ?, ?, ?);",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomeAcesso());
			st.setString(2, obj.getSenhaAcesso());
			st.setInt(3, obj.getUsuarioSeguranca().getIdUsuarioSeguranca());
			st.setInt(4, obj.getInstituicao().getIdInstituicao());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setIdAcesso(id);

				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro inesperado, nenhnum registro foi afetado!");

			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override // Sem uso
	public void update(DadosAcesso obj) {
		// TODO Auto-generated method stub

	}

	@Override // Sem uso
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override // Sem uso
	public DadosAcesso findbyId(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT id_acesso, nome_usuario, senha_usuario FROM organize_keys.dados_acesso where id_acesso = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			while (rs.next()) {

				DadosAcesso dadosAcesso = new DadosAcesso();

				dadosAcesso.setIdAcesso(rs.getInt("id_acesso"));
				dadosAcesso.setNomeAcesso(rs.getString("nome_usuario"));
				dadosAcesso.setSenhaAcesso(rs.getString("senha_usuario"));

				return dadosAcesso;

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<DadosAcesso> findAll(UsuarioSeguranca usuarioSeguranca) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT dacec.id_acesso, dacec.nome_usuario, dacec.senha_usuario,inst.nome_instituicao,tneg.tipo_negociacao FROM dados_acesso dacec "
							+ "inner join instituicao inst on dacec.id_instituicao = inst.id_instituicao "
							+ "inner join tipo_negociacao tneg on inst.id_tipo_negociacao = tneg.id_negociacao where id_usuario_seguranca = ?");

			st.setInt(1, usuarioSeguranca.getIdUsuarioSeguranca());
			rs = st.executeQuery();

			List<DadosAcesso> ListDadosAcesso = new ArrayList<DadosAcesso>();

			while (rs.next()) {

				DadosAcesso dadosAcesso = new DadosAcesso();
				Instituicao instituicao = new Instituicao();
				TipoNegociacao tipoNegociacao = new TipoNegociacao();

				dadosAcesso.setIdAcesso(rs.getInt("id_acesso"));
				dadosAcesso.setNomeAcesso(rs.getString("nome_usuario"));
				dadosAcesso.setSenhaAcesso(rs.getString("senha_usuario"));
				instituicao.setNomeInstituicao(rs.getString("nome_instituicao"));
				tipoNegociacao.setTipoNegociacao(rs.getString("tipo_negociacao"));
				instituicao.setTipoNegociacao(tipoNegociacao);
				dadosAcesso.setInstituicao(instituicao);

				ListDadosAcesso.add(dadosAcesso);

			}
			return ListDadosAcesso;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<DadosAcesso> findByUsuarioSeguranca(UsuarioSeguranca usuarioSeguranca) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"select *from dados_acesso as Dacesso inner join usuario_seguranca Useg on Dacesso.id_usuario_seguranca = Useg.id_usuario_seguranca inner join instituicao Inst on Dacesso.id_instituicao = Inst.id_instituicao Inner join tipo_negociacao Tneg on Inst.id_tipo_negociacao = Tneg.id_negociacao where Useg.id_usuario_seguranca = ?");

			st.setInt(1, usuarioSeguranca.getIdUsuarioSeguranca());

			rs = st.executeQuery();

			List<DadosAcesso> list = new ArrayList<DadosAcesso>();

			while (rs.next()) {

				Instituicao instituicao = new Instituicao();
				TipoNegociacao tipoNegociacao = new TipoNegociacao();
				UsuarioSeguranca userSeguranca = new UsuarioSeguranca();

				tipoNegociacao.setIdNegociacao(rs.getInt("id_negociacao"));
				tipoNegociacao.setTipoNegociacao(rs.getString("tipo_negociacao"));

				userSeguranca.setIdUsuarioSeguranca(rs.getInt("id_usuario_seguranca"));
				userSeguranca.setNomeUsuarioSeguranca(rs.getString("usuario_seguranca"));
				userSeguranca.setStatusUsuarioSeguranca(StatusUsuarioSeguranca.setStatus(rs.getInt("ativo")));

				instituicao.setIdInstituicao(rs.getInt("id_instituicao"));
				instituicao.setNomeInstituicao(rs.getNString("nome_instituicao"));
				instituicao.setSite(rs.getString("site"));
				instituicao.setTipoNegociacao(tipoNegociacao);

				list.add(new DadosAcesso(rs.getInt("id_acesso"), rs.getString("nome_usuario"),
						rs.getString("senha_usuario"), instituicao, usuarioSeguranca));
			}

			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<DadosAcesso> findBuscaNome(String nome, UsuarioSeguranca usuarioSeguranca) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT dacec.id_acesso, dacec.nome_usuario, dacec.senha_usuario,inst.nome_instituicao,tneg.tipo_negociacao FROM dados_acesso dacec "
							+ "inner join instituicao inst on dacec.id_instituicao = inst.id_instituicao "
							+ "inner join tipo_negociacao tneg on inst.id_tipo_negociacao = tneg.id_negociacao where nome_usuario like ? and id_usuario_seguranca = ?;");

			st.setString(1, "%" + nome + "%");
			st.setInt(2, usuarioSeguranca.getIdUsuarioSeguranca());
			rs = st.executeQuery();

			List<DadosAcesso> ListDadosAcesso = new ArrayList<DadosAcesso>();

			while (rs.next()) {

				DadosAcesso dadosAcesso = new DadosAcesso();
				Instituicao instituicao = new Instituicao();
				TipoNegociacao tipoNegociacao = new TipoNegociacao();

				dadosAcesso.setIdAcesso(rs.getInt("id_acesso"));
				dadosAcesso.setNomeAcesso(rs.getString("nome_usuario"));
				dadosAcesso.setSenhaAcesso(rs.getString("senha_usuario"));
				instituicao.setNomeInstituicao(rs.getString("nome_instituicao"));
				tipoNegociacao.setTipoNegociacao(rs.getString("tipo_negociacao"));
				instituicao.setTipoNegociacao(tipoNegociacao);
				dadosAcesso.setInstituicao(instituicao);

				ListDadosAcesso.add(dadosAcesso);

			}
			return ListDadosAcesso;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<DadosAcesso> findBuscaTipoNegociacao(String tipoNegociacao, UsuarioSeguranca usuarioSeguranca) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT dacec.id_acesso, dacec.nome_usuario, dacec.senha_usuario,inst.nome_instituicao,tneg.tipo_negociacao FROM dados_acesso dacec "
							+ "inner join instituicao inst on dacec.id_instituicao = inst.id_instituicao "
							+ "inner join tipo_negociacao tneg on inst.id_tipo_negociacao = tneg.id_negociacao where tneg.tipo_negociacao like ? and id_usuario_seguranca = ?;");

			st.setString(1, "%" + tipoNegociacao + "%");
			st.setInt(2, usuarioSeguranca.getIdUsuarioSeguranca());

			rs = st.executeQuery();

			List<DadosAcesso> ListDadosAcesso = new ArrayList<DadosAcesso>();

			while (rs.next()) {

				DadosAcesso dadosAcesso = new DadosAcesso();
				Instituicao instituicao = new Instituicao();
				TipoNegociacao tipoNegociacao1 = new TipoNegociacao();

				dadosAcesso.setIdAcesso(rs.getInt("id_acesso"));
				dadosAcesso.setNomeAcesso(rs.getString("nome_usuario"));
				dadosAcesso.setSenhaAcesso(rs.getString("senha_usuario"));
				instituicao.setNomeInstituicao(rs.getString("nome_instituicao"));
				tipoNegociacao1.setTipoNegociacao(rs.getString("tipo_negociacao"));
				instituicao.setTipoNegociacao(tipoNegociacao1);
				dadosAcesso.setInstituicao(instituicao);

				ListDadosAcesso.add(dadosAcesso);

			}
			return ListDadosAcesso;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<DadosAcesso> findBuscaInstituicao(String instituicao,UsuarioSeguranca usuarioSeguranca) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement(
					"SELECT dacec.id_acesso, dacec.nome_usuario, dacec.senha_usuario,inst.nome_instituicao,tneg.tipo_negociacao FROM dados_acesso dacec "
							+ "inner join instituicao inst on dacec.id_instituicao = inst.id_instituicao "
							+ "inner join tipo_negociacao tneg on inst.id_tipo_negociacao = tneg.id_negociacao where inst.nome_instituicao like ? and id_usuario_seguranca = ?;");

			st.setString(1, "%" + instituicao + "%");
			st.setInt(2, usuarioSeguranca.getIdUsuarioSeguranca());

			rs = st.executeQuery();

			List<DadosAcesso> ListDadosAcesso = new ArrayList<DadosAcesso>();

			while (rs.next()) {

				DadosAcesso dadosAcesso = new DadosAcesso();
				Instituicao instituicao1 = new Instituicao();
				TipoNegociacao tipoNegociacao1 = new TipoNegociacao();

				dadosAcesso.setIdAcesso(rs.getInt("id_acesso"));
				dadosAcesso.setNomeAcesso(rs.getString("nome_usuario"));
				dadosAcesso.setSenhaAcesso(rs.getString("senha_usuario"));
				instituicao1.setNomeInstituicao(rs.getString("nome_instituicao"));
				tipoNegociacao1.setTipoNegociacao(rs.getString("tipo_negociacao"));
				instituicao1.setTipoNegociacao(tipoNegociacao1);
				dadosAcesso.setInstituicao(instituicao1);

				ListDadosAcesso.add(dadosAcesso);

			}
			return ListDadosAcesso;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
