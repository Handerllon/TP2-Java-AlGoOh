package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Efecto;

public class ExodiaTheForbiddenOne extends CartaMonstruo implements Efecto
{
    private static int PUNTOS_DEFENSA = 1000;
    private static int PUNTOS_ATAQUE = 1000;

    public ExodiaTheForbiddenOne()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 3;
        this.nombre = "Exodia The Forbidden One";
    }


	@Override
	public void efecto(Jugador jugador, Jugador oponente) {
		// TODO Auto-generated method stub
		
	}
}
