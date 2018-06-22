package AlGoOh;

import areaDeJuego.RegionCampo;
import areaDeJuego.RegionCementerio;
import areaDeJuego.RegionMonstruos;
import carta.*;
import carta.magica.DarkHole;
import carta.monstruo.*;
import carta.trampa.MagicCylinder;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlGoOhTest
{

    @Test
    public void test01SeColocaUnaCartaMonstruoEnAtaque()
    {
        CartaMonstruo carta = new AlexandriteDragon();
        carta.cambiarModo();

        RegionMonstruos regionMonstruos = new RegionMonstruos();
        regionMonstruos.colocarCarta(carta);
        
        assertTrue(regionMonstruos.contieneCarta(carta));
        assertTrue(carta.enAtaque());
    }

    @Test
    public void test02SeColocaUnaCartaMonstruoEnDefensa()
    {
        CartaMonstruo carta = new AlexandriteDragon();

        RegionMonstruos regionMonstruos = new RegionMonstruos();
        regionMonstruos.colocarCarta(carta);
        
        assertTrue(regionMonstruos.contieneCarta(carta));
        assertTrue(carta.enDefensa());
    }

    @Test
    public void test03ColocarCartaMagicaEnCampoBocaAbajoNoActivaEfecto()
    {
        CartaMagica carta = new DarkHole();
        RegionCampo regionCampo = new RegionCampo();

        regionCampo.colocarCarta(carta);
        
        assertTrue(regionCampo.contieneCarta(carta));
    }

    @Test
    public void test04ColocarCartaTrampaEnCampoBocaAbajoNoActivaEfecto()
    {
        CartaTrampa carta = new MagicCylinder();
        RegionCampo regionCampo = new RegionCampo();

        regionCampo.colocarCarta(carta);
        
        assertTrue(regionCampo.contieneCarta(carta));
    }

    @Test
    public void test05MandarUnaCartaAlCementerioSeQuedaEnCementerio()
    {
        CartaMonstruo carta = new AlexandriteDragon();

        RegionCementerio cementerio = new RegionCementerio();

        cementerio.colocarCarta(carta);

        assertTrue(cementerio.contieneCarta(carta));

    }

    @Test
    public void test06JugadorAtacaAMonstruoOponenteEnPosicionDeAtaqueConMayorAtaqueQueElDelJugador()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        
        CartaMonstruo cartaJugador1 = new AncientBrain();
        cartaJugador1.cambiarModo();
        jugador1.agregarCarta(cartaJugador1);
        
        CartaMonstruo cartaJugador2 = new CharcoalInpachi();
        cartaJugador2.cambiarModo();
        jugador2.agregarCarta(cartaJugador2);
        
        jugador1.atacar(cartaJugador1, cartaJugador2);
        
        assertTrue(jugador2.getPuntosDeVida() == (8000-900));

        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }

    @Test
    public void test07JugadorAtacaAMonstruoEnPosicionDeAtaqueConMenorAtaqueQueElDelJugador()
    {
    	Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        
        CartaMonstruo cartaJugador1 = new CharcoalInpachi();
        cartaJugador1.cambiarModo();
        jugador1.agregarCarta(cartaJugador1);
        
        CartaMonstruo cartaJugador2 = new AncientBrain();
        cartaJugador2.cambiarModo();
        jugador2.agregarCarta(cartaJugador2);
        
        jugador1.atacar(cartaJugador1, cartaJugador2);
        
        assertTrue(jugador1.getPuntosDeVida() == (8000-900));

        assertTrue(jugador1.cartaEstaEnCementerio(cartaJugador1));
        assertFalse(jugador2.cartaEstaEnCementerio(cartaJugador2));
    }

    @Test
    public void test08JugadorAtacaAMonstruoEnPosicionDeAtaqueConIgualAtaqueQueElDelJugador()
    {
    	Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        
        CartaMonstruo cartaJugador1 = new AncientBrain();
        cartaJugador1.cambiarModo();
        jugador1.agregarCarta(cartaJugador1);
        
        CartaMonstruo cartaJugador2 = new AncientBrain();
        cartaJugador2.cambiarModo();
        jugador2.agregarCarta(cartaJugador2);
        
        jugador1.atacar(cartaJugador1, cartaJugador2);

        assertTrue(jugador1.getPuntosDeVida() == 8000);
        assertTrue(jugador2.getPuntosDeVida() == 8000);

        assertTrue(jugador1.cartaEstaEnCementerio(cartaJugador1));
        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));

    }

    @Test
    public void test09JugadorAtacaMonstruoEnPosicionDeDefensaConMenorDefensaQueElAtaqueDelMismo()
    {
    	Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        
        CartaMonstruo cartaJugador1 = new AncientBrain();
        cartaJugador1.cambiarModo();
        jugador1.agregarCarta(cartaJugador1);
        
        CartaMonstruo cartaJugador2 = new AlexandriteDragon();
        jugador2.agregarCarta(cartaJugador2);
        
        jugador1.atacar(cartaJugador1, cartaJugador2);
        
        assertTrue(jugador2.getPuntosDeVida() == (8000));

        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }

    @Test
    public void test10JugadorAtacaMonstruoEnPosicionDeDefensaConMayorDefensaQueElAtaqueDelMismo()
    {
    	Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        
        CartaMonstruo cartaJugador1 = new AlexandriteDragon();
        cartaJugador1.cambiarModo();
        jugador1.agregarCarta(cartaJugador1);
        
        CartaMonstruo cartaJugador2 = new CharcoalInpachi();
        jugador2.agregarCarta(cartaJugador2);
        
        jugador1.atacar(cartaJugador1, cartaJugador2);
        
        assertTrue(jugador1.getPuntosDeVida() == (8000-100));

        assertFalse(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }

    @Test
    public void test11JugadorAtacaAMonstruoEnPosicionDeDefensaConIgualDefensaQueElAtaqueDelMismo(){
    	Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        
        CartaMonstruo cartaJugador1 = new CharcoalInpachi();
        cartaJugador1.cambiarModo();
        jugador1.agregarCarta(cartaJugador1);
        
        CartaMonstruo cartaJugador2 = new AlexandriteDragon();
        jugador2.agregarCarta(cartaJugador2);
        
        jugador1.atacar(cartaJugador1, cartaJugador2);
        
        assertTrue(jugador1.getPuntosDeVida() == (8000));
        assertTrue(jugador2.getPuntosDeVida() == (8000));

        assertFalse(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }
    
    @Test
    public void test12SeJuegaAgujeroNegroYMuerenTodosLosMonstruosDeAmbasRegionesYLosPuntosDeVidaQuedanIguales()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        jugador2.establecerOponente(jugador1);
        
        CartaMonstruo cartaJugador1 = new AlexandriteDragon();
        jugador1.agregarCarta(cartaJugador1);

        CartaMonstruo cartaJugador2 = new CharcoalInpachi();
        jugador2.agregarCarta(cartaJugador2);

        CartaMagica cartaMagicaJugador1 = new DarkHole();
        cartaMagicaJugador1.cambiarOrientacion();
        jugador1.agregarCarta(cartaMagicaJugador1);

        assertTrue(jugador1.cartaEstaEnCementerio(cartaJugador1));
        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));

        assertTrue(jugador1.getPuntosDeVida() == 8000);
        assertTrue(jugador2.getPuntosDeVida() == 8000);

    }/*Colocar monstruos en ambos lados del campo. Colocar Agujero negro boca arriba
            (es decir, se activa el efecto). Verificar que se destruyeron todos los monstruo de
    ambos lados del campo, y que nadie recibió daño alguno.*/

    @Test
    public void test13SeColocaMonstruoEnCampoYSeColocaMonstruoDe6EstrellasSacrificandoCorrectamenteAlPrimero()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");
        
        jugador1.establecerOponente(jugador2);
        jugador2.establecerOponente(jugador1);

        CartaMonstruo carta1Jugador1 = new CharcoalInpachi();
        jugador1.agregarCarta(carta1Jugador1);

        CartaMonstruo carta2Jugador1 = new AmphibianBeast();
        Sacrificio sacrificios = new Sacrificio();
        sacrificios.agregarCarta(carta1Jugador1);
        jugador1.agregarCarta(carta2Jugador1,sacrificios);

        assertTrue(jugador1.cartaEstaEnCementerio(carta1Jugador1));
        assertTrue(jugador1.cartaEstaEnRegionMonstruos(carta2Jugador1));

    }/*Se coloca un monstruo en el campo, se quiere colocar un monstruo de 5 o 6
    estrellas que requiere sacrificio. se verifica que se convocó al monstruo y se
    destruyó el primero.*/

    @Test
    public void test14SeColocan2MonstruosEnCampoYSeColocaMonstruoDe8EstrellasSacrificandoCorrectamenteAlLos2Primeros()
    {
    	Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.establecerOponente(jugador2);
        jugador2.establecerOponente(jugador1);

        CartaMonstruo carta1Jugador1 = new CharcoalInpachi();
        jugador1.agregarCarta(carta1Jugador1);
        CartaMonstruo carta2Jugador1 = new Bitron();
        jugador1.agregarCarta(carta2Jugador1);

        CartaMonstruo carta3Jugador1 = new BlueEyesWhiteDragon();
        Sacrificio sacrificios = new Sacrificio();
        sacrificios.agregarCarta(carta1Jugador1);
        sacrificios.agregarCarta(carta2Jugador1);
        jugador1.agregarCarta(carta3Jugador1,sacrificios);

        assertTrue(jugador1.cartaEstaEnCementerio(carta1Jugador1));
        assertTrue(jugador1.cartaEstaEnCementerio(carta2Jugador1));
        assertTrue(jugador1.cartaEstaEnRegionMonstruos(carta3Jugador1));
    }/*Se colocan 2 monstruo en el campo, se quiere colocar un monstruo de 7 o más
    estrellas que requiere 2 sacrificios. se verifica que se convocó al monstruo y se
    destruyeron los demás.*/
}