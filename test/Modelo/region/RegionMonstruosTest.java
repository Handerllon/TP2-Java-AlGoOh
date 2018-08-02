package Modelo.region;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Modelo.Jugador;
import Modelo.carta.monstruo.AlexandriteDragon;
import Modelo.carta.monstruo.AncientBrain;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.GaiaTheFierceKnight;

public class RegionMonstruosTest {
	
	@Test
	public void test01RegionDevuelveElMonstruoConMenorAtaque()
	{
		Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");
        
        RegionMonstruos region = new RegionMonstruos(jugador1);
        
		CartaMonstruo carta3 = new GaiaTheFierceKnight(jugador1, jugador2);
		CartaMonstruo carta2 = new AlexandriteDragon(jugador1, jugador2);
		CartaMonstruo carta = new AncientBrain(jugador1,jugador2);
		
		region.colocarCarta(carta);
		region.colocarCarta(carta2);
		region.colocarCarta(carta3);
		
		CartaMonstruo carta4 = region.getMonstruoConMenorAtaque();
		
		assertTrue(carta4.getClass() == carta.getClass());
	}
	
}
