package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita implements Serializable{
	public static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalTime hora;
	private Sesion sesion;
	private Alumno alumno;

	public Cita(Alumno alumno, Sesion sesion, LocalTime hora) {
		setAlumno(alumno);
		setSesion(sesion);
		setHora(hora);
	}

	public Cita(Cita cita) {
		if (cita == null) {
			throw new NullPointerException("ERROR: No es posible copiar una cita nula.");
		}
		setAlumno(cita.getAlumno());
		setSesion(cita.getSesion());
		setHora(cita.getHora());

	}

	public LocalTime getHora() {
		return hora;
	}

	private void setHora(LocalTime hora) {
		LocalTime horaInicioClases = LocalTime.parse("16:00", FORMATO_HORA);
		LocalTime horaFinClases = LocalTime.parse("22:15", FORMATO_HORA);
		if (hora == null) {
			throw new NullPointerException("ERROR: La hora no puede ser nula.");
		}
		if (hora.isBefore(horaInicioClases) || hora.isAfter(horaFinClases) || Math
				.toIntExact(Duration.between(hora, sesion.getHoraFin()).toMinutes()) < sesion.getMinutosDuracion()) {
			throw new IllegalArgumentException(
					"ERROR: La hora debe estar comprendida entre la hora de inicio y fin de la sesión.");
		}
		if (Duration.between(hora, sesion.getHoraFin()).toMinutes() % sesion.getMinutosDuracion() != 0) {
			throw new IllegalArgumentException(
					"ERROR: La hora debe comenzar en un múltiplo de los minutos de duración.");
		}
		if (sesion.getHoraInicio().isAfter(sesion.getHoraFin())) {
			throw new IllegalArgumentException("ERROR: La hora de inicio debe ir antes que la de Fin.");
		}

		this.hora = hora;

	}

	public Sesion getSesion() {
		return sesion;
	}

	private void setSesion(Sesion sesion) {
		if (sesion == null) {
			throw new NullPointerException("ERROR: La sesión no puede ser nula.");
		}
		this.sesion = new Sesion(sesion);

	}

	public Alumno getAlumno() {
		return alumno;
	}

	private void setAlumno(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: El alumno no puede ser nulo.");
		}
		this.alumno = new Alumno(alumno);

	}

	@Override
	public int hashCode() {
		return Objects.hash(alumno, hora, sesion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Cita)) {
			return false;
		}
		Cita other = (Cita) obj;
		return Objects.equals(alumno, other.alumno) && Objects.equals(hora, other.hora)
				&& Objects.equals(sesion, other.sesion);
	}

	@Override
	public String toString() {
		return String.format("alumno=%s, sesion=%s, hora=%s", getAlumno(), getSesion(), getHora().format(FORMATO_HORA));
	}

}
