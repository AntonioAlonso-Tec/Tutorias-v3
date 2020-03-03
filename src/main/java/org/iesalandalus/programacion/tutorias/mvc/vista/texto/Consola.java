package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.iesalandalus.programacion.tutorias.mvc.modelo.dominio.*;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private Consola() {

	}

	public static void mostrarMenu() {
		for (Opcion opcion : Opcion.values()) {
			System.out.println(opcion);

		}
	}

	public static void mostrarCabecera(String texto) {
		System.out.printf("%n%s%n", texto);
		String formato = "%0" + texto.length() + "d%n";
		System.out.println(String.format(formato, 0).replace("0", "-"));

	}

	public static int elegirOpcion() {
		int opcion;
		do {
			System.out.print("\nEscoge una opción");
			opcion = Entrada.entero();

		} while (!Opcion.esOrdinalValido(opcion));

		return opcion;
	}

	public static Alumno leerAlumno() {
		System.out.print("Introduce el nombre del alumno: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el correo electrónico del alumno: ");
		String correo = Entrada.cadena();

		return new Alumno(nombre, correo);

	}

	public static Alumno leerAlumnoFicticio() {
		System.out.print("Introduce el correo electrónico del alumno: ");
		return Alumno.getAlumnoFicticio(Entrada.cadena());
	}

	public static Profesor leerProfesor() {
		System.out.print("Introduce el nombre del profesor: ");
		String nombre = Entrada.cadena();
		System.out.print("Introduce el DNI del profesor: ");
		String dni = Entrada.cadena();
		System.out.print("Introduce el correo del profesor: ");
		String correo = Entrada.cadena();

		return new Profesor(nombre, dni, correo);
	}

	public static Profesor leerProfesorFicticio() {
		System.out.print("Introduce el DNI del profesor: ");
		return Profesor.getProfesorFicticio(Entrada.cadena());
	}

	public static Tutoria leerTutoria() {
		System.out.print("Introduce el nombre para la tutoría: ");
		String nombreTutoria = Entrada.cadena();

		return new Tutoria(leerProfesorFicticio(), nombreTutoria);
	}

	public static Sesion leerSesion() {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		Tutoria tutoria = leerTutoria();
		System.out.print("Introduce la fecha de la sesión: ");
		String fechaSesionTexto = Entrada.cadena();
		LocalDate fechaSesion = LocalDate.parse(fechaSesionTexto, formatoFecha);
		System.out.print("Introduce la hora de inicio de la sesión: ");
		String horaInicioSesionTexto = Entrada.cadena();
		LocalTime horaInicioSesion = LocalTime.parse(horaInicioSesionTexto, formatoHora);
		System.out.print("Introduce la hora de fin de la sesión: ");
		String horaFinSesionTexto = Entrada.cadena();
		LocalTime horaFinSesion = LocalTime.parse(horaFinSesionTexto, formatoHora);
		System.out.print("Introduce la duración de la sesión (En minutos) ");
		int duracionMinutos = Entrada.entero();

		return new Sesion(tutoria, fechaSesion, horaInicioSesion, horaFinSesion, duracionMinutos);
	}

	public static Sesion leerSesionFicticia() {
		DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Tutoria tutoria = leerTutoria();
		System.out.print("Introduce la fecha de la sesión: ");
		String fechaSesionTexto = Entrada.cadena();
		LocalDate fechaSesion = LocalDate.parse(fechaSesionTexto, formatoFecha);

		return new Sesion(Sesion.getSesionFicticia(tutoria, fechaSesion));
	}

	public static Cita leerCita() {
		Alumno alumno = leerAlumnoFicticio();
		Sesion sesion = leerSesionFicticia();
		DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		System.out.print("Introduce la hora de la cita: ");
		String horaCitaTexto = Entrada.cadena();
		LocalTime horaCita = LocalTime.parse(horaCitaTexto, formatoHora);

		return new Cita(alumno, sesion, horaCita);
	}
}
