package com.ipartek.formacion.oop.accesodatos;

import java.math.BigDecimal;

import com.ipartek.formacion.bibliotecas.accesodatos.AccesoDatosException;
import com.ipartek.formacion.bibliotecas.accesodatos.Dao;
import com.ipartek.formacion.oop.pojos.Producto;

public interface DaoProducto extends Dao<Producto> {
	default Iterable<Producto> obtenerPorNombre(String texto) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}

	default Iterable<Producto> obtenerPorPrecio(BigDecimal minimo, BigDecimal maximo) {
		throw new AccesoDatosException("NO IMPLEMENTADO");
	}
}
