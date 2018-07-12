package Modelo.carta;

public class OrientacionAbajo implements Orientacion
{
    public void cambiarOrientacion(Carta carta)
    {
        carta.establecerOrientacion(new OrientacionArriba());
    }
}
