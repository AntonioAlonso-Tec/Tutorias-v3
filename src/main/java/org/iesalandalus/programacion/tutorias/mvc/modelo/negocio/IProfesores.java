package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;

public interface IProfesores {
	public void comenzar();
	public void terminar();
	public List<Profesor> get();
	public int getTamano();
	public void insertar(Profesor profesor) throws OperationNotSupportedException;
	public Profesor buscar(Profesor profesor);
	public void borrar(Profesor profesor) throws OperationNotSupportedException;
}
