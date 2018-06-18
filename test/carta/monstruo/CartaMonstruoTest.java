package carta.monstruo;


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

    @Test
    public void test03CartaMonstruoTieneOrientacionInicialHaciaAbajo()
    {
        CartaMonstruo carta = new GaiaTheFierceKnight();
        assertTrue(carta.orientacionAbajo());
    }

    @Test
    public void test04CartaMonstruoCambiaHorientacionHaciaArribaYQuedaHaciaArriba()
    {
        CartaMonstruo carta = new GaiaTheFierceKnight();
        carta.cambiarOrientacion();
        assertTrue(carta.orientacionArriba());
    }
}