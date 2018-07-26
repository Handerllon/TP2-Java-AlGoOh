package AlGoOh;

import Modelo.Jugador;
import Modelo.carta.FabricaCartas;
import Modelo.carta.Sacrificio;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlGoOhTest
{
    @Test
    public void test01SeColocaUnaCartaMonstruoEnAtaque()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMonstruo carta = fabricaCartasJugador1.crearCartaMonstruo("Alexandrite Dragon");
        carta.cambiarModo();
        jugador1.enviarARegion(carta);

        assertTrue(jugador1.cartaEstaEnRegionMonstruos(carta));
        assertTrue(carta.enAtaque());
    }

    @Test
    public void test02SeColocaUnaCartaMonstruoEnDefensa()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMonstruo carta = fabricaCartasJugador1.crearCartaMonstruo("Alexandrite Dragon");

        jugador1.enviarARegion(carta);

        assertTrue(jugador1.cartaEstaEnRegionMonstruos(carta));
        assertTrue(carta.enDefensa());
    }

    @Test
    public void test03ColocarCartaMagicaEnCampoBocaAbajoNoActivaEfecto()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMagica carta = fabricaCartasJugador1.crearCartaMagica("Dark Hole");
        jugador1.enviarARegion(carta);

        assertTrue(jugador1.cartaEstaEnRegionMagicasYTrampa(carta));
    }

    @Test
    public void test04ColocarCartaTrampaEnCampoBocaAbajoNoActivaEfecto()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaTrampa carta = fabricaCartasJugador1.crearCartaTrampa("Magic Cylinder");
        jugador1.enviarARegion(carta);

        assertTrue(jugador1.cartaEstaEnRegionMagicasYTrampa(carta));
    }

    @Test
    public void test05MandarUnaCartaAlCementerioSeQuedaEnCementerio()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMonstruo carta = fabricaCartasJugador1.crearCartaMonstruo("Alexandrite Dragon");
        jugador1.enviarARegion(carta);
        jugador1.destruirCarta(carta);

        assertTrue(jugador1.cartaEstaEnCementerio(carta));
    }

    @Test
    public void test06JugadorAtacaAMonstruoOponenteEnPosicionDeAtaqueConMayorAtaqueQueElDelJugador()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Ancient Brain");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        cartaJugador2.cambiarModo();
        jugador2.enviarARegion(cartaJugador2);

        jugador1.atacar(cartaJugador1, cartaJugador2);

        assertEquals(8000 - 900, jugador2.getPuntosDeVida(), 0);

        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }

    @Test
    public void test07JugadorAtacaAMonstruoEnPosicionDeAtaqueConMenorAtaqueQueElDelJugador()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Ancient Brain");
        cartaJugador2.cambiarModo();
        jugador2.enviarARegion(cartaJugador2);

        jugador1.atacar(cartaJugador1, cartaJugador2);

        assertEquals(8000 - 900, jugador1.getPuntosDeVida(), 0);

        assertTrue(jugador1.cartaEstaEnCementerio(cartaJugador1));
        assertFalse(jugador2.cartaEstaEnCementerio(cartaJugador2));
    }

    @Test
    public void test08JugadorAtacaAMonstruoEnPosicionDeAtaqueConIgualAtaqueQueElDelJugador()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Ancient Brain");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Ancient Brain");
        cartaJugador2.cambiarModo();
        jugador2.enviarARegion(cartaJugador2);

        jugador1.atacar(cartaJugador1, cartaJugador2);

        assertEquals(8000, jugador1.getPuntosDeVida(), 0);
        assertEquals(8000, jugador2.getPuntosDeVida(), 0);

        assertTrue(jugador1.cartaEstaEnCementerio(cartaJugador1));
        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));
    }

    @Test
    public void test09JugadorAtacaMonstruoEnPosicionDeDefensaConMenorDefensaQueElAtaqueDelMismo()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Ancient Brain");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Alexandrite Dragon");
        jugador2.enviarARegion(cartaJugador2);

        jugador1.atacar(cartaJugador1, cartaJugador2);

        assertEquals(8000, jugador2.getPuntosDeVida(), 0);

        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }

    @Test
    public void test10JugadorAtacaMonstruoEnPosicionDeDefensaConMayorDefensaQueElAtaqueDelMismo()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Alexandrite Dragon");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        jugador2.enviarARegion(cartaJugador2);

        jugador1.atacar(cartaJugador1, cartaJugador2);

        assertEquals(8000 - 100, jugador1.getPuntosDeVida(), 0);

        assertFalse(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }

    @Test
    public void test11JugadorAtacaAMonstruoEnPosicionDeDefensaConIgualDefensaQueElAtaqueDelMismo()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Alexandrite Dragon");
        jugador2.enviarARegion(cartaJugador2);

        jugador1.atacar(cartaJugador1, cartaJugador2);

        assertEquals(8000, jugador1.getPuntosDeVida(), 0);
        assertEquals(8000, jugador2.getPuntosDeVida(), 0);

        assertFalse(jugador2.cartaEstaEnCementerio(cartaJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(cartaJugador1));
    }

    @Test
    public void test12SeJuegaAgujeroNegroYMuerenTodosLosMonstruosDeAmbasRegionesYLosPuntosDeVidaQuedanIguales()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Alexandrite Dragon");
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        jugador2.enviarARegion(cartaJugador2);

        CartaMagica cartaMagicaJugador1 = fabricaCartasJugador1.crearCartaMagica("Dark Hole");
        cartaMagicaJugador1.cambiarOrientacion();
        jugador1.enviarARegion(cartaMagicaJugador1);
        cartaMagicaJugador1.efecto();

        assertTrue(jugador1.cartaEstaEnCementerio(cartaJugador1));
        assertTrue(jugador2.cartaEstaEnCementerio(cartaJugador2));

        assertEquals(8000, jugador1.getPuntosDeVida(), 0);
        assertEquals(8000, jugador2.getPuntosDeVida(), 0);
    }

    @Test
    public void test13SeColocaMonstruoEnCampoYSeColocaMonstruoDe6EstrellasSacrificandoCorrectamenteAlPrimero()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMonstruo carta1Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        jugador1.enviarARegion(carta1Jugador1);

        CartaMonstruo carta2Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Amphibian Beast");
        Sacrificio sacrificios = new Sacrificio();
        sacrificios.agregarCarta(carta1Jugador1);
        jugador1.enviarARegion(carta2Jugador1, sacrificios);

        assertTrue(jugador1.cartaEstaEnCementerio(carta1Jugador1));
        assertTrue(jugador1.cartaEstaEnRegionMonstruos(carta2Jugador1));
    }

    @Test
    public void test14SeColocan2MonstruosEnCampoYSeColocaMonstruoDe8EstrellasSacrificandoCorrectamenteAlLos2Primeros()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMonstruo carta1Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        jugador1.enviarARegion(carta1Jugador1);
        CartaMonstruo carta2Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Bitron");
        jugador1.enviarARegion(carta2Jugador1);

        CartaMonstruo carta3Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Blue-Eyes White Dragon");
        Sacrificio sacrificios = new Sacrificio();
        sacrificios.agregarCarta(carta1Jugador1);
        sacrificios.agregarCarta(carta2Jugador1);
        jugador1.enviarARegion(carta3Jugador1, sacrificios);

        assertTrue(jugador1.cartaEstaEnCementerio(carta1Jugador1));
        assertTrue(jugador1.cartaEstaEnCementerio(carta2Jugador1));
        assertTrue(jugador1.cartaEstaEnRegionMonstruos(carta3Jugador1));
    }
}