package carta.campo;

import AlGoOh.Jugador;
import carta.CartaCampo;
import carta.CartaMonstruo;

public class Wasteland extends CartaCampo {
    private static int modificadorAtaque;
    private static int modificadorDefensa;
    private static Jugador jugador;
    private static Jugador oponente;

    public Wasteland(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        this.nombre = "Wasteland";

        //En region de jugador que juega la carta
        this.modificadorAtaque = 200;
        //En region de oponente
        this.modificadorDefensa = 300;

        this.jugador = jugador;
        this.oponente = oponente;

    }

	public void efecto(CartaMonstruo cartaMonstruo) {
		
		cartaMonstruo.wasteland(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
	}

	public void efecto() {
		
		this.jugador.wasteland(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
		this.oponente.wasteland(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
	}

	
	public void quitarEfecto(CartaMonstruo cartaMonstruo) {
		
		cartaMonstruo.quitarWasteland(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
	}
}
