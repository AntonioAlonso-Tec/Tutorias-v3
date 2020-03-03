package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.memoria;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;

public class Sesiones implements ISesiones {

	private List<Sesion> coleccionSesiones;

	public Sesiones() {

		coleccionSesiones = new ArrayList<>();
	}

	public List<Sesion> get() {
		List<Sesion> sesionesOrdenadas = copiaProfundaSesiones();
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor)
				.thenComparing(Tutoria::getNombre);
		sesionesOrdenadas
				.sort(Comparator.comparing(Sesion::getTutoria, comparadorTutoria).thenComparing(Sesion::getFecha));

		return sesionesOrdenadas;
	}

	private List<Sesion> copiaProfundaSesiones() {

		List<Sesion> copiaSesiones = new ArrayList<>();
		for (Sesion sesion : coleccionSesiones) {
			copiaSesiones.add(new Sesion(sesion));
		}

		return copiaSesiones;

	}

	public List<Sesion> get(Tutoria tutoria) {

		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		List<Sesion> copiaPorTutoria = new ArrayList<>();
		for (Sesion sesion : coleccionSesiones) {
			if (sesion.getTutoria().equals(tutoria)) {
				copiaPorTutoria.add(new Sesion(sesion));
			}
		}
		Comparator<Sesion> comparadorFecha = Comparator.comparing(Sesion::getFecha);
		copiaPorTutoria.sort(comparadorFecha);
		return copiaPorTutoria;
	}

	public int getTamano() {
		return coleccionSesiones.size();
	}

	public void insertar(Sesion sesion) throws OperationNotSupportedException {
		int indice = coleccionSesiones.indexOf(sesion);
		if (sesion == null) {
			throw new NullPointerException("ERROR: No se puede insertar una sesión nula.");
		}
		if (indice == -1) {
			coleccionSesiones.add(new Sesion(sesion));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una sesión con esa fecha.");
		}
	}

	public Sesion buscar(Sesion sesion) {
		int indice = coleccionSesiones.indexOf(sesion);
		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una sesión nula.");
		}
		if (indice == -1) {
			return null;
		} else {
			return new Sesion(coleccionSesiones.get(indice));
		}

	}

	public void borrar(Sesion sesion) throws OperationNotSupportedException {
		int indice = coleccionSesiones.indexOf(sesion);

		if (sesion == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una sesión nula.");
		}
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna sesión con esa fecha.");
		} else {
			coleccionSesiones.remove(indice);
		}

	}

}
