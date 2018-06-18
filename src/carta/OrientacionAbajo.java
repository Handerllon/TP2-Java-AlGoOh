package carta;

public class OrientacionAbajo extends Orientacion
{
    public void cambiarOrientacion(Carta carta)
    {
        carta.establecerOrientacion(new OrientacionArriba());
    }
}
