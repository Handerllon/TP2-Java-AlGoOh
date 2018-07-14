package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;
import Modelo.carta.CartaTrampa;

public class Reinforcements extends CartaTrampa
{
    private static String rutaImagen = "resources/imagenes/trampa/Reinforcements.jpg";

    public Reinforcements(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Reinforcements";
    }

    public void efecto()
    {

    }

    public void efecto(CartaMonstruo cartaMonstruoJugador, CartaMonstruo cartaMonstruoOponente)
    {

        cartaMonstruoOponente.reinforcements();
    }
}
