package carta.campo;

import AlGoOh.Jugador;
import carta.CartaCampo;

public class Wasteland extends CartaCampo
{
    private static int modificadorAtaque;
    private static int modificadorDefensa;

    public Wasteland(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente);
        this.nombre = "Wasteland";

        //En region de jugador que juega la carta
        this.modificadorAtaque = 200;
        //En region de oponente
        this.modificadorDefensa = 300;
    }

    public void efecto()
    {
        //TODO: Aqui se debe modificar la region de monstruos de cada uno de los jugadores
    }
}
