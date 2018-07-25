package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.modo.Modo;
import Modelo.carta.modo.ModoAtaque;
import Modelo.carta.modo.ModoDefensa;
import Modelo.carta.trampa.CartaTrampa;

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

    public void establecerModo(Modo modoNuevo)
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

    public int obtenerPuntosDeAtaque()
    {

        return this.puntosAtaque;
    }

    // --------------------------------------------------------------------
    // Métodos de ataque.
    // --------------------------------------------------------------------

    // TODO: la Modelo.carta monstruo no debería saber sobre reinforcements...
    public void reinforcements()
    {

        this.puntosAtaque = this.puntosAtaque + 500;
        if (this.enAtaque())
        {
            this.puntos = this.puntosAtaque;
        }
    }

    // TODO: hay alguna forma de no preguntar el estado de la Modelo.carta del oponente, utilizando solamente mensajes, y
    // que ella haga lo que tenga que hacer dependiendo del estado en que se encuentra? Además, capaz cada Modelo.carta
    // tenga una estrategia de ataque diferente, como la Modelo.carta come hombres.
    // TODO: Ver de nuevo funcionamiento de cartas trampa
    public void atacarOponente(CartaMonstruo cartaOponente)
    {

        // TODO: la Modelo.carta monstruo no debería saber sobre la Modelo.carta trampa...
        CartaTrampa cartaTrampa = this.oponente.obtenerCartaTrampaAActivar();

        // TODO: no es bueno preguntar si algo es null.
        if (cartaTrampa == null)
        {

            cartaOponente.recibirAtaque(this);
        } else if (!cartaTrampa.trampaCancelaAtaqueAMonstruo())
        {
            cartaTrampa.efecto(this, cartaOponente);
            cartaOponente.recibirAtaque(this);
        } else
        {
            cartaTrampa.efecto(this, cartaOponente);
        }
    }

    public void recibirAtaque(CartaMonstruo cartaAtacante)
    {

        int diferenciaDePuntos = cartaAtacante.calcularDiferenciaPuntos(this);

        if (this.enAtaque())
        {

            if (diferenciaDePuntos > 0)
            {

                this.jugador.destruirMonstruo(this);
                this.jugador.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else if (diferenciaDePuntos < 0)
            {

                this.oponente.destruirMonstruo(cartaAtacante);
                this.oponente.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {

                this.jugador.destruirMonstruo(this);
                this.oponente.destruirMonstruo(cartaAtacante);
            }
        } else
        {
            if (diferenciaDePuntos > 0)
            {

                this.jugador.destruirMonstruo(this);
            } else if (diferenciaDePuntos < 0)
            {

                this.oponente.disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                //No pasa nada
            }
        }
    }

    public void atacarOponente()
    {

        CartaTrampa cartaTrampa = this.oponente.obtenerCartaTrampaAActivar();

        if (cartaTrampa == null)
        {

            this.oponente.disminuirPuntosVida(this.obtenerPuntosDeAtaque());
        } else
        {
            cartaTrampa.efecto(this, null);
        }
    }

    // --------------------------------------------------------------------
    // Métodos de invocación.
    // --------------------------------------------------------------------
    public void invocar(Sacrificio sacrificio)
    {

    }

    public boolean requiereSacrificio()
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
}