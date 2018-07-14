package Modelo.carta.excepciones;

import Modelo.carta.Carta;

public class CartaNoExisteEnRegionError extends RuntimeException
{
    Carta carta;

    public CartaNoExisteEnRegionError(Carta carta)
    {
        this.carta = carta;
    }
}
