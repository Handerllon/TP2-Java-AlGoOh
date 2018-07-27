package AlGoOh;

import Modelo.Jugador;
import Modelo.Modelo;
import Modelo.carta.FabricaCartas;
import Modelo.carta.Mano;
import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.finDeJuego.CausaFinJuego;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlGoOhTest2
{
    //Colocar un monstruo de cada lado del campo. Activo la Modelo.carta mágica Wasteland y
    //verificar que de un lado del campo, el ataque del monstruo aumenta en 200 puntos y
    //del otro lado del campo, se aumenta la defensa del monstruo en 300 puntos.
    @Test
    public void test01SeColocaCartaEnRegionDeMonstruosYSeActivaCartaWasteland()
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
        jugador2.enviarARegion(cartaJugador2);

        CartaCampo cartaWasteland = fabricaCartasJugador1.crearCartaCampo("Wasteland");
        cartaWasteland.cambiarOrientacion();
        jugador1.enviarARegion(cartaWasteland);

        int puntosDeAtaqueEsperadosCartaJugador = 1000 + 200;
        int puntosDeDefensaEsperadosCartaOponente = 2100 + 300;

        assertEquals(puntosDeAtaqueEsperadosCartaJugador, cartaJugador1.getPuntos());
        assertEquals(puntosDeDefensaEsperadosCartaOponente, cartaJugador2.getPuntos());

        jugador1.destruirCarta(cartaWasteland);
        puntosDeAtaqueEsperadosCartaJugador = 1000 + 200 - 200;
        puntosDeDefensaEsperadosCartaOponente = 2100 + 300 - 300;
        assertEquals(puntosDeAtaqueEsperadosCartaJugador, cartaJugador1.getPuntos());
        assertEquals(puntosDeDefensaEsperadosCartaOponente, cartaJugador2.getPuntos());
    }

    @Test
    public void test01bisSeColocaCartaWastelandActivaYSeColocanCartasEnRegionDeMonstruos()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaCampo cartaWasteland = fabricaCartasJugador1.crearCartaCampo("Wasteland");
        cartaWasteland.cambiarOrientacion();
        jugador1.enviarARegion(cartaWasteland);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Ancient Brain");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");

        jugador2.enviarARegion(cartaJugador2);

        int puntosDeAtaqueEsperadosCartaJugador = 1000 + 200;
        int puntosDeDefensaEsperadosCartaOponente = 2100 + 300;

        assertEquals(puntosDeAtaqueEsperadosCartaJugador, cartaJugador1.getPuntos());
        assertEquals(puntosDeDefensaEsperadosCartaOponente, cartaJugador2.getPuntos());
    }

    //    Colocar un monstruo de cada lado del campo. activo la Modelo.carta mágica Sogen y
    //    verificar que de un lado del campo, la defensa del monstruo aumenta en 500 puntos
    //    y del otro lado del campo, se aumenta el ataque del monstruo en 200 puntos.
    @Test
    public void test02SeColocanMonstruosYSeUsaCartaSogen()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Ancient Brain");
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        cartaJugador2.cambiarModo();
        jugador2.enviarARegion(cartaJugador2);

        CartaCampo cartaSogen = fabricaCartasJugador1.crearCartaCampo("Sogen");
        cartaSogen.cambiarOrientacion();
        jugador1.enviarARegion(cartaSogen);

        int puntosDeDefensaEsperadosCartaJugador = 700 + 500;
        int puntosDeAtaqueEsperadosCartaOponente = 100 + 200;

        assertEquals(puntosDeDefensaEsperadosCartaJugador, cartaJugador1.getPuntos());
        assertEquals(puntosDeAtaqueEsperadosCartaOponente, cartaJugador2.getPuntos());

        jugador1.destruirCarta(cartaSogen);
        puntosDeDefensaEsperadosCartaJugador = 700 + 500 - 500;
        puntosDeAtaqueEsperadosCartaOponente = 100 + 200 - 200;

        assertEquals(puntosDeDefensaEsperadosCartaJugador, cartaJugador1.getPuntos());
        assertEquals(puntosDeAtaqueEsperadosCartaOponente, cartaJugador2.getPuntos());
    }

    //    Activar la Modelo.carta mágica Olla de la codicia, y verificar que tomo 2 cartas del mazo.
    @Test
    public void test03SeUsaCartaOllaDeLaCodiciaYSeVerificaQueLaCantidadDeCartasEnLaManoSeanCorrectas()
    {
        Jugador jugador1 = new Jugador("Juan Pablo");
        Jugador jugador2 = new Jugador("Juan Carlos");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        CartaMagica cartaOllaDeLaCodicia = fabricaCartasJugador1.crearCartaMagica("Pot of Greed");

        jugador1.getMano().agregarCarta(cartaOllaDeLaCodicia);

        int cartasAntesDeJugar = jugador1.cantidadDeCartasEnMano();

        jugador1.enviarARegion(cartaOllaDeLaCodicia);
        cartaOllaDeLaCodicia.efecto();

        int cartasDespuesDeJugar = jugador1.cantidadDeCartasEnMano();

        int diferencia = cartasDespuesDeJugar - cartasAntesDeJugar;

        assertEquals(diferencia, (2 - 1));
    }

    //    Colocar 2 monstruos en el campo enemigo, con diferente ataque. Activo la Modelo.carta
    //    mágica Fisura, y verificar que el de menor ataque es destruido.
    @Test
    public void test04SeJueganDosMonstruosEnemigosSeUsaLaCartaFisuraYElDeMenorAtaqueMuere()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        //Carta con mayor ataque
        CartaMonstruo unaCartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Ancient Brain");
        unaCartaJugador2.cambiarModo();
        jugador2.enviarARegion(unaCartaJugador2);

        //Carta con menor ataque
        CartaMonstruo otraCartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        otraCartaJugador2.cambiarModo();
        jugador2.enviarARegion(otraCartaJugador2);

        CartaMagica cartaFisura = fabricaCartasJugador1.crearCartaMagica("Fissure");
        cartaFisura.cambiarOrientacion();
        jugador1.enviarARegion(cartaFisura);
        cartaFisura.efecto();

        assertTrue(jugador2.cartaEstaEnCementerio(otraCartaJugador2));

        assertFalse(jugador2.cartaEstaEnCementerio(unaCartaJugador2));
    }

    //    Colocar un monstruo en el campo enemigo. invoco a Jinzo #7 en mi lado del campo.
    //    Verificar que puedo atacar a los puntos de vida directamente.
    @Test
    public void test05SeColocaJinzoYSeAtacanLosPuntosDeVidaDirectamente()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo cartaJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Jinzo #7");
        cartaJugador1.cambiarModo();
        jugador1.enviarARegion(cartaJugador1);

        CartaMonstruo cartaJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        cartaJugador2.cambiarModo();
        jugador2.enviarARegion(cartaJugador2);

        jugador1.atacar(cartaJugador1);

        int puntosDeVidaEsperados = 8000 - 500;

        assertEquals(puntosDeVidaEsperados, jugador2.getPuntosDeVida(), 0);
    }

    //    Invocar 3 dragones blancos de ojos azules, al Dragón definitivo de ojos azules
    //    sacrificando los 3 dragones el lado del campo del jugador que los invocó.
    @Test
    public void test06SeJuegan3DragonesBlancosDeOjosAzulesYLuegoElDragonDefinitivoDeOjosAzules()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);

        //Necesitamos sacrificios para invocar a todos los dragones
        CartaMonstruo primerSacrificio1Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        jugador1.enviarARegion(primerSacrificio1Jugador1);
        CartaMonstruo primerSacrificio2Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Bitron");
        jugador1.enviarARegion(primerSacrificio2Jugador1);
        Sacrificio sacrificios1 = new Sacrificio();
        sacrificios1.agregarCarta(primerSacrificio1Jugador1);
        sacrificios1.agregarCarta(primerSacrificio2Jugador1);
        CartaMonstruo primerDragonBlancoJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Blue-Eyes White Dragon");
        jugador1.enviarARegion(primerDragonBlancoJugador1, sacrificios1);

        CartaMonstruo segundoSacrificio1Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        jugador1.enviarARegion(segundoSacrificio1Jugador1);
        CartaMonstruo segundoSacrificio2Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Bitron");
        jugador1.enviarARegion(segundoSacrificio2Jugador1);
        Sacrificio sacrificios2 = new Sacrificio();
        sacrificios2.agregarCarta(segundoSacrificio1Jugador1);
        sacrificios2.agregarCarta(segundoSacrificio2Jugador1);
        CartaMonstruo segundoDragonBlancoJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Blue-Eyes White Dragon");
        jugador1.enviarARegion(segundoDragonBlancoJugador1, sacrificios2);

        CartaMonstruo tercerSacrificio1Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        jugador1.enviarARegion(tercerSacrificio1Jugador1);
        CartaMonstruo tercerSacrificio2Jugador1 = fabricaCartasJugador1.crearCartaMonstruo("Bitron");
        jugador1.enviarARegion(tercerSacrificio2Jugador1);
        Sacrificio sacrificios3 = new Sacrificio();
        sacrificios3.agregarCarta(tercerSacrificio1Jugador1);
        sacrificios3.agregarCarta(tercerSacrificio2Jugador1);
        CartaMonstruo tercerDragonBlancoJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Blue-Eyes White Dragon");
        jugador1.enviarARegion(tercerDragonBlancoJugador1, sacrificios3);

        Sacrificio sacrificioFinal = new Sacrificio();
        sacrificioFinal.agregarCarta(primerDragonBlancoJugador1);
        sacrificioFinal.agregarCarta(segundoDragonBlancoJugador1);
        sacrificioFinal.agregarCarta(tercerDragonBlancoJugador1);

        CartaMonstruo dragonDefinitivoDeOjosAzulesJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Blue-Eyes Ultimate Dragon");
        jugador1.enviarARegion(dragonDefinitivoDeOjosAzulesJugador1, sacrificioFinal);

        assertTrue(jugador1.cartaEstaEnCementerio(primerDragonBlancoJugador1));
        assertTrue(jugador1.cartaEstaEnCementerio(segundoDragonBlancoJugador1));
        assertTrue(jugador1.cartaEstaEnCementerio(tercerDragonBlancoJugador1));

        assertTrue(jugador1.cartaEstaEnRegionMonstruos(dragonDefinitivoDeOjosAzulesJugador1));
    }

    //    Colocar al Insecto come-hombres, en posición de defensa boca abajo. Invocar un
    //    monstruo enemigo y atacar al insecto. activo el efecto de volteo, señalando al
    //    atacante como objetivo, verificar que este se destruye, y que mi monstruo sigue en el
    //    campo. Verificar que nadie sufre daño a los puntos de vida.
    @Test
    public void test07UnMonstruoAtacaAlInsectoComeHombresQueSeEncuentraBocaAbajoYEsteEsDestruido()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo insectoComeHombresJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Man-Eater Bug");
        jugador1.enviarARegion(insectoComeHombresJugador1);

        CartaMonstruo monstruoJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        monstruoJugador2.cambiarModo();
        jugador2.enviarARegion(monstruoJugador2);

        jugador2.atacar(monstruoJugador2, insectoComeHombresJugador1);

        assertTrue(jugador2.cartaEstaEnCementerio(monstruoJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(insectoComeHombresJugador1));

        assertEquals(8000, jugador1.getPuntosDeVida(), 0);
        assertEquals(8000, jugador2.getPuntosDeVida(), 0);
    }

    //    Colocar un monstruo del lado enemigo, luego coloco la carta trampa Cilindro mágico
    //    de mi lado del campo. Atacar con el monstruo y verificar que se activa la carta
    //    trampa, se niega el ataque y el oponente recibe el daño directamente en sus puntos
    //    de vida.
    @Test
    public void test08SeColocaMonstruoDelLadoEnemigoEsteAtacaYSeActivaCorrectamenteLaCartaTrampaMagicCylinder()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo monstruoJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Charcoal Inpachi");
        monstruoJugador2.cambiarModo();
        jugador2.enviarARegion(monstruoJugador2);

        CartaTrampa cartaTrampaJugador1 = fabricaCartasJugador1.crearCartaTrampa("Magic Cylinder");
        cartaTrampaJugador1.cambiarOrientacion();
        jugador1.enviarARegion(cartaTrampaJugador1);

        cartaTrampaJugador1.efecto(monstruoJugador2);
        jugador2.atacar(monstruoJugador2);
        cartaTrampaJugador1.deshacerEfecto();

        int puntosDeVidaEsperadosJugador2 = 8000 - 100;

        assertEquals(puntosDeVidaEsperadosJugador2, jugador2.getPuntosDeVida());
        assertEquals(8000, jugador1.getPuntosDeVida());
    }

    //Coloco un monstruo en posición de ataque (CharcoalInpachi) y la carta trampa Reinforcements de mi
    //lado del campo, coloco un monstruo en el campo enemigo (Bitron) (con 100 puntos mas de
    //ataque que el primero) y atacar al primer monstruo. Verificar que se activa la carta
    //trampa, y el monstruo enemigo es destruido y se infligió 400 puntos de daño a la
    //vida del otro jugador.
    //
    //TODO : Los puntos de ataque del monstruo afectado por la carta trampa deben volver a la normalidad una vez que termina el turno, ver como implementar
    @Test
    public void test09SeColocanDosMonstruosConLaTrampaReinforcementsEnJuegoSeAtacaYSeVerificaQueSeActiveLaTrampa()
    {
        Jugador jugador1 = new Jugador("J1");
        Jugador jugador2 = new Jugador("J2");

        jugador1.setOponente(jugador2);
        jugador2.setOponente(jugador1);

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(jugador1, jugador2);
        FabricaCartas fabricaCartasJugador2 = new FabricaCartas(jugador2, jugador1);

        CartaMonstruo monstruoJugador1 = fabricaCartasJugador1.crearCartaMonstruo("Charcoal Inpachi");
        monstruoJugador1.cambiarModo();
        jugador1.enviarARegion(monstruoJugador1);

        CartaTrampa cartaTrampaJugador1 = fabricaCartasJugador1.crearCartaTrampa("Reinforcements");
        cartaTrampaJugador1.cambiarOrientacion();
        jugador1.enviarARegion(cartaTrampaJugador1);

        CartaMonstruo monstruoJugador2 = fabricaCartasJugador2.crearCartaMonstruo("Bitron");
        monstruoJugador2.cambiarModo();
        jugador2.enviarARegion(monstruoJugador2);

        cartaTrampaJugador1.efecto(monstruoJugador2);
        jugador2.atacar(monstruoJugador2, monstruoJugador1);
        cartaTrampaJugador1.deshacerEfecto();

        int puntosDeVidaEsperadosJugador2 = 8000 - 400;

        assertTrue(jugador2.cartaEstaEnCementerio(monstruoJugador2));
        assertFalse(jugador1.cartaEstaEnCementerio(monstruoJugador1));
        assertEquals(puntosDeVidaEsperadosJugador2, jugador2.getPuntosDeVida(), 0);
        assertEquals(8000, jugador1.getPuntosDeVida(), 0);
    }

    //Extraer todas las cartas del mazo, y verificar que la partida terminó y el jugador
    //perdió.
    @Test
    public void test10SeExtraenTodasLasCartasDelMazoDelJugadorYEstePierde()
    {

        Modelo modelo = Modelo.getInstancia();
        ;
        modelo.setNombreJugador("J1");
        modelo.setNombreOponente("J1");

        Jugador jugador = modelo.getJugador();

        for (int k = 0; k <= 40; k++)
            jugador.getMazo().tomarCarta();

        CausaFinJuego causaFinJuego = modelo.getCausaFinJuego();
        assertTrue(causaFinJuego.getCausa() == "Sin cartas en el mazo");
    }

    //    Colocar las 5 partes de exodia en la mano de un jugador, verificar que la partida
    //    termina y que ese jugador resultó ganador.
    @Test
    public void test11SeColocanLasCincoPartesDeExodiaEnLaManoDeUnJugadorYEsteGanaLaPartida()
    {
        Modelo modelo = Modelo.getInstancia();
        ;
        modelo.setNombreJugador("J1");
        modelo.setNombreOponente("J1");

        Mano manoJugador1 = modelo.getJugador().getMano();

        FabricaCartas fabricaCartasJugador1 = new FabricaCartas(null, null);
        CartaMonstruo exodiaParte1 = fabricaCartasJugador1.crearCartaMonstruo("Exodia The Forbidden One");
        CartaMonstruo exodiaParte2 = fabricaCartasJugador1.crearCartaMonstruo("Left Arm Of The Forbidden One");
        CartaMonstruo exodiaParte3 = fabricaCartasJugador1.crearCartaMonstruo("Left Leg Of The Forbidden One");
        CartaMonstruo exodiaParte4 = fabricaCartasJugador1.crearCartaMonstruo("Right Arm Of The Forbidden One");
        CartaMonstruo exodiaParte5 = fabricaCartasJugador1.crearCartaMonstruo("Right Leg Of The Forbidden One");

        manoJugador1.agregarCarta(exodiaParte1);
        manoJugador1.agregarCarta(exodiaParte2);
        manoJugador1.agregarCarta(exodiaParte3);
        manoJugador1.agregarCarta(exodiaParte4);
        manoJugador1.agregarCarta(exodiaParte5);

        CausaFinJuego causaFinJuego = modelo.getCausaFinJuego();
        assertTrue(causaFinJuego.getCausa() == "Cinco partes de Exodia reunidas");
    }
}
