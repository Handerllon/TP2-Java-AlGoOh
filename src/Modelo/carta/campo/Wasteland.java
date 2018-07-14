package Modelo.carta.campo;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;

import java.util.ArrayList;

public class Wasteland extends CartaCampo
{
    private static String rutaImagen = "resources/imagenes/campo/Wasteland.png";

    public Wasteland(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Wasteland";

        this.modificadorAtaque = 200;
        this.modificadorDefensa = 300;
    }

    public void efecto()
    {
        ArrayList<CartaMonstruo> cartasMonstruoJugador = this.jugador.obtenerCartasEnAreaMonstruo();
        ArrayList<CartaMonstruo> cartasMonstruoOponente = this.oponente.obtenerCartasEnAreaMonstruo();

        cartasMonstruoJugador.forEach(item -> this.modificarPuntosAtaque(item));
        cartasMonstruoOponente.forEach(item -> this.modificarPuntosDefensa(item));
    }

    public void deshacerEfecto()
    {
        ArrayList<CartaMonstruo> cartasMonstruoJugador = this.jugador.obtenerCartasEnAreaMonstruo();
        ArrayList<CartaMonstruo> cartasMonstruoOponente = this.oponente.obtenerCartasEnAreaMonstruo();

        cartasMonstruoJugador.forEach(item -> this.restaurarPuntosAtaque(item));
        cartasMonstruoOponente.forEach(item -> this.restaurarPuntosDefensa(item));
    }

    public void efecto(CartaMonstruo carta)
    {
        if (carta.obtenerPropietario() == this.jugador)
        {
            this.modificarPuntosAtaque(carta);
        } else
        {
            this.modificarPuntosDefensa(carta);
        }
    }

    public void deshacerEfecto(CartaMonstruo carta)
    {
        if (carta.obtenerPropietario() == this.jugador)
        {
            this.restaurarPuntosAtaque(carta);
        } else
        {
            this.restaurarPuntosDefensa(carta);
        }
    }
}
