package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Cita;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Profesor;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Sesion;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Tutoria;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ICitas;

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

public class Citas implements ICitas{
	private List<Cita> coleccionCitas;
	public static final String NOMBRE_FICHERO_CITAS="datos/citas.dat";

	public Citas() {
		coleccionCitas = new ArrayList<>();
	}

	public void comenzar() {
		File ficheroCitas=new File(NOMBRE_FICHERO_CITAS);
		try(ObjectInputStream entrada=new ObjectInputStream(new FileInputStream(ficheroCitas))){
			Cita cita=null;
			do {
				cita=(Cita) entrada.readObject();
				insertar(cita);
			}while(cita!=null);
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
		File ficheroCitas=new File(NOMBRE_FICHERO_CITAS);
		try(ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(ficheroCitas))){
			for(Cita cita:coleccionCitas)
				salida.writeObject(cita);
			System.out.println("Archivo Alumnos escrito satisfactoriamente");
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo crear el archivo Citas");
		} catch (IOException e) {
			System.out.println("rror de entrada/salida del archivo");
		}
	}
	
	public List<Cita> get() {

		List<Cita> citasOrdenadas = copiaProfundaCitas();
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor)
				.thenComparing(Tutoria::getNombre);
		Comparator<Alumno> comparadorAlumno = Comparator.comparing(Alumno::getExpediente);
		Comparator<Sesion> comparadorSesion = Comparator.comparing(Sesion::getTutoria, comparadorTutoria)
				.thenComparing(Sesion::getFecha);
		citasOrdenadas.sort(Comparator.comparing(Cita::getSesion, comparadorSesion)
				.thenComparing(Cita::getAlumno, comparadorAlumno).thenComparing(Cita::getHora));
		return citasOrdenadas;

	}

	public List<Cita> get(Alumno alumno) {

		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}

		List<Cita> copiaPorAlumno = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			if (cita.getAlumno().equals(alumno)) {
				copiaPorAlumno.add(new Cita(cita));
			}
		}
		Comparator<Profesor> comparadorProfesor = Comparator.comparing(Profesor::getDni);
		Comparator<Tutoria> comparadorTutoria = Comparator.comparing(Tutoria::getProfesor, comparadorProfesor)
				.thenComparing(Tutoria::getNombre);
		Comparator<Sesion> comparadorSesion = Comparator.comparing(Sesion::getTutoria, comparadorTutoria)
				.thenComparing(Sesion::getFecha);
		Comparator<Cita> comparadorHora = Comparator.comparing(Cita::getHora);
		copiaPorAlumno.sort(Comparator.comparing(Cita::getSesion, comparadorSesion).thenComparing(comparadorHora));
		return copiaPorAlumno;
	}

	public List<Cita> get(Sesion sesion) {

		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}

		List<Cita> copiaPorSesion = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			if (cita.getSesion().equals(sesion)) {
				copiaPorSesion.add(new Cita(cita));
			}
		}
		Comparator<Cita> comparadorHora = Comparator.comparing(Cita::getHora);
		copiaPorSesion.sort(comparadorHora);
		return copiaPorSesion;

	}

	private List<Cita> copiaProfundaCitas() {
		List<Cita> copiaCitas = new ArrayList<>();
		for (Cita cita : coleccionCitas) {
			copiaCitas.add(new Cita(cita));
		}

		return copiaCitas;

	}

	public int getTamano() {
		return coleccionCitas.size();
	}

	public void insertar(Cita cita) throws OperationNotSupportedException {
		int indice = coleccionCitas.indexOf(cita);
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}

		if (indice == -1) {
			coleccionCitas.add(new Cita(cita));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita con esa hora.");
		}
	}

	public Cita buscar(Cita cita) {
		int indice = coleccionCitas.indexOf(cita);
		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar una cita nula.");
		}

		if (indice == -1) {
			return null;
		} else {
			return new Cita(coleccionCitas.get(indice));
		}

	}

	public void borrar(Cita cita) throws OperationNotSupportedException {
		int indice = coleccionCitas.indexOf(cita);

		if (cita == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
		}

		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ninguna cita con esa hora.");
		} else {
			coleccionCitas.remove(indice);
		}

	}

}
