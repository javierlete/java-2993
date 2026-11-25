package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.oop.accesodatos.DaoProducto;
import com.ipartek.formacion.oop.accesodatos.DaoProductoMySql;
import com.ipartek.formacion.oop.pojos.Producto;

public class DaoProductoPrueba {
	public static void main(String[] args) {
		DaoProducto dao = new DaoProductoMySql();

//		dao.obtenerPorNombre("Prueba");
//		dao.obtenerPorPrecio(new BigDecimal("10"), new BigDecimal("100"));

		System.out.println(dao.insertar(new Producto("Botella", new BigDecimal("3.45"))));
		System.out.println(dao.modificar(new Producto(7L, "MODIFICADO", BigDecimal.ONE)));
		dao.borrar(5L);

		for(Producto producto: dao.obtenerTodos()) {
			System.out.println(producto);
		}
		
		System.out.println(dao.obtenerPorId(3L).get().getNombre());
		System.out.println(dao.obtenerPorId(33L));
		
	}
}
