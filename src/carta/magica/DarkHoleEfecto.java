package carta.magica;

import AlGoOh.Jugador;
import carta.Efecto;

public class DarkHoleEfecto implements Efecto
{
    public void efecto(Jugador jugador, Jugador oponente)
    {
        jugador.destruirMonstruos();
        oponente.destruirMonstruos();
    }
}
