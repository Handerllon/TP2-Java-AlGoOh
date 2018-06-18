package carta.magica;

import areaDeJuego.Tablero;
import carta.Efecto;

public class DarkHoleEfecto implements Efecto
{
    public void efecto(Tablero tablero)
    {
        tablero.destruirMonstruosJugador();
        tablero.destruirMonstruosOponente();
    }
}
