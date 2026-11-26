package com.ipartek.formacion.bibliotecas.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

public class JdbcHelper {
	private final String jdbcUrl;
	private final String jdbcUser;
	private final String jdbcPass;

	private Connection con;
	private PreparedStatement pst;

	public JdbcHelper(String jdbcUrl, String jdbcUser, String jdbcPass) {
		super();
		this.jdbcUrl = jdbcUrl;
		this.jdbcUser = jdbcUser;
		this.jdbcPass = jdbcPass;
	}

	public PreparedStatement crearPreparedStatement(String sql) {
		try {
			con = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPass);
			pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			return pst;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al crear la sentencia " + sql, e);
		}
	}

	public void cerrarConexion() {
		try {
			if (pst != null) {
				pst.close();
			}

			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al cerrar la conexión", e);
		}
	}

	public <T> Iterable<T> ejecutarConsulta(String sql, Mapeador<T> mapeador, Object... args) {
		try {
			PreparedStatement pst = crearPreparedStatement(sql);

			int pos = 1;

			for (Object arg : args) {
				pst.setObject(pos++, arg);
			}

			try (ResultSet rs = pst.executeQuery()) {
				ArrayList<T> objetos = new ArrayList<>();

				while (rs.next()) {
					objetos.add(mapeador.mapear(rs));
				}

				return objetos;
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener los objetos", e);
		} finally {
			cerrarConexion();
		}
	}

	public <T> Optional<T> ejecutarConsultaUno(String sql, Mapeador<T> mapeador,
			Object... args) {
		Iterable<T> objetos = ejecutarConsulta(sql, mapeador, args);

		if (objetos.iterator().hasNext()) {
			return Optional.of(objetos.iterator().next());
		}

		return Optional.empty();
	}

	public Optional<Long> ejecutarCambio(String sql, Object... args) {
		try {
			PreparedStatement pst = crearPreparedStatement(sql);

			int pos = 1;

			for (Object arg : args) {
				pst.setObject(pos++, arg);
			}

			int numeroRegistrosModificados = pst.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException(String.format("¡Se han cambiado %s registros!",
						numeroRegistrosModificados));
			}

			ResultSet rs = pst.getGeneratedKeys();

			if (rs.next()) {
				Long id = rs.getLong(1);

				return Optional.of(id);
			}

			return Optional.empty();
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al hacer el cambio", e);
		} finally {
			cerrarConexion();
		}
	}
}
