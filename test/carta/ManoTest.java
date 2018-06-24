package carta;

import AlGoOh.Jugador;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ManoTest {
    @Test
    public void test01CuandoSeCreaLaManoDebeTener5Cartas() {
        Jugador jugador1 = new Jugador("Pepe");
        Jugador jugador2 = new Jugador("Miauricio");
        Mazo mazo = new Mazo(jugador1, jugador2);
        Mano mano = new Mano();

        for (int i = 0; i < 5; i++) {
            mano.agregarCarta(mazo.tomarCarta());
        }

        int cantidadDeCartasEsperadas = 5;

        assertEquals(cantidadDeCartasEsperadas, mano.cantidadDeCartas());

    }
}