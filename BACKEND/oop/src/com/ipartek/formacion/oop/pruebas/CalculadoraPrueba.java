package com.ipartek.formacion.oop.pruebas;

import java.util.function.BiFunction;

public class CalculadoraPrueba {
	public static void main(String[] args) {
		Operacion op;
		
		op = new Sumar();
		
		System.out.println(op.operar(5, 6));
		
		op = new Restar();
		
		System.out.println(op.operar(5, 6));
		
		op = new Operacion() {
			@Override
			public int operar(int a, int b) {
				return a * b;
			}
		};
		
		System.out.println(op.operar(5, 6));
		
		op = (a, b) -> a / b;

		System.out.println(op.operar(5, 6));
		
		BiFunction<Integer, Integer, Integer> operacion;
		
		operacion = (a, b) -> Math.powExact(a, b);
		
		System.out.println(operacion.apply(5, 6));
	}
	
	static class Restar implements Operacion {

		@Override
		public int operar(int a, int b) {
			return a - b;
		}
		
	}
}

interface Operacion {
	int operar(int a, int b);
}

class Sumar implements Operacion {

	@Override
	public int operar(int a, int b) {
		return a + b;
	}
	
}