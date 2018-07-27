package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.ataque.Ataque;
import Modelo.carta.ataque.AtaqueDefault;
import Modelo.carta.ataque.AtaqueModificadoMagicCylinder;
import Modelo.carta.monstruo.CartaMonstruo;

public class MagicCylinder extends CartaTrampa
{
    private static String rutaImagen = "resources/imagenes/trampa/MagicCylinder.png";
    CartaMonstruo cartaAtacante;
    private Ataque modificadorAtaque;

    public MagicCylinder(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Magic Cylinder";
        this.modificadorAtaque = AtaqueModificadoMagicCylinder.getInstancia();
    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {
        this.cartaAtacante = cartaAtacante;
        cartaAtacante.setAtaque(this.modificadorAtaque);
        this.notificarObservadores();
        this.getPropietario().destruirCarta(this);
    }

    @Override
    public void deshacerEfecto()
    {
        cartaAtacante.setAtaque(AtaqueDefault.getInstancia());
    }
}
