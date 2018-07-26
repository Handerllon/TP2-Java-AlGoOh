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

    public int getPuntosDeAtaque()
    {

        return this.puntosAtaque;
    }

    // --------------------------------------------------------------------
    // Métodos de ataque.
    // --------------------------------------------------------------------

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
        cartaAAtacar.aplicarDaño(this);

        if (cartaAAtacar.estaBocaAbajo())
        {
            cartaAAtacar.cambiarOrientacion();
        }
    }

    public void atacar()
    {
        this.oponente.disminuirPuntosVida(this.getPuntosDeAtaque());
    }

    // Utilizamos cartaAtacante ya que es la carta misma la que debe saber cómo calcular su daño.
    public void aplicarDaño(CartaMonstruo cartaAtacante)
    {

        int diferenciaDePuntos = cartaAtacante.calcularDiferenciaPuntos(this);

        if (this.enAtaque())
        {

            if (diferenciaDePuntos > 0)
            {
                this.jugador.destruirCarta(this);
                this.jugador.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else if (diferenciaDePuntos < 0)
            {
                this.oponente.destruirCarta(cartaAtacante);
                this.oponente.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                this.jugador.destruirCarta(this);
                this.oponente.destruirCarta(cartaAtacante);
            }
        } else
        {
            if (diferenciaDePuntos > 0)
            {

                this.jugador.destruirCarta(this);
            } else if (diferenciaDePuntos < 0)
            {

                this.oponente.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                //No pasa nada
            }
        }
    }

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