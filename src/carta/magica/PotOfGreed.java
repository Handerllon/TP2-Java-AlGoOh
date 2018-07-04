package carta.magica;

import AlGoOh.Jugador;
import carta.CartaMagica;

public class PotOfGreed extends CartaMagica
{
    public PotOfGreed(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(jugador, oponente,locacionDeImagen);
        this.nombre = "Pot of Greed";
    }

    public void efecto()
    {
        this.jugador.tomarCarta();
        this.jugador.tomarCarta();
    }
}
