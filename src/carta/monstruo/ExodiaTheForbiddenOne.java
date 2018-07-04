package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

public class ExodiaTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 1000;
    private static int PUNTOS_ATAQUE = 1000;

    public ExodiaTheForbiddenOne(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 3;
        this.nombre = "Exodia The Forbidden One";
    }
}
