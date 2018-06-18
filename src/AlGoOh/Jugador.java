package AlGoOh;

import AlGoOh.excepciones.JugadorSinVida;
import areaDeJuego.AreaDeCartas;
import areaDeJuego.Tablero;
import carta.Mano;
import carta.Mazo;
import carta.Sacrificio;
import carta.magica.CartaMagica;
import carta.monstruo.CartaMonstruo;

public class Jugador
{

    private String nombre;
    private Mazo mazo;
    private Mano cartasEnMano;
    private int puntosDeVida;
    private AreaDeCartas areaDeCartas;
    private Tablero tablero;

    public Jugador(String unNombre)
    {

        this.nombre = unNombre;

        int cantidadCartasInicialesMazo = 40;
        this.mazo = new Mazo(cantidadCartasInicialesMazo);

        int cartasATomarInicialmente = 5;
        this.cartasEnMano = new Mano(cartasATomarInicialmente);

        int puntosDeVidaIniciales = 8000;
        this.puntosDeVida = puntosDeVidaIniciales;

        this.areaDeCartas = new AreaDeCartas(this);

//        for (int i = 0; i < cartasATomarInicialmente; i++)
//        {
//            this.cartasEnMano.agregarCarta(mazo.agarrarCarta());
//        }

    }

    public void definirTablero(Tablero tablero)
    {
        this.tablero = tablero;
    }

    public int getPuntosDeVida()
    {
        return this.puntosDeVida;
    }

    public void restarVida(int puntosARestar)
    {
        this.puntosDeVida -= puntosARestar;
        if (this.puntosDeVida <= 0)
            throw new JugadorSinVida();
    }

    public AreaDeCartas areaDeCartas()
    {
        return this.areaDeCartas;
    }


    public void agregarCarta(CartaMonstruo cartaJugador, Sacrificio sacrificios)
    {
        tablero.agregarCarta(cartaJugador, sacrificios);
    }

    public void agregarCarta(CartaMagica cartaJugador)
    {
        tablero.agregarCarta(cartaJugador);

    }

    public boolean cartaEstaEnCementerio(CartaMonstruo cartaJugador)
    {
        return areaDeCartas.cartaEstaEnCementerio(cartaJugador);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnRegionMonstruos(carta);
    }

    public void atacar(CartaMonstruo cartaJugador, CartaMonstruo cartaOponente)
    {
        tablero.atacarOponente(cartaJugador, cartaOponente);
    }
}

