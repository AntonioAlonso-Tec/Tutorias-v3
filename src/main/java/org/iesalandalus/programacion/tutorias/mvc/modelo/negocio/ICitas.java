package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;

public interface ICitas {
	public void comenzar();
	public void terminar();
	public List<Cita> get();
	public List<Cita> get(Sesion sesion);
	public List<Cita> get(Alumno alumno);
	public int getTamano();
	public void insertar(Cita cita) throws OperationNotSupportedException;
	public Cita buscar(Cita cita);
	public void borrar(Cita cita) throws OperationNotSupportedException;
}
