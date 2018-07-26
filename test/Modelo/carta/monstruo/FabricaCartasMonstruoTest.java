package Modelo.carta.monstruo;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FabricaCartasMonstruoTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {

        FabricaCartasMonstruo fabrica = new FabricaCartasMonstruo(null, null);

        CartaMonstruo unaCartaMonstruo = fabrica.getCarta("Alexandrite Dragon");

        assertTrue(unaCartaMonstruo.esMonstruo());
    }
}