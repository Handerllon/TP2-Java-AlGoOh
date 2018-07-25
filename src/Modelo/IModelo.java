package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.excepciones.NoEsCartaMagicaError;
import Modelo.carta.excepciones.NoEsCartaMonstruo;
import Modelo.carta.excepciones.NoEsCartaTrampa;
import Modelo.carta.monstruo.CartaMonstruo;

public interface IModelo
{
    // ------------------------------------
    // Métodos de juego de cartas.
    // ------------------------------------
    void tomarCarta(Jugador jugador);

    void setCartaMagica(Jugador jugador, Carta carta) throws NoEsCartaMagicaError;

    void activarCartaMagica(Carta carta) throws NoEsCartaMagicaError;

    void setCartaTrampa(Jugador jugador, Carta carta) throws NoEsCartaTrampa;

    void setCartaMonstruo(Carta carta) throws NoEsCartaMonstruo;

    void setCartaMonstruo(Carta carta, Sacrificio sacrificios) throws NoEsCartaMonstruo;

    void summonCartaMonstruo(Carta carta) throws NoEsCartaMonstruo;

    void summonCartaMonstruo(Carta carta, Sacrificio sacrificios) throws NoEsCartaMonstruo;

    boolean requiereSacrificios(Carta carta) throws NoEsCartaMonstruo;

    boolean haySuficientesSacrificios(Carta carta) throws NoEsCartaMonstruo;

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void flipBocaAbajo(Carta carta);

    void flipBocaArriba(Carta carta);

    void setModoAtaque(Carta carta) throws NoEsCartaMonstruo;

    void setModoDefensa(Carta carta) throws NoEsCartaMonstruo;

    void flipCartaMonstruo(CartaMonstruo carta);

    void cambiarModoCartaMonstruo(CartaMonstruo carta);

    // ------------------------------------
    // Métodos de ataques.
    // ------------------------------------
    void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada);

    void atacar(CartaMonstruo cartaAtacante);


}
