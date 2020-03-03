package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;
import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;

public class VistaTexto implements IVista {
	private Controlador controlador;

	public VistaTexto() {
		Opcion.setVista(this);
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void comenzar() {
		Consola.mostrarCabecera("Gestión de la reserva de tutorías en IES Al-Ándalus");
		int opcionNum;
		do {
			Consola.mostrarMenu();
			opcionNum = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(opcionNum);
			opcion.ejecutar();
		} while (opcionNum != Opcion.SALIR.ordinal());
	}

	public void terminar() {
		controlador.terminar();
	}

	public void insertarAlumno() {
		Consola.mostrarCabecera("Insertar Alumno");
		try {
			controlador.insertar(Consola.leerAlumno());
			System.out.println("Alumno insertado con éxito");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarAlumno() {
		Consola.mostrarCabecera("Buscar Alumno");
		Alumno alumno;
		try {
			alumno = controlador.buscar(Consola.leerAlumnoFicticio());
			String msj;
			if (alumno != null) {
				msj = alumno.toString();
			} else {
				msj = "No existe este alumno";
			}
			System.out.println(msj);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlumno() {
		Consola.mostrarCabecera("Borrar Alumno");
		try {
			controlador.borrar(Consola.leerAlumnoFicticio());
			System.out.println("Alumno borrado satisfactoriamente");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlumnos() {
		Consola.mostrarCabecera("Mostrar Alumnos");
		List<Alumno> alumnos = controlador.getAlumnos();
		if (!alumnos.isEmpty()) {
			for (Alumno alumno : alumnos) {
				if (alumno != null) {
					System.out.println(alumno);
				}
			}
		} else {
			System.out.println("No hay alumnos disponibles");
		}
	}

	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		try {
			controlador.insertar(Consola.leerProfesor());
			System.out.println("Profesor insertado con éxito");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar Profesor");
		Profesor profesor;
		try {
			profesor = controlador.buscar(Consola.leerProfesorFicticio());
			String msj;
			if (profesor != null) {
				msj = profesor.toString();
			} else {
				msj = "Este profesor no existe";
			}
			System.out.println(msj);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			controlador.borrar(Consola.leerProfesorFicticio());
			System.out.println("Profesor borrado satisfactoriamente");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarProfesores() {
		Consola.mostrarCabecera("Mostrar Profesores");
		List<Profesor> profesores = controlador.getProfesores();
		if (!profesores.isEmpty()) {
			for (Profesor profesor : profesores) {
				if (profesor != null) {
					System.out.println(profesor);
				}
			}
		} else {
			System.out.println("No hay profesores disponibles");
		}
	}

	public void insertarTutoria() {
		Consola.mostrarCabecera("Insertar Tutoría");
		try {
			controlador.insertar(Consola.leerTutoria());
			System.out.println("Tutoría insertada con éxito");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarTutoria() {
		Consola.mostrarCabecera("Buscar Tutoría");
		Tutoria tutoria;
		try {
			tutoria = controlador.buscar(Consola.leerTutoria());
			String msj;
			if (tutoria != null) {
				msj = tutoria.toString();
			} else {
				msj = "Esta tutoría no existe";
			}
			System.out.println(msj);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarTutoria() {
		Consola.mostrarCabecera("Borrar Tutoría");
		try {
			controlador.borrar(Consola.leerTutoria());
			System.out.println("Tutoría borrada satisfactoriamente");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarTutorias() {
		Consola.mostrarCabecera("Mostrar Tutorías");
		List<Tutoria> tutorias = controlador.getTutorias();
		if (!tutorias.isEmpty()) {
			for (Tutoria tutoria : tutorias) {
				if (tutoria != null) {
					System.out.println(tutoria);
				}
			}
		} else {
			System.out.println("No hay tutorías disponibles");
		}
	}

	public void listarTutoriasProfesor() {
		Consola.mostrarCabecera("Mostrar Tutorías por Profesor");
		List<Tutoria> tutorias = controlador.getTutorias(Consola.leerProfesorFicticio());
		if (!tutorias.isEmpty()) {
			for (Tutoria tutoria : tutorias) {
				if (tutoria != null) {
					System.out.println(tutoria);
				}
			}
		} else {
			System.out.println("No hay tutorías disponibles a nombre de este Profesor");
		}
	}

	public void insertarSesion() {
		Consola.mostrarCabecera("Insertar Sesión");
		try {
			controlador.insertar(Consola.leerSesion());
			System.out.println("Sesión insertada con éxito");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarSesion() {
		Consola.mostrarCabecera("Buscar Tutoría");
		Sesion sesion;
		try {
			sesion = controlador.buscar(Consola.leerSesionFicticia());
			String msj;
			if (sesion != null) {
				msj = sesion.toString();
			} else {
				msj = "Esta sesión no existe";
			}
			System.out.println(msj);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarSesion() {
		Consola.mostrarCabecera("Borrar Sesión");
		try {
			controlador.borrar(Consola.leerSesionFicticia());
			System.out.println("Sesión borrada satisfactoriamente");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarSesiones() {
		Consola.mostrarCabecera("Mostrar Sesiones");
		List<Sesion> sesiones = controlador.getSesiones();
		if (!sesiones.isEmpty()) {
			for (Sesion sesion : sesiones) {
				if (sesion != null) {
					System.out.println(sesion);
				}
			}
		} else {
			System.out.println("No hay sesiones disponibles");
		}
	}

	public void listarSesionesTutoria() {
		Consola.mostrarCabecera("Mostrar Sesiones por Tutoría");
		List<Sesion> sesiones = controlador.getSesiones(Consola.leerTutoria());
		if (!sesiones.isEmpty()) {
			for (Sesion sesion : sesiones) {
				if (sesion != null) {
					System.out.println(sesion);
				}
			}
		} else {
			System.out.println("No hay sesiones disponibles a partir de esta tutoría");
		}
	}

	public void insertarCita() {
		Consola.mostrarCabecera("Insertar Cita");
		try {
			controlador.insertar(Consola.leerCita());
			System.out.println("Cita insertada con éxito");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

	}

	public void buscarCita() {
		Consola.mostrarCabecera("Buscar Cita");
		Cita cita;
		try {
			cita = controlador.buscar(Consola.leerCita());
			String msj;
			if (cita != null) {
				msj = cita.toString();
			} else {
				msj = "Esta cita no existe";
			}
			System.out.println(msj);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCita() {
		Consola.mostrarCabecera("Borrar Cita");
		try {
			controlador.borrar(Consola.leerCita());
			System.out.println("Cita borrada satisfactoriamente");
		} catch (OperationNotSupportedException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarCitas() {
		Consola.mostrarCabecera("Mostrar Cita");
		List<Cita> citas = controlador.getCitas();
		if (!citas.isEmpty()) {
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
		} else {
			System.out.println("No hay citas disponibles");
		}
	}

	public void listarCitasSesion() {
		Consola.mostrarCabecera("Mostrar Citas por Sesión");
		List<Cita> citas = controlador.getCitas(Consola.leerSesionFicticia());
		if (!citas.isEmpty()) {
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
		} else {
			System.out.println("No hay citas disponibles para esta Sesión");
		}
	}

	public void listarCitasAlumno() {
		Consola.mostrarCabecera("Mostrar Citas por Alumno");
		List<Cita> citas = controlador.getCitas(Consola.leerAlumnoFicticio());
		if (!citas.isEmpty()) {
			for (Cita cita : citas) {
				if (cita != null) {
					System.out.println(cita);
				}
			}
		} else {
			System.out.println("No hay citas disponibles para este Alumno");
		}
	}

}
