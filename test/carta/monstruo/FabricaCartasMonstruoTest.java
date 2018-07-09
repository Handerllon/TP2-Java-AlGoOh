package carta.monstruo;

import org.junit.Test;

import AlGoOh.Jugador;
import carta.CartaMonstruo;

import static org.junit.Assert.assertEquals;

public class FabricaCartasMonstruoTest
{
    @Test
    public void test01SeCreaUnaCartaEnLaFabricaYSeVerificaQueLaClaseCreadaSeaCorrecta()
    {
       
    	FabricaCartasMonstruo fabrica = new FabricaCartasMonstruo(null, null);
    	
    	CartaMonstruo unaCartaMonstruo = fabrica.obtenerCarta("Alexandrite Dragon");
    	
    	CartaMonstruo cartaEsperada = new AlexandriteDragon(null, null , null);
    	
    	assertEquals(cartaEsperada.getClass(), unaCartaMonstruo.getClass());
    }
}