package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.bibliotecas.accesodatos.AccesoDatosException;
import com.ipartek.formacion.bibliotecas.fabrica.Fabrica;
import com.ipartek.formacion.oop.accesodatos.DaoProducto;
import com.ipartek.formacion.oop.pojos.Producto;

public class DaoProductoPrueba {
	public static void main(String[] args) {
		DaoProducto dao = (DaoProducto) Fabrica.fabricar("dao.producto");

//		dao.obtenerPorNombre("Prueba");
//		dao.obtenerPorPrecio(new BigDecimal("10"), new BigDecimal("100"));

		try {
			System.out.println(dao.insertar(new Producto("Botella3", new BigDecimal("3.45"))));
		} catch (AccesoDatosException e) {
			System.out.println("No se ha insertado");
		}

		System.out.println(dao.modificar(new Producto(7L, "MODIFICADO", BigDecimal.ONE)));

		try {
			dao.borrar(9L);
		} catch (AccesoDatosException e) {
			System.out.println("No se ha borrado");
		}

		for (Producto producto : dao.obtenerTodos()) {
			System.out.println(producto);
		}

		System.out.println(dao.obtenerPorId(3L).get().getNombre());
		System.out.println(dao.obtenerPorId(33L));

	}
}
