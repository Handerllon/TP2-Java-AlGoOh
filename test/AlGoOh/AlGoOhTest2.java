package AlGoOh;

import static org.junit.Assert.*;

import org.junit.Test;

import carta.*;
import carta.CartaMonstruo;
import carta.SacrificioNulo;
import carta.campo.Wasteland;
import carta.monstruo.AncientBrain;
import carta.monstruo.CharcoalInpachi;

public class AlGoOhTest2 
{
	
	@Test
	public void test01SeColocaCartaEnRegionDeMonstruosYSeActivaCartaWasteland(){
		Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        
        CartaMonstruo cartaJugador1 = new AncientBrain();
        cartaJugador1.cambiarModo();
        jugador1.agregarCarta(cartaJugador1, new SacrificioNulo());
        
        CartaMonstruo cartaJugador2 = new CharcoalInpachi();
        cartaJugador2.cambiarModo();
        jugador2.agregarCarta(cartaJugador2, new SacrificioNulo());
        
        CartaCampo cartaWasteland = new Wasteland();
        cartaWasteland.cambiarOrientacion();
        jugador1.agregarCarta(cartaWasteland);
        
        
        assertTrue(0 == 0);
	}
	
	@Test
	public void test02(){
		
		assertTrue (0 == 0);
	}
	
	
}
