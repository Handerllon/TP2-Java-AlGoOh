package carta.magica;

import areaDeJuego.Tablero;

public class DarkHole extends CartaMagica
{
    public void efecto(Tablero tablero)
    {
        // TODO: Depende del estado en el que está la carta. Podemos hacerlo más prolijo?
        if(this.orientacionArriba())
        {
            tablero.destruirMonstruosJugador();
            tablero.destruirMonstruosOponente();
        }
    }
}
