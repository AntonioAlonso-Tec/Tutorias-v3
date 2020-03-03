package org.iesalandalus.programacion.tutorias.mvc.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

public class Alumno implements Serializable {
	private static final String ER_NOMBRE = "([A-Zu00C0-\\u017F]{1}[a-zu00C0-\\u017F]+ )+([A-Zu00C0-\\u017F]{1}[a-zu00C0-\\u017F]+)";
	private static final String PREFIJO_EXPEDIENTE = "SP_";
	private static final String ER_CORREO = "([A-Za-z0-9]+[.]?)([A-Za-z0-9]+)[@]([A-Za-z0-9]+[.])([a-z]{2,3})";
	private static int ultimoIdentificador = 0;
	private String nombre;
	private String correo;
	private String expediente;

	public Alumno(String nombre, String correo) {
		setNombre(nombre);
		setCorreo(correo);
		incrementaUltimoModificador();
		setExpediente();
	}

	public Alumno(Alumno alumno) {
		if (alumno == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alumno nulo.");
		} else {
			setNombre(alumno.getNombre());
			setCorreo(alumno.getCorreo());
			setExpediente();
		}

	}

	public static Alumno getAlumnoFicticio(String correo) {
		String nombre = "Perico palotes";

		return new Alumno(nombre, correo);

	}

	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		String nombreConFormato;
		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else {
			nombreConFormato = formateaNombre(nombre);
		}
		if (nombreConFormato.trim().equals("") || !nombreConFormato.matches(ER_NOMBRE)) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		} else {
			this.nombre = nombreConFormato;
		}
	}

	private String formateaNombre(String nombre) {
		String nombreFormateado = "";

		if (nombre == null) {
			throw new NullPointerException("ERROR: El nombre no puede ser nulo.");
		} else if (nombre.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El nombre no tiene un formato válido.");
		} else {
			nombre = nombre.toLowerCase().trim();
			StringBuilder formato = new StringBuilder(nombre);

			for (int i = 1; i < formato.length(); i++) {
				if (Character.isLowerCase(formato.charAt(i)) && formato.charAt(i) > 1 && formato.charAt(i - 1) == ' ') {
					formato.setCharAt(i, Character.toUpperCase(formato.charAt(i)));
				}
			}
			if (Character.isLowerCase(formato.charAt(0))) {
				formato.setCharAt(0, Character.toUpperCase(formato.charAt(0)));
			}
			nombreFormateado = formato.toString().trim().replaceAll("\\s{2,}", " ");
			return nombreFormateado;
		}
	}

	public String getCorreo() {
		return correo;
	}

	private void setCorreo(String correo) {
		if (correo == null) {
			throw new NullPointerException("ERROR: El correo no puede ser nulo.");
		} else if (correo.trim().equals("")) {
			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		} else if (!correo.matches(ER_CORREO)) {

			throw new IllegalArgumentException("ERROR: El formato del correo no es válido.");
		} else {
			this.correo = correo;
		}
	}

	public String getExpediente() {
		return expediente;
	}

	private void setExpediente() {
		if (ultimoIdentificador < 1) {
			throw new IllegalArgumentException("ERROR: No puede existir menos de 1 alumno");
		} else {

			this.expediente = PREFIJO_EXPEDIENTE + getIniciales() + "_" + ultimoIdentificador;
		}
	}

	private String getIniciales() {
		nombre = formateaNombre(nombre);
		String iniciales = "";
		char inicial;

		if (nombre == null) {
			System.out.println("No hay nombre del que coger las iniciales");
		}

		for (int i = 0; i < nombre.length(); i++) {
			if (Character.isUpperCase(nombre.charAt(i))) {
				inicial = nombre.charAt(i);
				iniciales += inicial;
			}
		}
		return iniciales;
	}

	private static void incrementaUltimoModificador() {
		ultimoIdentificador++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(correo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Alumno)) {
			return false;
		}
		Alumno other = (Alumno) obj;
		return Objects.equals(correo, other.correo);
	}

	@Override
	public String toString() {
		return "nombre=" + nombre + " (" + getIniciales() + ")" + ", correo=" + correo + ", expediente=" + expediente;
	}

}
