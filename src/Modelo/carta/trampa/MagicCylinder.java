package Modelo.carta.trampa;

import Modelo.Jugador;
import Modelo.carta.ataque.Ataque;
import Modelo.carta.ataque.AtaqueDefault;
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
        this.modificadorAtaque = new AtaqueModificadoMagicCylinder();
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
        cartaAtacante.setAtaque(new AtaqueDefault());
    }

    private class AtaqueModificadoMagicCylinder implements Ataque
    {
        @Override
        public void ejecutar(CartaMonstruo atacante, CartaMonstruo atacada)
        {
            this.ejecutar(atacante);
        }

        @Override
        public void ejecutar(CartaMonstruo atacante)
        {
            atacante.getPropietario().disminuirPuntosVida(atacante.getPuntosDeAtaque());
        }
    }
}
