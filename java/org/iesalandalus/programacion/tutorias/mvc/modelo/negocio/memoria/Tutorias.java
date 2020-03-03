package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.memoria;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;

public class Tutorias implements ITutorias {
	private List<Tutoria> coleccionTutorias;

	public Tutorias() {
		coleccionTutorias = new ArrayList<>();
	}

	public List<Tutoria> get() {
		List<Tutoria> tutoriasOrdenadas = copiaProfundaTutorias();
		Comparator<Profesor> comparaProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparaNombreTutoria = Comparator.comparing(Tutoria::getNombre);
		tutoriasOrdenadas
				.sort(Comparator.comparing(Tutoria::getProfesor, comparaProfesor).thenComparing(comparaNombreTutoria));
		return tutoriasOrdenadas;
	}

	private List<Tutoria> copiaProfundaTutorias() {
		List<Tutoria> copiaTutorias = new ArrayList<>();
		for (Tutoria tutoria : coleccionTutorias) {
			copiaTutorias.add(new Tutoria(tutoria));
		}

		return copiaTutorias;

	}

	public List<Tutoria> get(Profesor profesor) {

		if (profesor == null) {
			throw new NullPointerException("ERROR: El profesor no puede ser nulo.");
		}
		List<Tutoria> copiaPorProfesor = new ArrayList<>();
		for (Tutoria tutoria : coleccionTutorias) {
			if (tutoria.getProfesor().equals(profesor)) {
				copiaPorProfesor.add(new Tutoria(tutoria));
			}
		}
		Comparator<Tutoria> comparaNombreTutoria = Comparator.comparing(Tutoria::getNombre);
		copiaPorProfesor.sort(comparaNombreTutoria);
		return copiaPorProfesor;
	}

	public int getTamano() {
		return coleccionTutorias.size();
	}

	public void insertar(Tutoria tutoria) throws OperationNotSupportedException {
		int indice = coleccionTutorias.indexOf(tutoria);
		if (tutoria == null) {
			throw new NullPointerException("ERROR: No se puede insertar una tutoría nula.");
		}
		if (indice == -1) {
			coleccionTutorias.add(new Tutoria(tutoria));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una tutoría con ese identificador.");
		}
	}

	public Tutoria buscar(Tutoria tutoria) {
		int indice = coleccionTutorias.indexOf(tutoria);
		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una tutoría nula.");
		}

		if (indice == -1) {
			return null;
		} else {
			return new Tutoria(coleccionTutorias.get(indice));
		}

	}

	public void borrar(Tutoria tutoria) throws OperationNotSupportedException {
		int indice = coleccionTutorias.indexOf(tutoria);

		if (tutoria == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una tutoría nula.");
		}

		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna tutoría con ese identificador.");
		} else {
			coleccionTutorias.remove(indice);
		}

	}

}
