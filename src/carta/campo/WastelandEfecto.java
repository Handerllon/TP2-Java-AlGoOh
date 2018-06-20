package carta.campo;

import AlGoOh.Jugador;
import carta.Efecto;

public class WastelandEfecto implements Efecto{

	private static int modificadorAtaque;
	private static int modificadorDefensa;
	
	public WastelandEfecto(){
		
		//En region de jugador que juega la carta
		this.modificadorAtaque = 200;
		//En region de oponente
		this.modificadorDefensa = 300;
	}
	
	@Override
	public void efecto(Jugador jugador, Jugador oponente) {
		
		//TODO: Aqui se debe modificar la region de monstruos de cada uno de los jugadores
	}

}
