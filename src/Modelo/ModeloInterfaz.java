package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
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

    void setCartaMagica(Jugador jugador, CartaMagica carta);

    void activarCartaMagicaDesdeRegionMyT(Jugador jugador, CartaMagica carta);

    void activarCartaMagicaDesdeMano(Jugador jugador, CartaMagica carta);

    void setCartaTrampa(Jugador jugador, CartaTrampa carta);

    void activarCartaTrampa(Jugador jugador, CartaTrampa carta);

    void activarCartaCampo(Jugador jugador, CartaCampo carta);

    void setCartaMonstruo(Jugador jugador, CartaMonstruo carta);

    void setCartaMonstruo(Jugador jugador, CartaMonstruo carta, Sacrificio sacrificios);

    void summonCartaMonstruo(Jugador jugador, CartaMonstruo carta);

    void summonCartaMonstruo(Jugador jugador, CartaMonstruo carta, Sacrificio sacrificios);

    Sacrificio getSacrificio(CartaMonstruo carta);
    
    boolean requiereSacrificios(CartaMonstruo carta);

    boolean haySuficientesCartasParaSacrificar(CartaMonstruo carta);

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
