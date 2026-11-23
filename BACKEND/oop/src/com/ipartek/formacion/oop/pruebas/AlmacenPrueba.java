package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.oop.pojos.Almacen;
import com.ipartek.formacion.oop.pojos.Persona;
import com.ipartek.formacion.oop.pojos.Producto;

public class AlmacenPrueba {
	public static void main(String[] args) {
		Almacen almacen = new Almacen(null, "Bilbao",
				new Persona(null, "Javier", "Lete", LocalDate.of(2000, 1, 2)));

		almacen.guardarProducto(new Producto("Portátil", new BigDecimal("1234.56")));
		almacen.guardarProducto(new Producto("Teclado", new BigDecimal("123.56")));
		almacen.guardarProducto(new Producto("Ratón", new BigDecimal("12.56")));
		almacen.guardarProducto(null);

		System.out.println(almacen);

		for (Producto producto : almacen.getProductos()) {
			System.out.println(producto);
			System.out.println(producto.getPrecioIvaIncluido());
		}

	}
}
