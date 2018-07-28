package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;

public class Reinforcements extends CartaTrampa
{
    private static final String rutaImagen = "resources/imagenes/trampa/Reinforcements.jpg";
    private static final int modificadorPuntosAtaque = 500;
    private CartaMonstruo cartaAtacada;

    public Reinforcements(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Reinforcements";
    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {
        this.cartaAtacada = cartaAtacada;
        cartaAtacada.sumarPuntosAtaque(modificadorPuntosAtaque);
        this.notificarEventoCartaTrampa();
        this.getPropietario().destruirCarta(this);
    }

    @Override
    public void deshacerEfecto()
    {
        this.cartaAtacada.sumarPuntosAtaque(-modificadorPuntosAtaque);
    }
}
