package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface ISesiones {
	public void comenzar();
	public void terminar();
	public List<Sesion> get();
	public List<Sesion> get(Tutoria tutoria);
	public int getTamano();
	public void insertar(Sesion sesion) throws OperationNotSupportedException;
	public Sesion buscar(Sesion sesion);
	public void borrar(Sesion sesion) throws OperationNotSupportedException;
}
