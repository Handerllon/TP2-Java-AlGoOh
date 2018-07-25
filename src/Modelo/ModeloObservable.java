package Modelo;

import Modelo.carta.Carta;
import Modelo.carta.Sacrificio;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.excepciones.NoEsUnaCartaMonstruo;
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

    boolean requiereSacrificios(Carta carta) ;

    void setCartaMonstruo(Carta carta);

    boolean haySuficientesSacrificios(Carta carta) ;

    void setCartaMonstruo(Carta carta, Sacrificio sacrificios) ;
}
