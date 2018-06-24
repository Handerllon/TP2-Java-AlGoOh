package carta.trampa;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.CartaTrampa;

public class MagicCylinder extends CartaTrampa {

    public MagicCylinder(Jugador jugador, Jugador oponente) {
        super(jugador, oponente);
        this.nombre = "Magic Cylinder";
    }

    public void efecto(CartaMonstruo cartaAtacante) {
    	
    	oponente.disminuirPuntosVida(cartaAtacante.obtenerPuntosDeAtaque());

    }
    
    public void efecto() {
    	
    }
}
