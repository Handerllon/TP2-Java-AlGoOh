package Modelo.carta;

import org.junit.Test;

import Modelo.Jugador;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.GaiaTheFierceKnight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CartaTest
{
    @Test
    public void test01CartaMonstruoEstaBocaArribaLuegoDeCambiarOrientacion()
    {
    	
       Jugador jugador1 = new Jugador("J1");
       Jugador jugador2 = new Jugador("J2");

       jugador1.setOponente(jugador2);
       jugador2.setOponente(jugador1);

       CartaMonstruo carta = new GaiaTheFierceKnight(jugador1, jugador2);
       carta.cambiarOrientacion();

       assertTrue(carta.estaBocaArriba());
    }
        
}