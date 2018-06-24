package carta;

import AlGoOh.Jugador;

public abstract class CartaTrampa extends Carta implements Efecto {
	
	protected boolean trampaCancelaAtaqueAMonstruo;
	
    public CartaTrampa(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        trampaCancelaAtaqueAMonstruo = false;
    }
    
    public boolean trampaCancelaAtaqueAMonstruo(){
    	
    	return trampaCancelaAtaqueAMonstruo;
    }
    
    public void efecto (CartaMonstruo cartaMonstruoJugador, CartaMonstruo cartaMonstruoOponente){}
}
