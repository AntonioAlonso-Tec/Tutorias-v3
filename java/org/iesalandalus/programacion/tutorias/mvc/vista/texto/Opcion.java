package org.iesalandalus.programacion.tutorias.mvc.vista.texto;

public enum Opcion {
	INSERTAR_ALUMNO("Insertar Alumno") {
		public void ejecutar() {
			vista.insertarAlumno();
		}
	},
	BUSCAR_ALUMNO("Buscar Alumno") {
		public void ejecutar() {
			vista.buscarAlumno();
		}
	},
	BORRAR_ALUMNO("Borrar Alumno") {
		public void ejecutar() {
			vista.borrarAlumno();
		}
	},
	LISTAR_ALUMNOS("Listar Alumnos") {
		public void ejecutar() {
			vista.listarAlumnos();
		}
	},
	INSERTAR_PROFESOR("Insertar Profesor") {
		public void ejecutar() {
			vista.insertarProfesor();
		}
	},
	BUSCAR_PROFESOR("Buscar Profesor") {
		public void ejecutar() {
			vista.buscarProfesor();
		}
	},
	BORRAR_PROFESOR("Borrar Profesor") {
		public void ejecutar() {
			vista.borrarProfesor();
		}
	},
	LISTAR_PROFESORES("Listar Profesores") {
		public void ejecutar() {
			vista.listarProfesores();
		}
	},
	INSERTAR_TUTORIA("Insertar Tutoría") {
		public void ejecutar() {
			vista.insertarTutoria();
		}
	},
	BUSCAR_TUTORIA("Buscar Tutoría") {
		public void ejecutar() {
			vista.buscarTutoria();
		}
	},
	BORRAR_TUTORIA("Borrar Tutoría") {
		public void ejecutar() {
			vista.borrarTutoria();
		}
	},
	LISTAR_TUTORIAS("Listar Tutorías") {
		public void ejecutar() {
			vista.listarTutorias();
		}
	},
	LISTAR_TUTORIAS_PROFESOR("Listar Tutorías por Profesor") {
		public void ejecutar() {
			vista.listarTutoriasProfesor();
		}
	},
	INSERTAR_SESION("Insertar Sesión") {
		public void ejecutar() {
			vista.insertarSesion();
		}
	},
	BUSCAR_SESION("Buscar Sesión") {
		public void ejecutar() {
			vista.buscarSesion();
		}
	},
	BORRAR_SESION("Borrar Sesión") {
		public void ejecutar() {
			vista.borrarSesion();
		}
	},
	LISTAR_SESION("Listar Sesiones") {
		public void ejecutar() {
			vista.listarSesiones();
		}
	},
	LISTAR_SESIONES_TUTORIA("Listar Sesiones por Tutoría") {
		public void ejecutar() {
			vista.listarSesionesTutoria();
		}
	},
	INSERTAR_CITA("Insertar Cita") {
		public void ejecutar() {
			vista.insertarCita();
		}
	},
	BUSCAR_CITA("Buscar Cita") {
		public void ejecutar() {
			vista.buscarCita();
		}
	},
	BORRAR_CITA("Borrar Cita") {
		public void ejecutar() {
			vista.borrarCita();
		}
	},
	LISTAR_CITAS("Listar Citas") {
		public void ejecutar() {
			vista.listarCitas();
		}
	},
	LISTAR_CITAS_SESION("Listar Citas por Sesión") {
		public void ejecutar() {
			vista.listarCitasSesion();
		}
	},
	LISTAR_CITAS_ALUMNO("Listar Citas por Alumno") {
		public void ejecutar() {
			vista.listarCitasAlumno();
		}
	},
	SALIR("Salir") {
		public void ejecutar() {
			vista.terminar();
		}
	};

	private static VistaTexto vista;
	private String texto;

	private Opcion(String texto) {
		this.texto = texto;
	}

	public abstract void ejecutar();

	protected static void setVista(VistaTexto vista) {
		Opcion.vista = vista;
	}

	public static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal <= values().length - 1);
	}

	public static Opcion getOpcionSegunOrdinal(int ordinal) {
		if (esOrdinalValido(ordinal)) {
			return values()[ordinal];
		} else {
			throw new IllegalArgumentException("ERROR: Número de opción no válido");
		}
	}

	@Override
	public String toString() {
		return String.format("%d.- %s", ordinal(), texto);
	}

}
