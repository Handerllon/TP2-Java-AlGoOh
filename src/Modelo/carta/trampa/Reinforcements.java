package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;

public class Reinforcements extends CartaTrampa
{
    private static final String rutaImagen = "resources/imagenes/trampa/Reinforcements.jpg";
    private static final int modificadorPuntosAtaque = 500;
    private CartaMonstruo cartaAtacante, cartaAtacada;

    public Reinforcements(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Reinforcements";
    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante)
    {

    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {
        this.cartaAtacante = cartaAtacante;
        this.cartaAtacada = cartaAtacada;

        cartaAtacada.sumarPuntosAtaque(modificadorPuntosAtaque);
    }

    @Override
    public void deshacerEfecto()
    {
        this.cartaAtacada.sumarPuntosAtaque(-modificadorPuntosAtaque);
    }
}
