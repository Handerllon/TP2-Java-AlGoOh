package controlador;

import AlGoOh.Jugador;
import interfaceJuego.InterfacePrincipal;
import javafx.application.Application;

public class Controlador {
	
	private Jugador jugador;
	private Jugador oponente;
	
	public Controlador(Jugador jugador, Jugador oponente) {
	
		this.jugador = jugador;
		this.oponente = oponente;
	
	}

	public static void main(String[] args) throws Exception{
		
		Application.launch(InterfacePrincipal.class, args);
	}
}
