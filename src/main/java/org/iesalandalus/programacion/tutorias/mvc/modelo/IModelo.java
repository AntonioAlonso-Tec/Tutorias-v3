package org.iesalandalus.programacion.tutorias.mvc.modelo;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;

public interface IModelo {
	public void insertar(Alumno alumno) throws OperationNotSupportedException;

	public void insertar(Profesor profesor) throws OperationNotSupportedException;

	public void insertar(Tutoria tutoria) throws OperationNotSupportedException;

	public void insertar(Sesion sesion) throws OperationNotSupportedException;

	public void insertar(Cita cita) throws OperationNotSupportedException;

	public Alumno buscar(Alumno alumno);

	public Profesor buscar(Profesor profesor);

	public Tutoria buscar(Tutoria tutoria);
	public Sesion buscar(Sesion sesion);

	public Cita buscar(Cita cita);

	public void borrar(Alumno alumno) throws OperationNotSupportedException;
	public void borrar(Profesor profesor) throws OperationNotSupportedException;

	public void borrar(Tutoria tutoria) throws OperationNotSupportedException;

	public void borrar(Sesion sesion) throws OperationNotSupportedException;

	public void borrar(Cita cita) throws OperationNotSupportedException;
	public List<Alumno> getAlumnos();
	public List<Profesor> getProfesores();

	public List<Tutoria> getTutorias();

	public List<Tutoria> getTutorias(Profesor profesor);

	public List<Sesion> getSesiones();

	public List<Sesion> getSesiones(Tutoria tutoria);

	public List<Cita> getCitas();

	public List<Cita> getCitas(Sesion sesion);

	public List<Cita> getCitas(Alumno alumno);
}
