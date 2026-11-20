package com.ipartek.formacion.oop.pojos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Producto {
	// CONSTANTES
	private static final String NOMBRE_POR_DEFECTO = "SIN NOMBRE";
	private static final BigDecimal PRECIO_POR_DEFECTO = BigDecimal.ZERO;

	// VARIABLES DE INSTANCIA
	private Long id;
	private String nombre;
	private BigDecimal precio;

	// CONSTRUCTORES
	public Producto(Long id, String nombre, BigDecimal precio) {
		setId(id);
		setNombre(nombre);
		setPrecio(precio);
	}

	public Producto(String nombre, BigDecimal precio) {
		this(null, nombre, precio);
	}

	public Producto() {
		this(null, NOMBRE_POR_DEFECTO, PRECIO_POR_DEFECTO);
	}

	// Constructor de copia
	public Producto(Producto producto) {
		this(producto.getId(), producto.getNombre(), producto.getPrecio());
	}

	// GETTERS Y SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws IllegalArgumentException {
		if (nombre == null || nombre.isBlank()) {
			throw new IllegalArgumentException("El nombre es obligatorio");
		}

		this.nombre = nombre.trim();
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) throws IllegalArgumentException {
		if (precio.compareTo(PRECIO_POR_DEFECTO) < 0) {
			throw new IllegalArgumentException("El precio debe ser 0 o más");
		}

		this.precio = precio.setScale(2, RoundingMode.HALF_UP);
	}

	public void setPrecio(double d) {
		setPrecio(BigDecimal.valueOf(d));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nombre, precio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(precio, other.precio);
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}

}
