package areaDeJuego;

import AlGoOh.Jugador;
import carta.CartaMagica;
import carta.CartaMonstruo;
import carta.Sacrificio;

public class Tablero
{

    private AreaDeCartas areaDeCartasJugador;
    private AreaDeCartas areaDeCartasOponente;
    private Jugador jugador;
    private Jugador oponente;

    public Tablero(Jugador jugador, Jugador oponente)
    {
        this.jugador = jugador;
        this.oponente = oponente;

        this.definirRegiones();
    }

    private void definirRegiones()
    {
        this.areaDeCartasJugador = this.jugador.regionDeCartas();
        this.areaDeCartasOponente = this.oponente.regionDeCartas();
    }

    // TODO: Esto cambia el estado del tablero. Podríamos abastaernos?
    public void cambiarTurno()
    {
        Jugador tempJugador = this.jugador;

        this.jugador = this.oponente;
        this.oponente = tempJugador;

        this.definirRegiones();
    }

    // --------------------------------------------------------------------
    // Métodos de agregación de cartas.
    // --------------------------------------------------------------------

    public void agregarCarta(CartaMonstruo carta, Sacrificio sacrificios)
    {
        // TODO: Esto capaz se puede hacer de una forma más elegante y prolija.
        if (this.areaDeCartasJugador.regionMonstruosNoVacia())
        {
            if (carta.getEstrellas() >= 5 && carta.getEstrellas() <= 6)
            {
                this.destruirMonstruoJugador(sacrificios.getMonstruo());
            } else if (carta.getEstrellas() >= 7)
            {
                this.destruirMonstruoJugador(sacrificios.getMonstruo());
                this.destruirMonstruoJugador(sacrificios.getMonstruo());
            }
        }
        this.areaDeCartasJugador.agregarCarta(carta);
    }

    public void agregarCarta(CartaMagica carta)
    {
        this.areaDeCartasJugador.agregarCarta(carta);
        carta.efecto(this);
    }

    // --------------------------------------------------------------------
    // Métodos de destrucción de cartas.
    // --------------------------------------------------------------------

    private void destruirMonstruoJugador(CartaMonstruo carta)
    {
        this.areaDeCartasJugador.removerCarta(carta);
        this.areaDeCartasJugador.enviarAlCementerio(carta);
    }

    public void destruirMonstruosJugador()
    {
        this.areaDeCartasJugador.enviarMonstruosAlCementerio();
    }

    private void destruirMonstruoOponente(CartaMonstruo carta)
    {
        this.areaDeCartasOponente.removerCarta(carta);
        this.areaDeCartasOponente.enviarAlCementerio(carta);
    }

    public void destruirMonstruosOponente()
    {
        this.areaDeCartasOponente.enviarMonstruosAlCementerio();
    }

    // --------------------------------------------------------------------
    // Métodos de estrategia.
    // --------------------------------------------------------------------

    public void atacarOponente(CartaMonstruo cartaJugador, CartaMonstruo cartaOponente)
    {
        int diferenciaPuntos = cartaJugador.getPuntos() - cartaOponente.getPuntos();

        // TODO: Esto capaz se puede hacer de una forma más elegante y prolija.
        if (cartaJugador.enAtaque() && cartaOponente.enAtaque())
        {
            if (diferenciaPuntos > 0)
            {
                this.destruirMonstruoOponente(cartaOponente);
                this.oponente.disminuirPuntosVida(Math.abs(diferenciaPuntos));
            } else if (diferenciaPuntos < 0)
            {
                this.destruirMonstruoJugador(cartaJugador);
                this.jugador.disminuirPuntosVida(Math.abs(diferenciaPuntos));
            } else if (diferenciaPuntos == 0)
            {
                this.destruirMonstruoOponente(cartaOponente);
                this.destruirMonstruoJugador(cartaJugador);
            }
        } else if (cartaJugador.enAtaque() && cartaOponente.enDefensa())
        {
            if (diferenciaPuntos > 0)
            {
                this.destruirMonstruoOponente(cartaOponente);
            } else if (diferenciaPuntos < 0)
            {
                this.jugador.disminuirPuntosVida(Math.abs(diferenciaPuntos));
            } else if (diferenciaPuntos == 0)
            {
                // No pasa nada.
            }
        }
    }
}
