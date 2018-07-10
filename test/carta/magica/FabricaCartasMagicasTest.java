package carta.magica;

import org.junit.Test;

import carta.CartaMagica;

import static org.junit.Assert.assertEquals;

public class FabricaCartasMagicasTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {
       
    	FabricaCartasMagicas fabrica = new FabricaCartasMagicas(null, null);
    	
    	CartaMagica unaCartaMagica = fabrica.obtenerCarta("Dark Hole");
    	
    	CartaMagica cartaEsperada = new DarkHole(null, null , null);
    	
    	assertEquals(cartaEsperada.getClass(), unaCartaMagica.getClass());
    }
}
