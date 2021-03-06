package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Tutoria implements Serializable{
	private String nombre;
	private Profesor profesor;

	public Tutoria(Profesor profesor, String nombre) {

		setProfesor(profesor);
		setNombre(nombre);
	}

	public Tutoria(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: No es posible copiar una tutoría nula.");
		}
		setProfesor(tutoria.getProfesor());
		setNombre(tutoria.getNombre());
		new Tutoria(tutoria.getProfesor(), tutoria.getNombre());

	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		} else {
			this.nombre = nombre.trim();
		}
	}

	public Profesor getProfesor() {
		return profesor;
	}

	private void setProfesor(Profesor profesor) {
		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		} else {
			this.profesor = new Profesor(profesor);
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, profesor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Tutoria)) {
			return false;
		}
		Tutoria other = (Tutoria) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(profesor, other.profesor);
	}

	@Override
	public String toString() {
		return "profesor=" + profesor + ", nombre=" + nombre;
	}

}
