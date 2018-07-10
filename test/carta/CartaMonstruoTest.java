package carta;

import AlGoOh.Jugador;
import carta.monstruo.GaiaTheFierceKnight;
import carta.monstruo.ManEaterBug;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
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

        CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2,"resources/imagenes/monstruo/GaiaTheFierceKnight.png");

        assertEquals(2100, carta.getPuntos(), 0);
    }

    @Test
    public void test02CartaMonstruoTienePuntosDeDefensaCorrectos()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        jugador2.establecerOponente(jugador1);

        CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2,"resources/imagenes/monstruo/GaiaTheFierceKnight.png");
        carta.cambiarModo();

        assertEquals(2300, carta.getPuntos(), 0);
    }
    
}