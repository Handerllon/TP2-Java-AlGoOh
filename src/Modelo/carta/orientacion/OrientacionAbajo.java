package Modelo.carta.orientacion;

import Modelo.carta.Carta;

public class OrientacionAbajo implements Orientacion
{
    public void cambiarOrientacion(Carta carta)
    {
        carta.establecerOrientacion(new OrientacionArriba());
    }
}
