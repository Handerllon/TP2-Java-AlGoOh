package carta.campo;

import AlGoOh.Jugador;
import carta.CartaCampo;
import carta.CartaMonstruo;

public class Sogen extends CartaCampo {
    private static int modificadorAtaque;
    private static int modificadorDefensa;
    private static Jugador jugador;
    private static Jugador oponente;

    public Sogen(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        this.nombre = "Sogen";

        //En region de jugador que juega la carta
        this.modificadorDefensa = 500;
        //En region del oponente
        this.modificadorAtaque = 200;

        this.jugador = jugador;
        this.oponente = oponente;
    }
    
    public void efecto(CartaMonstruo cartaMonstruo) {
		
		cartaMonstruo.sogen(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
	}

	public void efecto() {
		
		this.jugador.sogen(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
		this.oponente.sogen(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
	}

	
	public void quitarEfecto(CartaMonstruo cartaMonstruo) {
		
		cartaMonstruo.quitarSogen(this.modificadorAtaque,this.modificadorDefensa,this.jugador);
	}
}

