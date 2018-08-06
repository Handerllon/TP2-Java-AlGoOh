package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;

public interface ModeloInterfaz
{
    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    void tomarCarta(Jugador jugador);

    void setCartaMagica(CartaMagica carta);

    void activarCartaMagicaDesdeRegionMyT(CartaMagica carta);

    void activarCartaMagicaDesdeMano(CartaMagica carta);

    void setCartaTrampa(CartaTrampa carta);

    void activarCartaCampo(CartaCampo carta);

    void setCartaMonstruo(CartaMonstruo carta);

    void summonCartaMonstruo(CartaMonstruo carta);

    boolean seCumplenCondicionesDeSacrificiosRequeridos(CartaMonstruo carta);

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipCarta(Carta carta);

    void flipBocaAbajo(Carta carta);

    void flipBocaArriba(Carta carta);

    void setModoAtaque(CartaMonstruo carta);

    void setModoDefensa(CartaMonstruo carta);

    void cambiarModoCartaMonstruo(CartaMonstruo carta);

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    // Recibe la carta que ataca y la que es atacada.
    void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada);

    // Recibe la carta que ataca.
    void atacar(CartaMonstruo cartaAtacante);
}
