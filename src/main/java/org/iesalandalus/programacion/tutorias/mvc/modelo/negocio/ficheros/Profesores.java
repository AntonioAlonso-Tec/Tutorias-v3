package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IProfesores;

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

public class Profesores implements IProfesores {

	private List<Profesor> coleccionProfesores;
	public static final String NOMBRE_FICHERO_PROFESORES="datos/profesores.dat";

	public Profesores() {
		coleccionProfesores = new ArrayList<>();
	}

	public void comenzar() {
		File ficheroProfesores=new File(NOMBRE_FICHERO_PROFESORES);
		try(ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(ficheroProfesores))){
			Profesor profesor=null;
			do {
				profesor=(Profesor) entrada.readObject();
				insertar(profesor);
			}while(profesor!=null);
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
		File ficheroProfesores=new File(NOMBRE_FICHERO_PROFESORES);
		try(ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(ficheroProfesores))){
			for(Profesor profesor:coleccionProfesores)
				salida.writeObject(profesor);
			System.out.println("Archivo Alumnos escrito satisfactoriamente");
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo crear el archivo Profesores");
		} catch (IOException e) {
			System.out.println("rror de entrada/salida del archivo");
		}
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
