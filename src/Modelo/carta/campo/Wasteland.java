package Modelo.carta.campo;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.CartaCampo;
import Modelo.carta.CartaMonstruo;

import java.util.ArrayList;

public class Wasteland extends CartaCampo
{
    public Wasteland(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
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

    public void efecto(Carta carta)
    {
        if (carta.obtenerPropietario() == this.jugador)
        {
            this.modificarPuntosAtaque((CartaMonstruo) carta);
        } else
        {
            this.modificarPuntosDefensa((CartaMonstruo) carta);
        }
    }

    public void deshacerEfecto(Carta carta)
    {
        if (carta.obtenerPropietario() == this.jugador)
        {
            this.restaurarPuntosAtaque((CartaMonstruo) carta);
        } else
        {
            this.restaurarPuntosDefensa((CartaMonstruo) carta);
        }
    }
}
