package org.iesalandalus.programacion.tutorias;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;

import org.iesalandalus.programacion.tutorias.mvc.modelo.FactoriaFuenteDatos;
import org.iesalandalus.programacion.tutorias.mvc.modelo.IModelo;
import org.iesalandalus.programacion.tutorias.mvc.modelo.Modelo;
import org.iesalandalus.programacion.tutorias.mvc.vista.FactoriaVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.IVista;
import org.iesalandalus.programacion.tutorias.mvc.vista.texto.VistaTexto;

public class MainApp {

	public static void main(String[] args) {
		IModelo modelo = new Modelo(FactoriaFuenteDatos.FICHEROS.crear());
		IVista vista = FactoriaVista.TEXTO.crear();
		Controlador controlador = new Controlador(vista, modelo);
		controlador.comenzar();
	}

}
