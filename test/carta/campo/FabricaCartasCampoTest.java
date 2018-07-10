package carta.campo;

import org.junit.Test;

import carta.CartaCampo;

import static org.junit.Assert.assertEquals;

public class FabricaCartasCampoTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {
       
    	FabricaCartasCampo fabrica = new FabricaCartasCampo(null, null);
    	
    	CartaCampo unaCartaCampo = fabrica.obtenerCarta("Sogen");
    	
    	CartaCampo cartaEsperada = new Sogen(null, null , null);
    	
    	assertEquals(cartaEsperada.getClass(), unaCartaCampo.getClass());
    }
}