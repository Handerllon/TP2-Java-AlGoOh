package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;

public class LeftArmOfTheForbiddenOne extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 300;
    private static int PUNTOS_ATAQUE = 200;
    private static String rutaImagen = "resources/imagenes/monstruo/LeftArmOfTheForbiddenOne.png";

    public LeftArmOfTheForbiddenOne(Jugador jugador, Jugador oponente)
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 1;
        this.nombre = "Left Arm Of The Forbidden One";
    }

    public boolean esParteExodia()
    {
        return true;
    }
}
