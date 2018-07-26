package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.ataque.Ataque;
import Modelo.carta.ataque.AtaqueDefault;
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
    protected Ataque ataque;

    public CartaMonstruo(int puntosDefensa, int puntosAtaque, Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);

        this.puntosDefensa = puntosDefensa;
        this.puntosAtaque = puntosAtaque;

        this.modo = new ModoDefensa();
        this.puntos = this.puntosDefensa;

        this.ataque = new AtaqueDefault();
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

    public void setAtaque(Ataque ataque)
    {
        this.ataque = ataque;
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

    private int calcularDiferenciaPuntosRespectoDeCarta(CartaMonstruo carta)
    {
        return (this.getPuntos() - carta.getPuntos());
    }

    public int getPuntosDeAtaque()
    {

        return this.puntosAtaque;
    }

    // --------------------------------------------------------------------
    // Métodos de ataque.
    // --------------------------------------------------------------------
    public void atacar(CartaMonstruo atacada)
    {
        this.ataque.ejecutar(this, atacada);
    }

    public void atacar()
    {
        this.ataque.ejecutar(this);
    }

    public void recibirAtaque(CartaMonstruo atacante)
    {

        // La diferencia queda respecto de mi, el oponente de la atacante.
        int diferenciaDePuntos = atacante.calcularDiferenciaPuntosRespectoDeCarta(this);

        if (this.enAtaque())
        {
            if (diferenciaDePuntos > 0) // Si la atacante tiene más puntos.
            {
                this.getPropietario().destruirCarta(this);
                this.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else if (diferenciaDePuntos < 0) // Si la atacada tiene más puntos.
            {
                atacante.getPropietario().destruirCarta(atacante);
                atacante.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                this.getPropietario().destruirCarta(this);
                atacante.getPropietario().destruirCarta(atacante);
            }
        } else
        {
            if (diferenciaDePuntos > 0) // Si la atacante tiene más puntos.
            {
                this.getPropietario().destruirCarta(this);
            } else if (diferenciaDePuntos < 0) // Si la atacada tiene más puntos.
            {
                atacante.getPropietario().disminuirPuntosVida(Math.abs(diferenciaDePuntos));
            } else
            {
                //No pasa nada
            }
        }
    }

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