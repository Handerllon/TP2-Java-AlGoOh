package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.ataque.EstrategiaAtaque;
import Modelo.carta.ataque.EstrategiaAtaqueDefault;
import Modelo.carta.ataque.EstrategiaAtaqueModificadoMagicCylinder;
import Modelo.carta.monstruo.CartaMonstruo;

public class MagicCylinder extends CartaTrampa
{
    private static String rutaImagen = "resources/imagenes/trampa/MagicCylinder.png";
    CartaMonstruo cartaAtacante;
    private EstrategiaAtaque modificadorAtaque;

    public MagicCylinder(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Magic Cylinder";
        this.modificadorAtaque = EstrategiaAtaqueModificadoMagicCylinder.getInstancia();
    }

    @Override
    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada)
    {
        this.cartaAtacante = cartaAtacante;
        cartaAtacante.setEstrategiaAtaque(this.modificadorAtaque);
        this.getPropietario().destruirCarta(this);
        this.notificarEventoCartaTrampa();
    }

    @Override
    public void deshacerEfecto()
    {
        cartaAtacante.setEstrategiaAtaque(EstrategiaAtaqueDefault.getInstancia());
    }
}
