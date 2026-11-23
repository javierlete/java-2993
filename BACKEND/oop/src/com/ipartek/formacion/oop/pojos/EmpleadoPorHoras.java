package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;

public class EmpleadoPorHoras extends Empleado {
	private BigDecimal sueldoHora;
	private Integer numeroHorasMensuales;

	public EmpleadoPorHoras(Long id, String nombre, String dni, String nss,
			BigDecimal sueldoHora, Integer numeroHorasMensuales) {
		super(id, nombre, dni, nss);
		this.sueldoHora = sueldoHora;
		this.numeroHorasMensuales = numeroHorasMensuales;
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return sueldoHora.multiply(new BigDecimal(numeroHorasMensuales));
	}

}
