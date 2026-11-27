package com.ipartek.formacion.oop.accesodatos;

import java.io.IOException;
import java.util.Properties;

import com.ipartek.formacion.bibliotecas.accesodatos.AccesoDatosException;
import com.ipartek.formacion.bibliotecas.accesodatos.JdbcHelper;

public class Configuracion {
	private static final String JDBC_URL;
	private static final String JDBC_USER;
	private static final String JDBC_PASS;

	static final JdbcHelper JDBC;

	static {
		Properties props = new Properties();
		try {
			props.load(
					Configuracion.class.getClassLoader().getResourceAsStream("oop.properties"));

			JDBC_URL = props.getProperty("dao.url");
			JDBC_USER = props.getProperty("dao.user");
			JDBC_PASS = props.getProperty("dao.pass");

			JDBC = new JdbcHelper(JDBC_URL, JDBC_USER, JDBC_PASS);
		} catch (IOException e) {
			throw new AccesoDatosException("Error al leer el fichero de configuración", e);
		}

	}
}
