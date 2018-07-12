package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.CartaMagica;

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
