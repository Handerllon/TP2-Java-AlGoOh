package areaDeJuego;

import carta.Carta;
import carta.CartaTrampa;
import carta.CartaMagica;

public class RegionMagicasYTrampas extends Region<Carta> {

	public RegionMagicasYTrampas(int capacidadMaxima) {
		super(capacidadMaxima);
		
	}
	
	public CartaTrampa obtenerCartaTrampaAActivar(){
		
		CartaTrampa cartaTrampaBuscada = null;
		
		for (int i = 0 ; i<cartas.size(); i++){
			
			if (cartas.get(i) instanceof CartaTrampa ){
				
				cartaTrampaBuscada = (CartaTrampa) cartas.get(i);
				return cartaTrampaBuscada;
			}		
		}
		return cartaTrampaBuscada;
	}

}
