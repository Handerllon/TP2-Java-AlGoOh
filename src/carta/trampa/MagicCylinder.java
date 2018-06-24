package carta.trampa;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.CartaTrampa;

public class MagicCylinder extends CartaTrampa {

    public MagicCylinder(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        this.nombre = "Magic Cylinder";
        this.trampaCancelaAtaqueAMonstruo = true;
    }

    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaOponente) {
    	
    	oponente.disminuirPuntosVida(cartaAtacante.obtenerPuntosDeAtaque());

    }
    
    public void efecto() {
    	
    }
}
