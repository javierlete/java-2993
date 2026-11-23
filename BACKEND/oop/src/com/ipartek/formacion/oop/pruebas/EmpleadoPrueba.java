package com.ipartek.formacion.oop.pruebas;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.ipartek.formacion.oop.pojos.Empleado;
import com.ipartek.formacion.oop.pojos.EmpleadoIndefinido;
import com.ipartek.formacion.oop.pojos.EmpleadoPorHoras;

public class EmpleadoPrueba {
	public static void main(String[] args) {
		ArrayList<Empleado> empleados = new ArrayList<>();

		empleados.add(new EmpleadoIndefinido(null, "Javier", "12345678Z", "1234123412341234",
				new BigDecimal("23456"), 14));
		empleados.add(new EmpleadoPorHoras(null, "Pepe", "87654321Z", "4321432143214321",
				new BigDecimal("40"), 80));

		for (Empleado empleado : empleados) {
			System.out.println(empleado.getSueldoMensual());
		}
	}
}
