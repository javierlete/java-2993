package com.ipartek.formacion.oop.accesodatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;

import com.ipartek.formacion.bibliotecas.accesodatos.AccesoDatosException;
import com.ipartek.formacion.oop.pojos.Producto;

public class DaoProductoMySql implements DaoProducto {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/java_2993_basicos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "1234";

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

	@Override
	public Iterable<Producto> obtenerTodos() {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(SQL_SELECT);
				ResultSet rs = pst.executeQuery()) {
			ArrayList<Producto> productos = new ArrayList<>();

			while (rs.next()) {
				productos.add(new Producto(rs.getLong("id"), rs.getString("nombre"),
						rs.getBigDecimal("precio")));
			}

			return productos;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener los productos", e);
		}
	}

	@Override
	public Optional<Producto> obtenerPorId(Long id) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(SQL_SELECT_ID);) {
			pst.setLong(1, id);

			try (ResultSet rs = pst.executeQuery()) {
				if (rs.next()) {
					return Optional.of(new Producto(rs.getLong("id"), rs.getString("nombre"),
							rs.getBigDecimal("precio")));
				}

				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al obtener el producto id " + id, e);
		}
	}

	@Override
	public Producto insertar(Producto producto) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(SQL_INSERT,
						Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, producto.getNombre());
			pst.setBigDecimal(2, producto.getPrecio());

			int numeroRegistrosModificados = pst.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException(String.format("¡Se han insertado %s registros!",
						numeroRegistrosModificados));
			}

			ResultSet rs = pst.getGeneratedKeys();

			rs.next();

			Long id = rs.getLong(1);

			producto.setId(id);

			return producto;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al insertar el producto " + producto, e);
		}
	}

	@Override
	public Producto modificar(Producto producto) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(SQL_UPDATE);) {
			pst.setString(1, producto.getNombre());
			pst.setBigDecimal(2, producto.getPrecio());
			pst.setLong(3, producto.getId());

			int numeroRegistrosModificados = pst.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException(String.format("¡Se han modificado %s registros!",
						numeroRegistrosModificados));
			}

			return producto;
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al modificar el producto " + producto, e);
		}
	}

	@Override
	public void borrar(Long id) {
		try (Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
				PreparedStatement pst = con.prepareStatement(SQL_DELETE);) {
			pst.setLong(1, id);

			int numeroRegistrosModificados = pst.executeUpdate();

			if (numeroRegistrosModificados != 1) {
				throw new AccesoDatosException(String.format("¡Se han borrado %s registros!",
						numeroRegistrosModificados));
			}
		} catch (SQLException e) {
			throw new AccesoDatosException("Error al borrar el producto " + id, e);
		}
	}

}
