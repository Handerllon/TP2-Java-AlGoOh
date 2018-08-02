package Modelo.carta;

import org.junit.Test;

import Modelo.Jugador;
import Modelo.carta.mazo.Mazo;

import static org.junit.Assert.assertTrue;

public class MazoTest
{
    @Test
    public void test01MazoDevuelveLaCantidadEsperadaLuegoDeTomarUnaCarta()
    {
    	Jugador jugador = new Jugador("J1");
    	Jugador jugador2 = new Jugador("J1");
    	
    	Mazo mazo = new Mazo(jugador,jugador2);
    	
    	Carta carta = mazo.tomarCarta();
    	
    	
        assertTrue(mazo.cantidadCartas() == 39);
    }
}