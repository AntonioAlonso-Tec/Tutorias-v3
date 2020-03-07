package org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.ficheros;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.Alumno;
import org.iesalandalus.programacion.tutorias.mvc.modelo.negocio.IAlumnos;

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

public class Alumnos implements IAlumnos {
	private List<Alumno> coleccionAlumnos;
	public static final String NOMBRE_FICHERO_ALUMNOS = "datos/alumnos.dat";

	public Alumnos() {
		coleccionAlumnos = new ArrayList<>();
	}

	public void comenzar() {
		File ficheroAlumnos = new File(NOMBRE_FICHERO_ALUMNOS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAlumnos))) {
			Alumno alumno = null;
			String numExpedienteString = "";
			String numExpStringAnterior = "";
			int numExpediente = 0;
			int numExpedienteAnterior = 0;
			do {
				alumno = (Alumno) entrada.readObject();
				insertar(alumno);
				if (alumno != null) {
					for (int i = 0; i < alumno.getExpediente().length(); i++) {
						if (Character.isDigit(alumno.getExpediente().charAt(i))) {
							numExpedienteString += alumno.getExpediente().charAt(i);
						}
					}
					numExpediente = Integer.parseInt(numExpedienteString);
					if (coleccionAlumnos.get(coleccionAlumnos.size() - 1) != null) {
						for (int i = 0; i < coleccionAlumnos.size() - 1; i++) {
							if (Character.isDigit(alumno.getExpediente().charAt(i))) {
								numExpStringAnterior += alumno.getExpediente().charAt(i);
							}
						}
						numExpedienteAnterior = Integer.parseInt(numExpStringAnterior);
					}
					if (numExpediente > numExpedienteAnterior) {
						Alumno.modificaIdentificador(numExpediente);
					} else {
						numExpediente = numExpedienteAnterior + 1;
						Alumno.modificaIdentificador(numExpediente);
					}
				}

				// if(alumno!=null) {
				// alumno.comprobarModificador();
				// }
			} while (alumno != null);
		} catch (ClassNotFoundException e) {
			System.out.println("ERROR: No se encuentra la clase");
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: No se encuentra el archivo");
		} catch (EOFException e) {
			System.out.println("Archivo leído satisfactoriamente");
		} catch (IOException e) {
			System.out.println("Error de entrada/salida del archivo");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void terminar() {
		File ficheroAlumnos = new File(NOMBRE_FICHERO_ALUMNOS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAlumnos))) {
			for (Alumno alumno : coleccionAlumnos)
				salida.writeObject(alumno);
			System.out.println("Archivo Alumnos escrito satisfactoriamente");
		} catch (FileNotFoundException e) {
			System.out.println("No se pudo crear el archivo Alumnos");
		} catch (IOException e) {
			System.out.println("Error de entrada/salida del archivo");
		}
	}

	public List<Alumno> get() {
		List<Alumno> alumnosEnOrden = copiaProfundaAlumnos();
		alumnosEnOrden.sort(Comparator.comparing(Alumno::getCorreo));
		return alumnosEnOrden;

	}

	private List<Alumno> copiaProfundaAlumnos() {
		List<Alumno> copiaAlumnos = new ArrayList<>();
		for (Alumno alumno : coleccionAlumnos) {
			copiaAlumnos.add(new Alumno(alumno));
		}
		return copiaAlumnos;
	}

	public int getTamano() {
		return coleccionAlumnos.size();
	}

	public void insertar(Alumno alumno) throws OperationNotSupportedException {
		int indice = coleccionAlumnos.indexOf(alumno);
		if (alumno == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alumno nulo.");
		}

		if (indice == -1) {
			coleccionAlumnos.add(new Alumno(alumno));
		} else {
			throw new OperationNotSupportedException("ERROR: Ya existe un alumno con ese expediente.");
		}

	}

	public Alumno buscar(Alumno alumno) {
		int indice = coleccionAlumnos.indexOf(alumno);
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede buscar un alumno nulo.");
		}

		if (indice == -1) {
			return null;
		} else {
			return new Alumno(coleccionAlumnos.get(indice));
		}

	}

	public void borrar(Alumno alumno) throws OperationNotSupportedException {

		int indice = coleccionAlumnos.indexOf(alumno);
		if (alumno == null) {
			throw new IllegalArgumentException("ERROR: No se puede borrar un alumno nulo.");
		}

		if (indice == -1) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alumno con ese expediente.");
		} else {
			coleccionAlumnos.remove(indice);
		}

	}

}
