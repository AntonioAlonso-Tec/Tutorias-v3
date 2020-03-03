package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.memoria;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Profesores implements IProfesores {

	private List<Profesor> coleccionProfesores;

	public Profesores() {
		coleccionProfesores = new ArrayList<>();
	}

	public List<Profesor> get() {
		List<Profesor> profEnOrden = copiaProfundaProfesores();
		profEnOrden.sort(Comparator.comparing(Profesor::getDni));
		return profEnOrden;
	}

	private List<Profesor> copiaProfundaProfesores() {
		List<Profesor> copiaProfesores = new ArrayList<>();
		for (Profesor profesor : coleccionProfesores) {
			copiaProfesores.add(new Profesor(profesor));
		}

		return copiaProfesores;

	}

	public int getTamano() {
		return coleccionProfesores.size();
	}

	public void insertar(Profesor profesor) throws OperationNotSupportedException {
		int indice = coleccionProfesores.indexOf(profesor);

		if (profesor == null) {
			throw new NullPointerException("ERROR: No se puede insertar un profesor nulo.");
		}

		if (indice == -1) {
			coleccionProfesores.add(new Profesor(profesor));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un profesor con ese DNI.");
		}
	}

	public Profesor buscar(Profesor profesor) {
		int indice = coleccionProfesores.indexOf(profesor);
		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un profesor nulo.");
		}

		if (indice == -1) {
			return null;
		} else {
			return new Profesor(coleccionProfesores.get(indice));
		}

	}

	public void borrar(Profesor profesor) throws OperationNotSupportedException {
		int indice = coleccionProfesores.indexOf(profesor);

		if (profesor == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un profesor nulo.");
		}
		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún profesor con ese DNI.");
		} else {
			coleccionProfesores.remove(indice);
		}

	}

}
