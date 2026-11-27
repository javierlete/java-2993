package com.ipartek.formacion.oop.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.JdbcHelper;
import com.ipartek.formacion.bibliotecas.accesodatos.Mapeador;
import com.ipartek.formacion.oop.pojos.Cliente;

public class DaoClienteMySql implements DaoCliente {
	// TODO Extraer estos datos a un punto común para todos los DAOs
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/java_2993_basicos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "1234";

	private JdbcHelper jdbc = new JdbcHelper(JDBC_URL, JDBC_USER, JDBC_PASS);

	private static final String SQL_SELECT = "SELECT id, nombre, apellidos, nif FROM clientes";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id=?";
	private static final String SQL_INSERT = """
			INSERT INTO clientes
			(nombre, apellidos, nif)
			VALUES
			(?,?,?)
			""";
	private static final String SQL_UPDATE = """
			UPDATE clientes
			SET nombre=?,apellidos=?,nif=?
			WHERE id=?
			""";
	private static final String SQL_DELETE = "DELETE FROM clientes WHERE id=?";

	static class MapeadorCliente implements Mapeador<Cliente> {

		@Override
		public Cliente mapear(ResultSet rs) throws SQLException {
			return new Cliente(rs.getLong("id"), rs.getString("nombre"),
					rs.getString("apellidos"), rs.getString("nif"));
		}

	}

	@Override
	public Iterable<Cliente> obtenerTodos() {
		return jdbc.ejecutarConsulta(SQL_SELECT, new MapeadorCliente());
	}

	@Override
	public Optional<Cliente> obtenerPorId(Long id) {
		return jdbc.ejecutarConsultaUno(SQL_SELECT_ID, new MapeadorCliente(), id);
	}

	@Override
	public Cliente insertar(Cliente cliente) {
		cliente.setId(jdbc.ejecutarCambio(SQL_INSERT, cliente.getNombre(),
				cliente.getApellidos(), cliente.getNif()).get());

		return cliente;
	}

	@Override
	public Cliente modificar(Cliente cliente) {
		jdbc.ejecutarCambio(SQL_UPDATE, cliente.getNombre(), cliente.getApellidos(),
				cliente.getNif(), cliente.getId());

		return cliente;
	}

	@Override
	public void borrar(Long id) {
		jdbc.ejecutarCambio(SQL_DELETE, id);
	}

}
