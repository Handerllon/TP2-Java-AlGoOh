package carta.trampa;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.CartaTrampa;

public class MagicCylinder extends CartaTrampa
{
    public MagicCylinder(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {
        super(jugador, oponente,locacionDeImagen);
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
