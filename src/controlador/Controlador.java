package controlador;

import AlGoOh.Jugador;
import areaDeJuego.RegionMonstruos;
import carta.CartaMonstruo;
import carta.FabricaCartas;

public class Controlador {
	
	private Jugador jugador;
	private Jugador oponente;
	
	
	public void establecerJugadores(Jugador jugador, Jugador oponente){
		
		this.jugador = jugador;
		this.oponente = oponente;
		
	}


	public RegionMonstruos obtenerRegionMonstruosJugador() {
		
		return this.jugador.obtenerRegionMonstruos();
	}


	public RegionMonstruos obtenerRegionMonstruosOponente() {
		
		return this.oponente.obtenerRegionMonstruos();
	}
}
