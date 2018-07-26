package Modelo;

import Controlador.excepciones.NoSePuedeAtacarError;
import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.excepciones.NoEsCartaMagicaError;
import Modelo.excepciones.NoEsCartaMonstruo;
import Modelo.excepciones.NoEsCartaTrampa;

public interface IModelo
{
    // TODO: el modelo es el que debe avisarle a la maquina de turnos si se pudo llevar a cabo la accion.
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
    void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaAtacada) throws NoSePuedeAtacarError;

    void atacar(CartaMonstruo cartaAtacante) throws NoSePuedeAtacarError;
}
