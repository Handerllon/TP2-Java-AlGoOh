package Modelo.carta;

import Modelo.Jugador;
import Modelo.carta.mano.Mano;
import Modelo.carta.mazo.Mazo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ManoTest
{
    @Test
    public void test01CuandoSeCreaLaManoDebeTener5Cartas()
    {
        Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Miauricio");
        Mazo mazo = new Mazo(jugador1, jugador2);
        Mano mano = new Mano(null);

        for (int i = 0; i < 5; i++)
        {
            mano.agregarCarta(mazo.tomarCarta());
        }

        int cantidadDeCartasEsperadas = 5;

        assertEquals(cantidadDeCartasEsperadas, mano.cantidadDeCartas());
    }
    
    @Test
    public void test02DevuelveTrueSiLaManoEstaLlena()
    {
    	Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Miauricio");
        Mazo mazo = new Mazo(jugador1, jugador2);
        Mano mano = new Mano(null);

        for (int i = 0; i < 7; i++)
        {
            mano.agregarCarta(mazo.tomarCarta());
        }

        assertTrue(mano.manoLlena());
    
    }
}