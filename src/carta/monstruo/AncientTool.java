package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;
import carta.excepciones.NoHayCartasParaSacrificarError;

public class AncientTool extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 1400;
    private static int PUNTOS_ATAQUE = 1700;

    public AncientTool(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente);
        this.estrellas = 5;
        this.nombre = "Ancient Tool";
    }
    
    public void invocar(Sacrificio sacrificio){
    	
    	this.jugador.destruirMonstruo(sacrificio.getMonstruo());
    	
    	this.jugador.jugarCarta(this);
    	
    }
    
    public void invocar(){
    	
    	throw new NoHayCartasParaSacrificarError();
    }
}
