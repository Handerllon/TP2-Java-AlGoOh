package carta;

import AlGoOh.Jugador;

public abstract class Carta
{
    protected String nombre;
    protected Orientacion orientacion;
    protected Jugador jugador, oponente;

    public Carta(Jugador jugador, Jugador oponente)
    {
        this.orientacion = new OrientacionAbajo();
        this.jugador = jugador;
        this.oponente = oponente;
    }

    public String obtenerNombre()
    {
        return nombre;
    }

    public void cambiarOrientacion()
    {
        this.orientacion.cambiarOrientacion(this);
    }

    protected void establecerOrientacion(Orientacion orientacionNueva)
    {
        this.orientacion = orientacionNueva;
    }

    protected boolean orientacionArriba()
    {
        return this.orientacion instanceof OrientacionArriba;
    }

    public Jugador obtenerPropietario()
    {
        return this.jugador;
    }
}
