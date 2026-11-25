package com.ipartek.formacion.oop.pruebas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcPrueba {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:mysql://localhost:3306/java_2993_basicos";
		String user = "root";
		String pass = "1234";

		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();

		int numeroResultados = st.executeUpdate(
				"UPDATE productos SET nombre='Mega Portátil',precio=4321.21 WHERE id=1");

		System.out.println(numeroResultados);
		
		ResultSet rs = st.executeQuery("SELECT * FROM productos");

		while (rs.next()) {
			System.out.printf("%3s\t%-20s\t%10s\n", rs.getLong("id"), rs.getString("nombre"),
					rs.getBigDecimal("precio"));
		}
	}
}
