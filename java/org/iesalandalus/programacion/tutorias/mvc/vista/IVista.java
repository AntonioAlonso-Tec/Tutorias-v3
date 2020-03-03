package org.iesalandalus.programacion.tutorias.mvc.vista;

import org.iesalandalus.programacion.tutorias.mvc.controlador.Controlador;

public interface IVista {
	public void setControlador(Controlador controlador);

	public void comenzar();

	public void terminar();
}
