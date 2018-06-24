package carta.trampa;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.CartaTrampa;

public class Reinforcements extends CartaTrampa {

    public Reinforcements(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        this.nombre = "Reinforcements";
    }

	public void efecto() {
		
	}
	
	public void efecto(CartaMonstruo cartaMonstruoJugador, CartaMonstruo cartaMonstruoOponente) {
		
		cartaMonstruoOponente.reinforcements();
		
	}
}
