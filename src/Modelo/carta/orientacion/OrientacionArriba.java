package Modelo.carta.orientacion;

import Modelo.carta.Carta;

public class OrientacionArriba implements Orientacion
{
    public void cambiarOrientacion(Carta carta)
    {
        carta.establecerOrientacion(new OrientacionAbajo());
    }
}
