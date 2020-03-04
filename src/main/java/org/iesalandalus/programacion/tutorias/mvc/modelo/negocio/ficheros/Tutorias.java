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
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ITutorias;

public class Tutorias implements ITutorias {
	private List<Tutoria> coleccionTutorias;
	public static final String NOMBRE_ARCHIVO_TUTORIAS="datos/tutorias.dat";
	public Tutorias() {
		coleccionTutorias = new ArrayList<>();
	}

	public void comenzar() {
		File ficheroTutorias=new File(NOMBRE_ARCHIVO_TUTORIAS);
		try(ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(ficheroTutorias))){
			Tutoria tutoria=null;
			do {
				tutoria=(Tutoria) entrada.readObject();
				insertar(tutoria);
			}while(tutoria!=null);
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
		File ficheroTutorias=new File(NOMBRE_ARCHIVO_TUTORIAS);
		try(ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(ficheroTutorias))){
			for(Tutoria tutoria:coleccionTutorias)
				salida.writeObject(tutoria);
			System.out.println("Archivo Alumnos escrito satisfactoriamente");
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo crear el archivo Tutorias");
		} catch (IOException e) {
			System.out.println("rror de entrada/salida del archivo");
		}
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
