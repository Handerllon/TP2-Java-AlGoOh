package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;

public class MagicCylinder extends CartaTrampa
{
    private static String rutaImagen = "resources/imagenes/trampa/MagicCylinder.png";
    CartaMonstruo cartaAtacante;

    public MagicCylinder(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Magic Cylinder";
        this.trampaCancelaAtaqueAMonstruo = true;
    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante)
    {
        this.cartaAtacante = cartaAtacante;
        Jugador jugadorAtacante = cartaAtacante.getPropietario();
        cartaAtacante.setOponente(jugadorAtacante);
    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {

    }

    @Override
    public void deshacerEfecto()
    {
        Jugador jugadorOponente = this.cartaAtacante.getPropietario().getOponente();
        this.cartaAtacante.setOponente(jugadorOponente);
    }
}
