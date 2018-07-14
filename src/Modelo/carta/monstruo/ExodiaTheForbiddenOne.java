package Modelo.carta.monstruo;

import Modelo.Jugador;

public class ExodiaTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 1000;
    private static int PUNTOS_ATAQUE = 1000;
    private static String rutaImagen = "resources/imagenes/monstruo/ExodiaTheForbiddenOne.png";

    public ExodiaTheForbiddenOne(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 3;
        this.nombre = "Exodia The Forbidden One";
    }

    public boolean esParteExodia()
    {
        return true;
    }
}
