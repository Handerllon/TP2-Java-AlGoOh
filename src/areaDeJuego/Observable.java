package areaDeJuego;

import vistaJuego.Observador;

public interface Observable {

	
	void subscribir(Observador observer);
	
	void desubscribir(Observador observer);
	
	void notificar();
	
}
