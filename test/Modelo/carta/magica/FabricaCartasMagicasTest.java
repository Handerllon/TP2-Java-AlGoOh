package Modelo.carta.magica;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FabricaCartasMagicasTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {

        FabricaCartasMagicas fabrica = new FabricaCartasMagicas(null, null);

        CartaMagica unaCartaMagica = fabrica.getCarta("Dark Hole");

        assertTrue(unaCartaMagica.esMagica());
    }
}
