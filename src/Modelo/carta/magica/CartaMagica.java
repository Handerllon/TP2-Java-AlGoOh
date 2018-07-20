package Modelo.carta.magica;

import Modelo.Jugador;
import Modelo.carta.Carta;

public abstract class CartaMagica extends Carta
{
    public CartaMagica(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
    }

    public abstract void efecto();

    public boolean esCampo()
    {
        return false;
    }

    public boolean esMagica()
    {
        return true;
    }

    public boolean esMonstruo()
    {
        return false;
    }

    public boolean esTrampa()
    {
        return false;
    }
}
