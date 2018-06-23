package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;
import carta.excepciones.NoHayCartasParaSacrificarError;

public class GaiaTheFierceKnight extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 2100;
    private static int PUNTOS_ATAQUE = 2300;

    public GaiaTheFierceKnight(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE,jugador,oponente);
        this.estrellas = 7;
        this.nombre = "Gaia The Fierce Knight";
    }
    
    public void invocar(Sacrificio sacrificio){
    	
    	this.jugador.destruirMonstruo(sacrificio.getMonstruo());
    	this.jugador.destruirMonstruo(sacrificio.getMonstruo());
    	
    	this.jugador.jugarCarta(this);
    	
    }
    
    public void invocar(){
    	
    	throw new NoHayCartasParaSacrificarError();
    }
}
