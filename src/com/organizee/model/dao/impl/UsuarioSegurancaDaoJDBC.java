package com.organizee.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.organizee.db.DB;
import com.organizee.db.DbException;
import com.organizee.model.dao.UsuarioSegurancaDao;
import com.organizee.model.entities.UsuarioSeguranca;
import com.organizee.model.entities.enums.StatusUsuarioSeguranca;

public class UsuarioSegurancaDaoJDBC implements UsuarioSegurancaDao {

	private Connection conn;

	public UsuarioSegurancaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(UsuarioSeguranca obj) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"INSERT INTO `organize_keys`.`usuario_seguranca` (`usuario_seguranca`, `pass`, `ativo`) VALUES (?, ?, ?);",
					PreparedStatement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNomeUsuarioSeguranca());
			st.setString(2, obj.getPass());
			st.setInt(3, obj.getStatusUsuarioSeguranca().getValor());

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas > 0) {

				ResultSet rs = st.getGeneratedKeys();

				if (rs.next()) {

					int id = rs.getInt(1);
					obj.setIdUsuarioSeguranca(id);

					DB.closeResultSet(rs);

				} else {
					throw new DbException("Erro inesperado, nenhnum registro foi afetado!");
				}
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(UsuarioSeguranca obj) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement(
					"UPDATE usuario_seguranca SET usuario_seguranca = ? , pass = ?, ativo = ? WHERE id_usuario_seguranca = ?;");

			st.setString(1, obj.getNomeUsuarioSeguranca());
			st.setString(2, obj.getPass());
			st.setInt(3, obj.getStatusUsuarioSeguranca().getValor());
			st.setInt(4, obj.getIdUsuarioSeguranca());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("DELETE FROM usuario_seguranca WHERE id_usuario_seguranca = ?;");

			st.setInt(1, id);

			int linhasAfetadas = st.executeUpdate();

			if (linhasAfetadas == 0) {
				throw new DbException("Não há dados para deletar!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public UsuarioSeguranca findbyNome(String nome) {

		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM usuario_seguranca where usuario_seguranca = ?;");

			st.setString(1, nome);
			rs = st.executeQuery();

			while (rs.next()) {

				UsuarioSeguranca usuarioSeguranca = new UsuarioSeguranca();

				usuarioSeguranca.setIdUsuarioSeguranca(rs.getInt("id_usuario_seguranca"));
				usuarioSeguranca.setNomeUsuarioSeguranca(rs.getString("usuario_seguranca"));
				usuarioSeguranca.setPass(rs.getString("pass"));
				usuarioSeguranca.setStatusUsuarioSeguranca(StatusUsuarioSeguranca.setStatus(rs.getInt("ativo")));

				return usuarioSeguranca;

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
	public List<UsuarioSeguranca> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {

			st = conn.prepareStatement("SELECT * FROM organize_keys.usuario_seguranca;");

			rs = st.executeQuery();

			List<UsuarioSeguranca> listUsuarioSeguranca = new ArrayList<UsuarioSeguranca>();

			while (rs.next()) {

				UsuarioSeguranca usuarioSeguranca = new UsuarioSeguranca();

				usuarioSeguranca.setIdUsuarioSeguranca(rs.getInt("id_usuario_seguranca"));
				usuarioSeguranca.setNomeUsuarioSeguranca(rs.getString("usuario_seguranca"));
				usuarioSeguranca.setPass(rs.getString("pass"));
				usuarioSeguranca.setStatusUsuarioSeguranca(StatusUsuarioSeguranca.setStatus(rs.getInt("ativo")));

				listUsuarioSeguranca.add(usuarioSeguranca);

			}

			return listUsuarioSeguranca;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
