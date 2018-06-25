package areaDeJuego;

import AlGoOh.Jugador;
import carta.CartaCampo;
import carta.CartaMonstruo;

public class RegionCampo extends Region<CartaCampo> {

	public RegionCampo(int capacidadMaxima) {
		super(capacidadMaxima);
	}

	public void aplicarEfecto(CartaMonstruo cartaMonstruo) {
		
		if (!cartas.isEmpty())
			this.cartas.getFirst().efecto(cartaMonstruo);
		
	}
	
	public void quitarEfecto(CartaMonstruo cartaMonstruo){
		
		if (!cartas.isEmpty())
			this.cartas.getFirst().quitarEfecto(cartaMonstruo);
	}

}
