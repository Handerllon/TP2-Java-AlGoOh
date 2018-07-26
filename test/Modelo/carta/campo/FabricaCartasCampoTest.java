package Modelo.carta.campo;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FabricaCartasCampoTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {

        FabricaCartasCampo fabrica = new FabricaCartasCampo(null, null);

        CartaCampo unaCartaCampo = fabrica.getCarta("Sogen");

        assertTrue(unaCartaCampo.esCampo());
    }
}