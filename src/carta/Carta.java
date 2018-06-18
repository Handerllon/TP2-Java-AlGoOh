package carta;

public class Carta
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

    public void establecerOrientacion(Orientacion orientacionNueva)
    {
        this.orientacion = orientacionNueva;
    }

    public void cambiarOrientacion()
    {
        this.orientacion.cambiarOrientacion(this);
    }

    public boolean orientacionArriba(){
        return this.orientacion instanceof OrientacionArriba;
    }

    public boolean orientacionAbajo(){
        return this.orientacion instanceof OrientacionAbajo;
    }
}
