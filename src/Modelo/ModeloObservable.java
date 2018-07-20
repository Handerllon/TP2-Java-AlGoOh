package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.monstruo.CartaMonstruo;

import java.util.ArrayList;

public interface ModeloObservable
{
    void agregarObsevador(ObservadorDeModelo observador);

    void quitarObservador(ObservadorDeModelo observador);

    void notificarObservadores();

    // Aquí va lo que se le quiere pedir al modelo.

    ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosJugador();

    ArrayList<CartaMonstruo> obtenerCartasEnRegionMonstruosOponente();

    ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasJugador();

    ArrayList<Carta> obtenerCartasEnRegionMagicasYTrampasOponente();

    ArrayList<CartaCampo> obtenerCartasEnRegionCampoJugador();

    ArrayList<CartaCampo> obtenerCartasEnRegionCampoOponente();

    int obtenerNumeroDeCartasRestantesEnMazoJugador();

    int obtenerNumeroDeCartasRestantesEnMazoOponente();

    ArrayList<Carta> obtenerCartasEnLaManoDelJugador();

    ArrayList<Carta> obtenerCartasEnLaManoDelOponente();

    // ------------------------------------
    // Métodos de orientación de cartas.
    // ------------------------------------
    void voltearBocaAbajo(Carta carta);

    void voltearBocaArriba(Carta carta);

    void ponerEnModoAtaque(Carta carta);

    void ponerEnModoDefensa(Carta carta);

    void voltearCartaMonstruo(CartaMonstruo carta);

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

    void activarCartaMagica(Jugador jugador,Carta carta);

    void jugarCartaTrampa(Jugador jugador,Carta carta);

    void jugarCartaMagicaBocaArriba(Jugador jugador,Carta carta);

    void jugarCartaMagicaBocaAbajo(Jugador jugador,Carta carta);
}
