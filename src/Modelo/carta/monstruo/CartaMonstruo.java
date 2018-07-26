package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.modo.Modo;
import Modelo.carta.modo.ModoAtaque;
import Modelo.carta.modo.ModoDefensa;

public abstract class CartaMonstruo extends Carta
{
    protected int puntosAtaque;
    protected int puntosDefensa;
    protected int puntos;
    protected int estrellas;
    protected Modo modo;

    public CartaMonstruo(int puntosDefensa, int puntosAtaque, Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);

        this.puntosDefensa = puntosDefensa;
        this.puntosAtaque = puntosAtaque;

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;
    }

    public void cambiarModo()
    {
        this.modo.cambiarModo(this);
        this.actualizarPuntos();
    }

    public void setModo(Modo modoNuevo)
    {
        this.modo = modoNuevo;
    }

    // --------------------------------------------------------------------
    // Métodos de consulta.
    // --------------------------------------------------------------------
    public int getPuntos()
    {
        return this.puntos;
    }

    public boolean enAtaque()
    {
        return modo instanceof ModoAtaque;
    }

    public boolean enDefensa()
    {
        return modo instanceof ModoDefensa;
    }

    public int getEstrellas()
    {
        return this.estrellas;
    }

    public boolean esCampo()
    {
        return false;
    }

    public boolean esMagica()
    {
        return false;
    }

    public boolean esMonstruo()
    {
        return true;
    }

    public boolean esTrampa()
    {
        return false;
    }

    public boolean estaEnAtaque()
    {
        return this.modo.esAtaque();
    }

    public boolean estaEnDefensa()
    {
        return this.modo.esDefensa();
    }

    // --------------------------------------------------------------------
    // Métodos sobre puntos.
    // --------------------------------------------------------------------
    public void sumarPuntosAtaque(int puntos)
    {
        this.puntosAtaque += puntos;
        this.actualizarPuntos();
    }

    public void sumarPuntosDefensa(int puntos)
    {
        this.puntosDefensa += puntos;
        this.actualizarPuntos();
    }

    private void actualizarPuntos()
    {
        if (this.enAtaque())
        {
            this.puntos = this.puntosAtaque;
        } else
        {
            this.puntos = this.puntosDefensa;
        }
    }

    private int calcularDiferenciaPuntos(CartaMonstruo cartaOponente)
    {

        return (this.getPuntos() - cartaOponente.getPuntos());
    }

    // --------------------------------------------------------------------
    // Métodos de ataque.
    // --------------------------------------------------------------------

    public int getPuntosDeAtaque()
    {

        return this.puntosAtaque;
    }

    // ***********************************************************************************
    // *************************** TODO: REFACTORIZAR ************************************
    // ***********************************************************************************
    // TODO: hay alguna forma de no preguntar el estado de la carta del oponente, utilizando solamente mensajes, y
    // que ella haga lo que tenga que hacer dependiendo del estado en que se encuentra? Además, capaz cada carta
    // tenga una estrategia de ataque diferente, como la carta come hombres.
    // TODO : Los puntos de ataque del monstruo afectado por la carta trampa deben volver a la normalidad una vez que
    // termina el turno, ver como implementarlo.
    public void atacar(CartaMonstruo cartaAAtacar)
    {
        cartaAAtacar.recibirAtaque(this);

        if (cartaAAtacar.estaBocaAbajo())
        {
            cartaAAtacar.cambiarOrientacion();
        }
    }

    public void atacar()
    {
        this.getOponente().disminuirPuntosVida(this.getPuntosDeAtaque());
    }

    public void recibirAtaque(CartaMonstruo cartaAtacante)
    {

        // La diferencia queda respecto de mi, el oponente de la atacante.
        int diferenciaDePuntos = cartaAtacante.calcularDiferenciaPuntos(this);

        if (this.enAtaque()) // Si estoy en ataque.
        {
            if (diferenciaDePuntos > 0) // Si la atacante tiene más puntos.
            {
                this.getPropietario().destruirCarta(this);
                this.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else if (diferenciaDePuntos < 0) // Si yo tengo más puntos.
            {
                cartaAtacante.getPropietario().destruirCarta(cartaAtacante);
                cartaAtacante.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                this.getPropietario().destruirCarta(this);
                cartaAtacante.getPropietario().destruirCarta(cartaAtacante);
            }
        } else // Si estoy en defensa.
        {
            if (diferenciaDePuntos > 0) // Si la atacante tiene más puntos.
            {
                this.getPropietario().destruirCarta(this);
            } else if (diferenciaDePuntos < 0) // Si yo tengo más puntos.
            {
                cartaAtacante.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                //No pasa nada
            }
        }
    }


        /*
        int diferenciaDePuntos = this.getPuntos() - cartaAAtacar.getPuntos();

        if (cartaAAtacar.enAtaque())
        {
            if (diferenciaDePuntos > 0) // Si yo tengo mas puntos.
            {
                cartaAAtacar.getPropietario().destruirCarta(cartaAAtacar);
                cartaAAtacar.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else if (diferenciaDePuntos < 0) // Si la carta a atacar tiene mas puntos.
            {
                this.getPropietario().destruirCarta(this);
                this.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                cartaAAtacar.getPropietario().destruirCarta(cartaAAtacar);
                this.getPropietario().destruirCarta(this);
            }
        } else // Si la carta a atacar esta en defensa.
        {
            if (diferenciaDePuntos > 0) // Si yo tengo mas puntos.
            {
                cartaAAtacar.getPropietario().destruirCarta(cartaAAtacar);
            } else if (diferenciaDePuntos < 0) // Si la carta a atacar tiene mas puntos.
            {
                this.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                //No pasa nada
            }
        }
        */

    // ***********************************************************************************
    // *************************** TODO: REFACTORIZAR ************************************
    // ***********************************************************************************

    // --------------------------------------------------------------------
    // Métodos de invocación.
    // --------------------------------------------------------------------
    public void summon(Sacrificio sacrificio)
    {

    }

    public boolean requiereSacrificio()
    {
        return false;
    }
}