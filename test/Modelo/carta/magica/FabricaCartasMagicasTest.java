package Modelo.carta.magica;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FabricaCartasMagicasTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {

        FabricaCartasMagicas fabrica = new FabricaCartasMagicas(null, null);

        CartaMagica unaCartaMagica = fabrica.getCarta("Dark Hole");

        CartaMagica cartaEsperada = new DarkHole(null, null);

        assertEquals(cartaEsperada.getClass(), unaCartaMagica.getClass());
    }
}
