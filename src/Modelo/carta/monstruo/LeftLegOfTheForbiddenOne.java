package Modelo.carta.monstruo;

import Modelo.Jugador;

public class LeftLegOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;
    private static String rutaImagen = "resources/imagenes/monstruo/LeftLegOfTheForbiddenOne.png";

    public LeftLegOfTheForbiddenOne(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 1;
        this.nombre = "Left Leg Of The Forbidden One";
    }

    public boolean esParteExodia()
    {
        return true;
    }
}
