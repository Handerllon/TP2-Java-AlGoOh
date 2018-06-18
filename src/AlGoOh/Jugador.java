package AlGoOh;

import AlGoOh.excepciones.JugadorSinVida;
import areaDeJuego.AreaDeCartas;
import areaDeJuego.Tablero;
import carta.*;

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


        // TODO: Terminar de implementar bien la generación del mazo.
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

    public void disminuirPuntosVida(int puntosARestar)
    {
        this.puntosDeVida -= puntosARestar;
        if (this.puntosDeVida <= 0)
            // TODO: Esto puede ser un trigger para terminar el juego.
            throw new JugadorSinVida();
    }

    public AreaDeCartas regionDeCartas()
    {
        return this.areaDeCartas;
    }


    public void agregarCarta(CartaMonstruo carta, Sacrificio sacrificios)
    {
        tablero.agregarCarta(carta, sacrificios);
    }

    public void agregarCarta(CartaMagica carta)
    {
        tablero.agregarCarta(carta);

    }

    public void atacar(CartaMonstruo cartaAtacante, CartaMonstruo cartaOponente)
    {
        tablero.atacarOponente(cartaAtacante, cartaOponente);
    }

    public boolean cartaEstaEnCementerio(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnCementerio(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnRegionMonstruos(carta);
    }
}

