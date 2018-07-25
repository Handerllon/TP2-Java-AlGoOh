package Modelo.carta.monstruo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FabricaCartasMonstruoTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {

        FabricaCartasMonstruo fabrica = new FabricaCartasMonstruo(null, null);

        CartaMonstruo unaCartaMonstruo = fabrica.getCarta("Alexandrite Dragon");

        CartaMonstruo cartaEsperada = new AlexandriteDragon(null, null);

        assertEquals(cartaEsperada.getClass(), unaCartaMonstruo.getClass());
    }
}