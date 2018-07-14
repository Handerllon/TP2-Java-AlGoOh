package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.CartaMonstruo;
import Modelo.carta.CartaTrampa;

public class MagicCylinder extends CartaTrampa
{
    private static String rutaImagen = "resources/imagenes/trampa/MagicCylinder.png";

    public MagicCylinder(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
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
