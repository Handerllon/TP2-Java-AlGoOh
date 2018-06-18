package carta.monstruo;


import carta.CartaMonstruo;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CartaMonstruoTest
{
    @Test
    public void test01CartaMonstruoTienePuntosDeAtaqueCorrectos()
    {
        CartaMonstruo carta = new GaiaTheFierceKnight();

        assertTrue(carta.getPuntos() == 2100);
    }

    @Test
    public void test02CartaMonstruoTienePuntosDeDefensaCorrectos()
    {
        CartaMonstruo carta = new GaiaTheFierceKnight();
        carta.cambiarModo();

        assertTrue(carta.getPuntos() == 2300);
    }
}