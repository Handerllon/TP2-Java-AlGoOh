package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.monstruo.CartaMonstruo;

public interface IModelo
{
    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipBocaAbajo(Carta carta);

    void flipBocaArriba(Carta carta);

    void setModoAtaque(Carta carta);

    void setModoDefensa(Carta carta);

    void flipCartaMonstruo(CartaMonstruo carta);

    void cambiarModoCartaMonstruo(CartaMonstruo carta);

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada);

    void atacar(CartaMonstruo cartaAtacante);

    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    void tomarCarta(Jugador jugador);

    void activarCartaMagica(Jugador jugador, Carta carta);

    void setCartaTrampa(Jugador jugador, Carta carta);

    void setCartaMagica(Jugador jugador, Carta carta);

    boolean requiereSacrificios(Carta carta);

    void setCartaMonstruo(Carta carta);

    boolean haySuficientesSacrificios(Carta carta);

    void setCartaMonstruo(Carta carta, Sacrificio sacrificios);
}
