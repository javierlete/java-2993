package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EmpleadoIndefinido extends Empleado {
	private BigDecimal sueldoAnual;
	private Integer numeroPagas;
	
	public EmpleadoIndefinido(Long id, String nombre, String dni, String nss,
			BigDecimal sueldoAnual, Integer numeroPagas) {
		super(id, nombre, dni, nss);
		this.sueldoAnual = sueldoAnual;
		this.numeroPagas = numeroPagas;
	}

	@Override
	public BigDecimal getSueldoMensual() {
		return sueldoAnual.divide(new BigDecimal(numeroPagas), 2, RoundingMode.HALF_UP);
	}
	
	
}
