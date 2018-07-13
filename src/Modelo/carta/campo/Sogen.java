package Modelo.carta.campo;

import Modelo.Jugador;
import Modelo.carta.CartaCampo;
import Modelo.carta.CartaMonstruo;

import java.util.ArrayList;

public class Sogen extends CartaCampo
{
    public Sogen(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
        this.nombre = "Sogen";

        this.modificadorDefensa = 500;
        this.modificadorAtaque = 200;
    }

    public void efecto()
    {
        ArrayList<CartaMonstruo> cartasMonstruoJugador = this.jugador.obtenerCartasEnAreaMonstruo();
        ArrayList<CartaMonstruo> cartasMonstruoOponente = this.oponente.obtenerCartasEnAreaMonstruo();

        cartasMonstruoJugador.forEach(item -> this.modificarPuntosDefensa(item));
        cartasMonstruoOponente.forEach(item -> this.modificarPuntosAtaque(item));
    }

    public void deshacerEfecto()
    {
        ArrayList<CartaMonstruo> cartasMonstruoJugador = this.jugador.obtenerCartasEnAreaMonstruo();
        ArrayList<CartaMonstruo> cartasMonstruoOponente = this.oponente.obtenerCartasEnAreaMonstruo();

        cartasMonstruoJugador.forEach(item -> this.restaurarPuntosDefensa(item));
        cartasMonstruoOponente.forEach(item -> this.restaurarPuntosAtaque(item));
    }

    public void efecto(CartaMonstruo carta)
    {
        if (carta.obtenerPropietario() == this.jugador)
        {
            this.modificarPuntosDefensa(carta);
        } else
        {
            this.modificarPuntosAtaque(carta);
        }
    }

    public void deshacerEfecto(CartaMonstruo carta)
    {
        if (carta.obtenerPropietario() == this.jugador)
        {
            this.restaurarPuntosDefensa(carta);
        } else
        {
            this.restaurarPuntosAtaque(carta);
        }
    }
}
