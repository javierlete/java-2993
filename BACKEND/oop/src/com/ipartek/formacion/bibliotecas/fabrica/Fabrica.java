package com.ipartek.formacion.bibliotecas.fabrica;

import com.ipartek.formacion.oop.accesodatos.DaoClienteMySql;
import com.ipartek.formacion.oop.accesodatos.DaoProductoMySql;

public class Fabrica {
	public static Object fabricar(String identificador) {
		switch(identificador) {
		case "dao.cliente": return new DaoClienteMySql();
		case "dao.producto": return new DaoProductoMySql();
		default: throw new IllegalArgumentException("Clave no reconocida");
		}
	}
}
