package areaDeJuego;

import AlGoOh.Jugador;
import carta.Carta;
import carta.CartaMonstruo;
import carta.FabricaCartas;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class RegionTest
{
    @Test
    public void test01()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        jugador2.establecerOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMonstruo carta = fabricaCartasJugador1.crearCartaMonstruo("Alexandrite Dragon");

        int capacidadMaxima = 10;
        Region region = new Region(capacidadMaxima);

        assertFalse(region.contieneCarta(carta));
    }

    @Test
    public void test02()
    {
        int capacidadMaxima = 10;
        Region region = new Region(capacidadMaxima);

        ArrayList<Carta> cartas = region.obtenerCartas();

        assertTrue(cartas.isEmpty());
    }

    @Test
    public void test03()
    {
        int capacidadMaxima = 10;
        Region region = new Region(capacidadMaxima);

        region.removerTodasLasCartas();

        ArrayList<Carta> cartas = region.obtenerCartas();

        assertTrue(cartas.isEmpty());
    }
}