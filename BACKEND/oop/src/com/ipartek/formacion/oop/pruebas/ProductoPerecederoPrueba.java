package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ipartek.formacion.oop.pojos.Producto;
import com.ipartek.formacion.oop.pojos.ProductoPerecedero;

public class ProductoPerecederoPrueba {
	public static void main(String[] args) {
		ProductoPerecedero productoPerecedero = new ProductoPerecedero();

		System.out.println(productoPerecedero);

		productoPerecedero.setNombre("Patatas");
		productoPerecedero.setPrecio(new BigDecimal("1.23"));
		productoPerecedero.setFechaCaducidad(LocalDate.of(2026, 1, 1));

		System.out.println(productoPerecedero);
		System.out.println(productoPerecedero.getPrecioIvaIncluido());
		System.out.println(productoPerecedero.getFechaCaducidad());

		ProductoPerecedero tomates = new ProductoPerecedero("Tomates", new BigDecimal("3.21"),
				LocalDate.now());

		System.out.println(tomates);
		System.out.println(tomates.getPrecioIvaIncluido());
		System.out.println(tomates.getFechaCaducidad());

		ProductoPerecedero p1 = new ProductoPerecedero("Producto", BigDecimal.TEN,
				LocalDate.now());
		ProductoPerecedero p2 = new ProductoPerecedero("Producto", BigDecimal.TEN,
				LocalDate.now().minusDays(1));

		System.out.println(p1 == p2);
		System.out.println(p1.equals(p2));
		System.out.println(p1.hashCode());
		System.out.println(p2.hashCode());
		System.out.println(p1);
		System.out.println(p2);

		System.out.println(p1.getPrecio());
		System.out.println(p2.getPrecio());

		Producto p = p1;

		System.out.println("TO STRING DE PRODUCTO: " + p.toString());
		
//		System.out.println(p.getFechaCaducidad()); // Falla

		if (p instanceof ProductoPerecedero otroP) {
//			ProductoPerecedero otroP = (ProductoPerecedero) p;

			System.out.println(otroP.getFechaCaducidad());
		} else {
			System.out.println("NO es un producto perecedero");
		}

		Producto nuevoProducto = new Producto();

		if (nuevoProducto instanceof ProductoPerecedero) {
			ProductoPerecedero nuevoProductoPerecedero = (ProductoPerecedero) nuevoProducto;

			System.out.println(nuevoProductoPerecedero.getFechaCaducidad());
		} else {
			System.out.println("NO es un producto perecedero");
		}
	}
}
