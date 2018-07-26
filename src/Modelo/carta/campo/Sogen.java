package Modelo.carta.campo;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;

import java.util.ArrayList;

public class Sogen extends CartaCampo
{
    private static String rutaImagen = "resources/imagenes/campo/Sogen.png";

    public Sogen(Jugador jugador, Jugador oponente)
    {
        super(jugador, oponente, rutaImagen);
        this.nombre = "Sogen";

        this.modificadorDefensa = 500;
        this.modificadorAtaque = 200;
    }

    public void efecto()
    {
        ArrayList<CartaMonstruo> cartasMonstruoJugador = this.getPropietario().getCartasEnAreaMonstruo();
        ArrayList<CartaMonstruo> cartasMonstruoOponente = this.getOponente().getCartasEnAreaMonstruo();

        cartasMonstruoJugador.forEach(item -> this.modificarPuntosDefensa(item));
        cartasMonstruoOponente.forEach(item -> this.modificarPuntosAtaque(item));
    }

    public void deshacerEfecto()
    {
        ArrayList<CartaMonstruo> cartasMonstruoJugador = this.getPropietario().getCartasEnAreaMonstruo();
        ArrayList<CartaMonstruo> cartasMonstruoOponente = this.getOponente().getCartasEnAreaMonstruo();

        cartasMonstruoJugador.forEach(item -> this.restaurarPuntosDefensa(item));
        cartasMonstruoOponente.forEach(item -> this.restaurarPuntosAtaque(item));
    }

    public void efecto(CartaMonstruo carta)
    {
        if (carta.getPropietario() == this.getPropietario())
        {
            this.modificarPuntosDefensa(carta);
        } else
        {
            this.modificarPuntosAtaque(carta);
        }
    }

    public void deshacerEfecto(CartaMonstruo carta)
    {
        if (carta.getPropietario() == this.getPropietario())
        {
            this.restaurarPuntosDefensa(carta);
        } else
        {
            this.restaurarPuntosAtaque(carta);
        }
    }
}
