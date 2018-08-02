package Modelo.region;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import Modelo.Jugador;
import Modelo.carta.monstruo.AlexandriteDragon;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.GaiaTheFierceKnight;

public class RegionTest {

	
	@Test
	public void test01RegionTieneUnaCartaLuegoDeUsarColocarCarta()
	{
		Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");
        
        RegionMonstruos region = new RegionMonstruos(jugador1);
        
		CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);
		
		region.colocarCarta(carta);
		
		assertEquals(1, region.getCantidadCartas(), 0);
	}
	
	@Test
	public void test02RegionTieneCeroCartasLuegoDeRemoverLaUnicaCarta()
	{
		Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");
        
        RegionMonstruos region = new RegionMonstruos(jugador1);
        
		CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);
		
		region.colocarCarta(carta);
		
		region.removerCarta(carta);
		
		assertEquals(0, region.getCantidadCartas(), 0);
	}
	
	@Test
	public void test03RegionDevuelveLaUltimaCartaQueSeIngreso()
	{
		Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");
        
        RegionMonstruos region = new RegionMonstruos(jugador1);
        
		CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);
		CartaMonstruo carta2 = new AlexandriteDragon(jugador1, jugador2);
		
		region.colocarCarta(carta);
		region.colocarCarta(carta2);
		
		CartaMonstruo carta3 = region.getUltimaCartaEnEntrar();
		
		assertTrue(carta2.getClass() == carta3.getClass());
	}
	
	@Test
	public void test04RegionDevuelveLaUltimaCartaQueSalio()
	{
		Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");
        
        RegionMonstruos region = new RegionMonstruos(jugador1);
        
		CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);
		CartaMonstruo carta2 = new AlexandriteDragon(jugador1, jugador2);
		
		region.colocarCarta(carta);
		region.colocarCarta(carta2);
		
		region.removerCarta(carta);
		
		CartaMonstruo carta3 = region.getUltimaCartaEnSalir();
		
		assertTrue(carta.getClass() == carta3.getClass());
	}
	
	
}
