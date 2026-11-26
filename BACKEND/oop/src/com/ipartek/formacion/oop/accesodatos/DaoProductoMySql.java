package com.ipartek.formacion.oop.accesodatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.JdbcHelper;
import com.ipartek.formacion.bibliotecas.accesodatos.Mapeador;
import com.ipartek.formacion.oop.pojos.Producto;

public class DaoProductoMySql implements DaoProducto {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/java_2993_basicos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "1234";

	private JdbcHelper jdbc = new JdbcHelper(JDBC_URL, JDBC_USER, JDBC_PASS);

	private static final String SQL_SELECT = "SELECT id, nombre, precio FROM productos";
	private static final String SQL_SELECT_ID = SQL_SELECT + " WHERE id=?";
	private static final String SQL_INSERT = """
			INSERT INTO productos
			(nombre, precio)
			VALUES
			(?,?)
			""";
	private static final String SQL_UPDATE = """
			UPDATE productos
			SET nombre=?,precio=?
			WHERE id=?
			""";
	private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";

	static class MapeadorProducto implements Mapeador<Producto> {

		@Override
		public Producto mapear(ResultSet rs) throws SQLException {
			return new Producto(rs.getLong("id"), rs.getString("nombre"),
					rs.getBigDecimal("precio"));
		}

	}

	@Override
	public Iterable<Producto> obtenerTodos() {
		return jdbc.ejecutarConsulta(SQL_SELECT, new MapeadorProducto());
	}

	@Override
	public Optional<Producto> obtenerPorId(Long id) {
		return jdbc.ejecutarConsultaUno(SQL_SELECT_ID, new MapeadorProducto(), id);
	}

	@Override
	public Producto insertar(Producto producto) {
		producto.setId(jdbc
				.ejecutarCambio(SQL_INSERT, producto.getNombre(), producto.getPrecio()).get());

		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		jdbc.ejecutarCambio(SQL_UPDATE, producto.getNombre(), producto.getPrecio(),
				producto.getId());
		
		return producto;
	}

	@Override
	public void borrar(Long id) {
		jdbc.ejecutarCambio(SQL_DELETE, id);
	}

}
