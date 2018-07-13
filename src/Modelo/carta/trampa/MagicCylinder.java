package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;
import Modelo.carta.CartaTrampa;

public class MagicCylinder extends CartaTrampa
{
    public MagicCylinder(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
        this.nombre = "Magic Cylinder";
        this.trampaCancelaAtaqueAMonstruo = true;
    }

    public void efecto(CartaMonstruo cartaAtacante, CartaMonstruo cartaOponente)
    {

        oponente.disminuirPuntosVida(cartaAtacante.obtenerPuntosDeAtaque());
    }

    public void efecto()
    {

    }
}
