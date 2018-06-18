package carta.monstruo;

import carta.Carta;
import carta.Modo;
import carta.ModoAtaque;
import carta.ModoDefensa;

public abstract class CartaMonstruo extends Carta
{
    protected int puntosAtaque;
    protected int puntosDefensa;
    protected int estrellas;
    protected Modo modo;

    public CartaMonstruo()
    {
    }

    public void establecerModo(Modo modoNuevo)
    {
        this.modo = modoNuevo;
    }

    public void cambiarModo()
    {
        this.modo.cambiarModo(this);
    }

    public int getPuntosDefensa()
    {
        return this.puntosDefensa;
    }

    public int getPuntosAtaque()
    {
        return this.puntosAtaque;
    }

    public int getPuntos()
    {
        return this.modo.getPuntos();
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
}