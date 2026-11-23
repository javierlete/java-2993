package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Empleado {
	private Long id;
	private String nombre;
	private String dni;
	private String nss;

	public Empleado(Long id, String nombre, String dni, String nss) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.dni = dni;
		this.nss = nss;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNss() {
		return nss;
	}

	public void setNss(String nss) {
		this.nss = nss;
	}
	
	public abstract BigDecimal getSueldoMensual();

	@Override
	public int hashCode() {
		return Objects.hash(dni, id, nombre, nss);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(dni, other.dni) && Objects.equals(id, other.id)
				&& Objects.equals(nombre, other.nombre) && Objects.equals(nss, other.nss);
	}

	@Override
	public String toString() {
		return String.format("Empleado [id=%s, nombre=%s, dni=%s, nss=%s]", id, nombre, dni,
				nss);
	}

}
