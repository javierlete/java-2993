package com.ipartek.formacion.oop.accesodatos;

import com.ipartek.formacion.bibliotecas.accesodatos.JdbcHelper;

public class Configuracion {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/java_2993_basicos";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASS = "1234";

	static final JdbcHelper JDBC = new JdbcHelper(JDBC_URL, JDBC_USER, JDBC_PASS);
}
