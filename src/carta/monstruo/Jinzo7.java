package carta.monstruo;

import areaDeJuego.Tablero;
import carta.CartaMonstruo;
import carta.Efecto;

public class Jinzo7 extends CartaMonstruo implements Efecto
{
    private static int PUNTOS_DEFENSA = 400;
    private static int PUNTOS_ATAQUE = 500;

    public Jinzo7()
    {
        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE);
        this.estrellas = 2;
        this.nombre = "Jinzo7";
    }

    public void efecto(Tablero tablero)
    {
    }
}
