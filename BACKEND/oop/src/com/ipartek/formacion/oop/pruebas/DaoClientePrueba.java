package com.ipartek.formacion.oop.pruebas;

import com.ipartek.formacion.bibliotecas.accesodatos.AccesoDatosException;
import com.ipartek.formacion.oop.accesodatos.DaoCliente;
import com.ipartek.formacion.oop.accesodatos.DaoClienteMySql;
import com.ipartek.formacion.oop.pojos.Cliente;

public class DaoClientePrueba {
	private static final DaoCliente DAO = new DaoClienteMySql();

	public static void main(String[] args) {

		try {
			System.out.println(DAO.insertar(new Cliente(null, "Javier", "Lete", "12345678Z")));

			listado();
		} catch (AccesoDatosException e) {
			System.out.println("No se ha insertado");
		}

		try {
			System.out.println(
					DAO.modificar(new Cliente(1L, "MODIFICADO", "MODIFICADEZ", "87654321Z")));

			System.out.println(DAO.obtenerPorId(1L).get().getNombre());
		} catch (AccesoDatosException e) {
			System.out.println("No se ha modificado");
		}

		try {
			DAO.borrar(1L);

			listado();
		} catch (AccesoDatosException e) {
			System.out.println("No se ha borrado");
		}

		System.out.println(DAO.obtenerPorId(33L));

	}

	private static void listado() {
		System.out.println("""
				LISTADO
				-------
				""");

		for (Cliente cliente : DAO.obtenerTodos()) {
			System.out.println(cliente);
		}

		System.out.println();
	}
}
