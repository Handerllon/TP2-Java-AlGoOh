package Modelo.carta.excepciones;

import Modelo.carta.Carta;

public class CartaNoExisteEnRegion extends RuntimeException
{
    Carta carta;

    public CartaNoExisteEnRegion(Carta carta)
    {
        this.carta = carta;
    }
}
