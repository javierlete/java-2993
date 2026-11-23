package com.ipartek.formacion.oop.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Almacen {
	// VARIABLES DE INSTANCIA
	private Long id;
	private String ubicacion;
	private Persona encargado;

	private ArrayList<Producto> productos = new ArrayList<>();

	// CONSTRUCTORES
	public Almacen(Long id, String ubicacion, Persona encargado) {
		setId(id);
		setUbicacion(ubicacion);
		setEncargado(encargado);
	}

	// GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public ArrayList<Producto> getProductos() {
		return productos;
	}
	
	public Persona getEncargado() {
		return encargado;
	}

	public void setEncargado(Persona encargado) {
		this.encargado = encargado;
	}

	// MÉTODOS DE INSTANCIA
	public void guardarProducto(Producto producto) {
		if(producto == null) {
			throw new IllegalArgumentException("No se admiten productos nulos");
		}
		
		productos.add(producto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(encargado, id, ubicacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Almacen other = (Almacen) obj;
		return Objects.equals(encargado, other.encargado) && Objects.equals(id, other.id)
				&& Objects.equals(ubicacion, other.ubicacion);
	}

	@Override
	public String toString() {
		return "Almacen [id=" + id + ", ubicacion=" + ubicacion + ", encargado=" + encargado + "]";
	}

}
