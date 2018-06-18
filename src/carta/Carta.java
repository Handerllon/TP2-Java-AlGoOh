package carta;

public abstract class Carta
{

    protected String nombre;
    protected Orientacion orientacion;

    public Carta()
    {
        this.orientacion = new OrientacionAbajo();
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
}
