package Modelo.carta;

public class OrientacionArriba implements Orientacion
{
    public void cambiarOrientacion(Carta carta)
    {
        carta.establecerOrientacion(new OrientacionAbajo());
    }
}
