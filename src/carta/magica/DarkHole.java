package carta.magica;

import areaDeJuego.Tablero;

public class DarkHole extends CartaMagica
{
    public void efecto(Tablero tablero)
    {
        tablero.destruirMonstruosJugador();
        tablero.destruirMonstruosOponente();
    }
}
