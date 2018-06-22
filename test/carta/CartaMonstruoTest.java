package carta;


import AlGoOh.Jugador;
import carta.monstruo.GaiaTheFierceKnight;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CartaMonstruoTest
{
    @Test
    public void test01CartaMonstruoTienePuntosDeAtaqueCorrectos()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        jugador2.establecerOponente(jugador1);

        CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);

        assertTrue(carta.getPuntos() == 2100);
    }

    @Test
    public void test02CartaMonstruoTienePuntosDeDefensaCorrectos()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        jugador2.establecerOponente(jugador1);

        CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);
        carta.cambiarModo();

        assertTrue(carta.getPuntos() == 2300);
    }
}