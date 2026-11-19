package com.ipartek.formacion.oop.presentacion.consola;

import static com.ipartek.formacion.bibliotecas.consola.Consola.*;

import java.time.LocalDate;

public class PersonaConsola {

	public static void main(String[] args) {
		int entero = rInt("Dame un entero");
		pl(entero);

		String texto = rString("Dame un texto");
		pl(texto);

		LocalDate fecha = rLocalDate("Dame una fecha");
		pl(fecha);
	}
}
