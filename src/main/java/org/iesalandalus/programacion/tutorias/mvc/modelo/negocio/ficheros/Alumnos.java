package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Alumnos implements IAlumnos {
	private List<Alumno> coleccionAlumnos;

	public Alumnos() {
		coleccionAlumnos = new ArrayList<>();
	}

	public List<Alumno> get() {
		List<Alumno> alumnosEnOrden = copiaProfundaAlumnos();
		alumnosEnOrden.sort(Comparator.comparing(Alumno::getCorreo));
		return alumnosEnOrden;

	}

	private List<Alumno> copiaProfundaAlumnos() {
		List<Alumno> copiaAlumnos = new ArrayList<>();
		for (Alumno alumno : coleccionAlumnos) {
			copiaAlumnos.add(new Alumno(alumno));
		}
		return copiaAlumnos;
	}

	public int getTamano() {
		return coleccionAlumnos.size();
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		int indice = coleccionAlumnos.indexOf(alumno);
		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		if (indice == -1) {
			coleccionAlumnos.add(new Alumno(alumno));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");
		}

	}

	public Alumno buscar(Alumno alumno) {
		int indice = coleccionAlumnos.indexOf(alumno);
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}

		if (indice == -1) {
			return null;
		} else {
			return new Alumno(coleccionAlumnos.get(indice));
		}

	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {

		int indice = coleccionAlumnos.indexOf(alumno);
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}

		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
		} else {
			coleccionAlumnos.remove(indice);
		}

	}

}
