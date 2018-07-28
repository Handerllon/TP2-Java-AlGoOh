package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.trampa.CartaTrampa;

public interface ModeloInterfaz
{
    // TODO: el modelo es el que debe avisarle a la maquina de turnos si se pudo llevar a cabo la accion?
    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    void tomarCarta(Jugador jugador);

    void setCartaMagica(Jugador jugador, CartaMagica carta);

    void activarCartaMagica(CartaMagica carta);

    void setCartaTrampa(Jugador jugador, CartaTrampa carta);

    void setCartaMonstruo(CartaMonstruo carta);

    void setCartaMonstruo(CartaMonstruo carta, Sacrificio sacrificios);

    void summonCartaMonstruo(CartaMonstruo carta);

    void summonCartaMonstruo(CartaMonstruo carta, Sacrificio sacrificios);

    boolean requiereSacrificios(CartaMonstruo carta);

    boolean haySuficientesSacrificios(CartaMonstruo carta);

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
