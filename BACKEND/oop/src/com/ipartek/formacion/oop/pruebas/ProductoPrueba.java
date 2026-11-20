package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;

import com.ipartek.formacion.oop.pojos.Producto;

public class ProductoPrueba {
	public static void main(String[] args) {
		Producto producto = new Producto();

		try {
			producto.setId(5L);
			producto.setNombre("      Pepe      ");
			producto.setPrecio(1234.567);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		System.out.println(producto);

		System.out.println(producto.getId());
		System.out.println(producto.getNombre());
		System.out.println(producto.getPrecio());

		Producto p2 = new Producto(1L, "Teclado", new BigDecimal("12.34"));

		System.out.println(p2.getId());
		System.out.println(p2.getNombre());
		System.out.println(p2.getPrecio());
		
		System.out.println(p2);
		
		Producto p3 = new Producto(p2);
		
		System.out.println(p2 == p3);
		System.out.println(p2.equals(p3));
		System.out.println(p2.hashCode());
		System.out.println(p3.hashCode());
	}
}
