package Modelo.carta.magica;

import Controlador.visitor.VisitadorCarta;
import Modelo.Jugador;
import Modelo.carta.Carta;

public abstract class CartaMagica extends Carta
{
    public CartaMagica(Jugador jugador, Jugador oponente, String locacionDeImagen)
    {
        super(jugador, oponente, locacionDeImagen);
    }

    // --------------------------------------------------------------------
    // Metodos para patrón visitador.
    // --------------------------------------------------------------------
    public void aceptar(VisitadorCarta visitadorCarta)
    {
        visitadorCarta.visitarCartaMagica(this);
    }

    public void efecto()
    {
        this.efectoParticular();
        this.getPropietario().destruirCarta(this);
    }

    protected abstract void efectoParticular();

    public boolean esCampo()
    {
        return false;
    }

    public boolean esMagica()
    {
        return true;
    }

    public boolean esMonstruo()
    {
        return false;
    }

    public boolean esTrampa()
    {
        return false;
    }
}
