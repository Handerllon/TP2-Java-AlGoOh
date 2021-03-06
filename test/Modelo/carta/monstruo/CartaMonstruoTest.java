package Modelo.carta.monstruo;

import Modelo.Jugador;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartaMonstruoTest
{
    @Test
    public void test01CartaMonstruoTienePuntosDeAtaqueCorrectos()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);

        assertEquals(2100, carta.getPuntos(), 0);
    }

    @Test
    public void test02CartaMonstruoTienePuntosDeDefensaCorrectos()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);
        carta.cambiarModo();

        assertEquals(2300, carta.getPuntos(), 0);
    }
    
}