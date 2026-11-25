package com.ipartek.formacion.oop.accesodatos;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.TreeMap;

import com.ipartek.formacion.oop.pojos.Producto;

public class DaoProductoTreeMap implements DaoProducto {
	private static final TreeMap<Long, Producto> PRODUCTOS = new TreeMap<>();
	private static Long idUltimo = 3L;
	
	static {
		PRODUCTOS.put(1L, new Producto(1L, "PORTÁTIL", new BigDecimal("1234.56")));
		PRODUCTOS.put(2L, new Producto(2L, "MONITOR", new BigDecimal("123.56")));
		PRODUCTOS.put(3L, new Producto(3L, "RATÓN", new BigDecimal("12.56")));
	}
	
	@Override
	public Iterable<Producto> obtenerTodos() {
		return PRODUCTOS.values();
	}

	@Override
	public Optional<Producto> obtenerPorId(Long id) {
		Producto producto = PRODUCTOS.get(id);
		
		return Optional.ofNullable(producto);
	}

	@Override
	public Producto insertar(Producto producto) {
		Long id = ++idUltimo;
		
		producto.setId(id);
		
		PRODUCTOS.put(producto.getId(), producto);
		
		return producto;
	}

	@Override
	public Producto modificar(Producto producto) {
		PRODUCTOS.put(producto.getId(), producto);
		
		return producto;
	}

	@Override
	public void borrar(Long id) {
		PRODUCTOS.remove(id);
	}

}
