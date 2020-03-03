package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Sesion implements Serializable{
	private static final LocalTime HORA_COMIENZO_CLASES = LocalTime.of(16, 00);
	private static final LocalTime HORA_FIN_CLASES = LocalTime.of(22, 15);
	public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalDate fecha;
	private LocalTime horaInicio;
	private LocalTime horaFin;
	private int minutosDuracion;
	private Tutoria tutoria;

	public Sesion(Tutoria tutoria, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int minutosDuracion) {
		setTutoria(tutoria);
		setFecha(fecha);
		setHoraInicio(horaInicio);
		setHoraFin(horaFin);
		setMinutosDuracion(minutosDuracion);
		comprobarValidezSesion();
	}

	public Sesion(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: No es posible copiar una sesión nula.");
		}
		setTutoria(sesion.getTutoria());
		setFecha(sesion.getFecha());
		setHoraInicio(sesion.getHoraInicio());
		setHoraFin(sesion.getHoraFin());
		setMinutosDuracion(sesion.getMinutosDuracion());
	}

	public LocalDate getFecha() {
		return fecha;
	}

	private void setFecha(LocalDate fecha) {

		if (fecha == null) {
			throw new NullPointerException("ERROR: La fecha no puede ser nula.");
		}
		if (fecha.compareTo(LocalDate.now().plusDays(1)) < 0) {
			throw new IllegalArgumentException("ERROR: Las sesiones de deben planificar para fechas futuras.");
		}
		this.fecha = fecha;

	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	private void setHoraInicio(LocalTime horaInicio) {
		if (horaInicio == null) {
			throw new NullPointerException("ERROR: La hora de inicio no puede ser nula.");
		}
		if (horaInicio.compareTo(HORA_COMIENZO_CLASES) < 0 || horaInicio.compareTo(HORA_FIN_CLASES) >= 0) {
			throw new IllegalArgumentException("ERROR: La hora de inicio no es válida.");
		}
		this.horaInicio = horaInicio;

	}

	public LocalTime getHoraFin() {
		return horaFin;
	}

	private void setHoraFin(LocalTime horaFin) {
		if (horaFin == null) {
			throw new NullPointerException("ERROR: La hora de fin no puede ser nula.");
		}
		if (horaFin.compareTo(HORA_COMIENZO_CLASES) < 0 || horaFin.compareTo(HORA_FIN_CLASES) > 0) {
			throw new IllegalArgumentException("ERROR: La hora de fin no es válida.");
		}
		this.horaFin = horaFin;

	}

	public int getMinutosDuracion() {
		return minutosDuracion;
	}

	private void setMinutosDuracion(int minutosDuracion) {
		if (minutosDuracion < 1) {
			throw new IllegalArgumentException("ERROR: Los minutos de duración no son válidos.");
		}
		this.minutosDuracion = minutosDuracion;

	}

	public Tutoria getTutoria() {
		return tutoria;
	}

	private void setTutoria(Tutoria tutoria) {
		if (tutoria == null) {
			throw new NullPointerException("ERROR: La tutoría no puede ser nula.");
		}
		this.tutoria = new Tutoria(tutoria);

	}

	private void comprobarValidezSesion() {

		Duration tiempoSesion = Duration.between(horaInicio, horaFin);
		int tiempoSesionMinutos = Math.toIntExact(tiempoSesion.toMinutes());

		if (horaFin.compareTo(horaInicio) == 0 || horaInicio.compareTo(horaFin) > 0) {
			throw new IllegalArgumentException("ERROR: Las hora para establecer la sesión no son válidas.");
		}

		if (tiempoSesionMinutos % minutosDuracion != 0) {
			throw new IllegalArgumentException(
					"ERROR: Los minutos de duración no es divisor de los minutos establecidos para toda la sesión.");
		}

	}

	public static Sesion getSesionFicticia(Tutoria tutoria, LocalDate fecha) {
		LocalTime horaInicio = LocalTime.of(16, 45);
		LocalTime horaFin = LocalTime.of(17, 00);
		int minutosDuracion = 15;

		return new Sesion(tutoria, fecha, horaInicio, horaFin, minutosDuracion);

	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, tutoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sesion)) {
			return false;
		}
		Sesion other = (Sesion) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(tutoria, other.tutoria);
	}

	@Override
	public String toString() {
		return String.format("tutoria=%s, fecha=%s, horaInicio=%s, horaFin=%s, minutosDuracion=%d", getTutoria(),
				getFecha().format(FORMATO_FECHA), getHoraInicio().format(FORMATO_HORA),
				getHoraFin().format(FORMATO_HORA), getMinutosDuracion());
	}

}
