package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;

public interface IAlumnos {
	public void comenzar();
	public void terminar();
	public List<Alumno> get();
	public int getTamano();
	public void insertar(Alumno alumno) throws OperationNotSupportedException;
	public Alumno buscar(Alumno alumno);
	public void borrar(Alumno alumno) throws OperationNotSupportedException;
}
