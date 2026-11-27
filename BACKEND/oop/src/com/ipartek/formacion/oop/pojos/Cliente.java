package com.ipartek.formacion.oop.pojos;

import java.util.Objects;

public class Cliente {
	private Long id;
	private String nombre;
	private String apellidos;
	private String nif;

	public Cliente(Long id, String nombre, String apellidos, String nif) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellidos, id, nif, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(apellidos, other.apellidos) && Objects.equals(id, other.id)
				&& Objects.equals(nif, other.nif) && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		return String.format("Cliente [id=%s, nombre=%s, apellidos=%s, nif=%s]", id, nombre,
				apellidos, nif);
	}

}
