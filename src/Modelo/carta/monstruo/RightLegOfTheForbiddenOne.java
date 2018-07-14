package Modelo.carta.monstruo;

import Modelo.Jugador;

public class RightLegOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;
    private static String rutaImagen = "resources/imagenes/monstruo/RightLegOfTheForbiddenOne.png";

    public RightLegOfTheForbiddenOne(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 1;
        this.nombre = "Right Leg Of The Forbidden One";
    }

    public boolean esParteExodia()
    {
        return true;
    }
}
