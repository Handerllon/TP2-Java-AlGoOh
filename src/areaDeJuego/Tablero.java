package areaDeJuego;

import AlGoOh.Jugador;
import carta.Sacrificio;
import carta.magica.CartaMagica;
import carta.monstruo.CartaMonstruo;

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

        this.inicializarAreas();
    }

    private void inicializarAreas()
    {
        this.areaDeCartasJugador = this.jugador.areaDeCartas();
        this.areaDeCartasOponente = this.oponente.areaDeCartas();
    }

    // Esto cambia el estado del tablero. Podríamos abastaernos.
    public void cambiarTurno()
    {
        Jugador tempJugador = this.jugador;

        this.jugador = this.oponente;
        this.oponente = tempJugador;

        this.inicializarAreas();
    }

    public void atacarOponente(CartaMonstruo cartaJugador, CartaMonstruo cartaOponente)
    {
        int diferenciaPuntos = cartaJugador.getPuntos() - cartaOponente.getPuntos();

        // Esto capaz se puede hacer de una forma más elegante y prolija.
        if (cartaJugador.enAtaque() && cartaOponente.enAtaque())
        {
            if (diferenciaPuntos > 0)
            {
                areaDeCartasOponente.enviarAlCementerio(cartaOponente);
                oponente.restarVida(Math.abs(diferenciaPuntos));
                areaDeCartasOponente.removerCarta(cartaOponente);
            }
            if (diferenciaPuntos < 0)
            {
                areaDeCartasJugador.enviarAlCementerio(cartaJugador);
                jugador.restarVida(Math.abs(diferenciaPuntos));
                areaDeCartasJugador.removerCarta(cartaJugador);
            }
            if (diferenciaPuntos == 0)
            {
                areaDeCartasOponente.enviarAlCementerio(cartaOponente);
                areaDeCartasJugador.enviarAlCementerio(cartaJugador);
                areaDeCartasJugador.removerCarta(cartaJugador);
                areaDeCartasOponente.removerCarta(cartaOponente);
            }
        }

        if (cartaJugador.enAtaque() && cartaOponente.enDefensa())
        {
            if (diferenciaPuntos > 0)
            {
                areaDeCartasOponente.enviarAlCementerio(cartaOponente);
                areaDeCartasOponente.removerCarta(cartaOponente);
            }
            if (diferenciaPuntos < 0)
            {
                jugador.restarVida(Math.abs(diferenciaPuntos));
            }
            if (diferenciaPuntos == 0)
            {
                // No pasa nada.
            }
        }
    }

    public void activarEfectoCartaMagica(CartaMagica cartaJugador)
    {
        cartaJugador.efecto(this);
    }

    public void destruirMonstruosJugador()
    {
        areaDeCartasJugador.enviarTodosMonstruosAlCementerio();
    }

    public void destruirMonstruosOponente()
    {
        areaDeCartasOponente.enviarTodosMonstruosAlCementerio();
    }


    public void agregarCarta(CartaMonstruo cartaJugador, Sacrificio sacrificios)
    {
        CartaMonstruo cartaASacrificar;

        if (areaDeCartasJugador.tieneMonstruos())
        {
            if (cartaJugador.getNivel() >= 5 && cartaJugador.getNivel() <= 6)
            {
                cartaASacrificar = sacrificios.obtenerMonstruo();
                areaDeCartasJugador.removerCarta(cartaASacrificar);
                areaDeCartasJugador.enviarAlCementerio(cartaASacrificar);

            } else if (cartaJugador.getNivel() >= 7)
            {
                cartaASacrificar = sacrificios.obtenerMonstruo();
                areaDeCartasJugador.removerCarta(cartaASacrificar);
                areaDeCartasJugador.enviarAlCementerio(cartaASacrificar);
                cartaASacrificar = sacrificios.obtenerMonstruo();
                areaDeCartasJugador.removerCarta(cartaASacrificar);
                areaDeCartasJugador.enviarAlCementerio(cartaASacrificar);
            }
        }

        areaDeCartasJugador.agregarCarta(cartaJugador);
    }

    public void agregarCarta(CartaMagica cartaJugador)
    {
        areaDeCartasJugador.agregarCarta(cartaJugador);
        if (cartaJugador.orientacionArriba())
        {
            this.activarEfectoCartaMagica(cartaJugador);
        }
    }
}
