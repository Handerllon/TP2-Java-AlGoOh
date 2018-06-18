package carta;

public class OrientacionArriba extends Orientacion
{
    public void cambiarOrientacion(Carta carta)
    {
        carta.establecerOrientacion(new OrientacionAbajo());
    }
}
