package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ISesiones;

public class Sesiones implements ISesiones {
	private List<Sesion> coleccionSesiones;
	public static final String NOMBRE_FICHERO_SESIONES="datos/sesiones.dat";
	public Sesiones() {

		coleccionSesiones = new ArrayList<>();
	}
	
	public void comenzar() {
		File ficheroSesiones=new File(NOMBRE_FICHERO_SESIONES);
		try(ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(ficheroSesiones))){
			Sesion sesion=null;
			do {
				sesion=(Sesion) entrada.readObject();
				insertar(sesion);
			}while(sesion!=null);
		}catch(ClassNotFoundException e) {
			System.out.println("ERROR: No se encuentra la clase");
		}catch(FileNotFoundException e) {
			System.out.println("ERROR: No se encuentra el archivo");
		}catch(EOFException e) {
			System.out.println("Archivo leído satisfactoriamente");
		}catch(IOException e) {
			System.out.println("Error de entrada/salida del archivo");
		}catch(OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void terminar() {
		File ficheroSesiones=new File(NOMBRE_FICHERO_SESIONES);
		try(ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(ficheroSesiones))){
			for(Sesion sesion:coleccionSesiones)
				salida.writeObject(sesion);
			System.out.println("Archivo Alumnos escrito satisfactoriamente");
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo crear el archivo Sesiones");
		} catch (IOException e) {
			System.out.println("Error de entrada/salida del archivo");
		}
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
