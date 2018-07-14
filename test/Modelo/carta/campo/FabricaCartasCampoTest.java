package Modelo.carta.campo;

import Modelo.carta.CartaCampo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FabricaCartasCampoTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {

        FabricaCartasCampo fabrica = new FabricaCartasCampo(null, null);

        CartaCampo unaCartaCampo = fabrica.obtenerCarta("Sogen");

        CartaCampo cartaEsperada = new Sogen(null, null);

        assertEquals(cartaEsperada.getClass(), unaCartaCampo.getClass());
    }
}