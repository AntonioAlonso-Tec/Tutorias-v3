package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface ITutorias {
	public List<Tutoria> get();
	public List<Tutoria> get(Profesor profesor);
	public int getTamano();
	public void insertar(Tutoria tutoria) throws OperationNotSupportedException;
	public Tutoria buscar(Tutoria tutoria);
	public void borrar(Tutoria tutoria) throws OperationNotSupportedException;
}
