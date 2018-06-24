package AlGoOh;

import AlGoOh.excepciones.JugadorSinVida;
import areaDeJuego.AreaDeCartas;
import carta.*;

public class Jugador
{
    private String nombre;
    private Mazo mazo;
    private Mano cartasEnMano;
    private int puntosDeVida;
    private AreaDeCartas areaDeCartas;
    private Jugador oponente;

    public Jugador(String unNombre)
    {
        this.nombre = unNombre;

        this.mazo = new Mazo(this, this.oponente);

        this.cartasEnMano = new Mano();

        this.puntosDeVida = 8000;

        this.areaDeCartas = new AreaDeCartas();

        int cartasATomarInicialmente = 5;
        this.popularMano(cartasATomarInicialmente);
    }

    public void establecerOponente(Jugador oponente)
    {

        this.oponente = oponente;
    }

    private void popularMano(int cartasATomarInicialmente)
    {

        for (int i = 0; i < cartasATomarInicialmente; i++)
        {
            this.cartasEnMano.agregarCarta(mazo.tomarCarta());
        }
    }

    public void tomarCarta()
    {
        Carta cartaAQuitar;
        while (this.cartasEnMano.manoLlena())
        {
            cartaAQuitar = this.cartasEnMano.quitarUltimaCarta();
            this.areaDeCartas.enviarAlCementerio(cartaAQuitar);
        }

        this.cartasEnMano.agregarCarta(mazo.tomarCarta());
    }

    // --------------------------------------------------------------------
    // Métodos de juego y acciones de cartas.
    // --------------------------------------------------------------------

    public void jugarCarta(CartaMonstruo cartaMonstruo, Sacrificio sacrificio)
    {
        cartaMonstruo.invocar(sacrificio);
    }

    public void jugarCarta(CartaMonstruo cartaMonstruo)
    {
        this.areaDeCartas.colocarCarta(cartaMonstruo);
        this.cartasEnMano.quitarCarta(cartaMonstruo);
    }

    public void jugarCarta(CartaMagica cartaMagica)
    {
        this.areaDeCartas.colocarCarta(cartaMagica);
        this.cartasEnMano.quitarCarta(cartaMagica);
        cartaMagica.efecto();
    }

    public void jugarCarta(CartaCampo cartaCampo)
    {
        this.areaDeCartas.colocarCarta(cartaCampo);
        this.cartasEnMano.quitarCarta(cartaCampo);
        cartaCampo.efecto();
    }

    public void atacarCartaOponente(CartaMonstruo cartaAtacante, CartaMonstruo cartaOponente)
    {
        cartaAtacante.atacarCarta(cartaOponente);
    }

    public void atacarOponente(CartaMonstruo cartaAtacante)
    {

        cartaAtacante.atacarOponente();
    }

    public void destruirMonstruo(CartaMonstruo carta)
    {
        this.areaDeCartas.removerCarta(carta);
        this.areaDeCartas.enviarAlCementerio(carta);
    }

    public void destruirMonstruos()
    {

        this.areaDeCartas.enviarMonstruosAlCementerio();
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

    // TODO: Estos deberían ser implementados dentro de cada carta específica.
    public void wasteland(Jugador jugador, int modificadorAtaque, int modificadorDefensa)
    {

        if (this == jugador)
        {
            this.areaDeCartas.modificarAtaqueMonstruosCon(modificadorAtaque);
        } else
        {
            this.areaDeCartas.modificarDefensaMonstruosCon(modificadorDefensa);
        }
    }

    public void sogen(Jugador jugador, int modificadorAtaque, int modificadorDefensa)
    {

        if (this == jugador)
        {
            this.areaDeCartas.modificarDefensaMonstruosCon(modificadorDefensa);
        } else
        {
            this.areaDeCartas.modificarAtaqueMonstruosCon(modificadorAtaque);
        }
    }
    // --------------------------------------------------------------------
    // Métodos de consultas.
    // --------------------------------------------------------------------

    public boolean cartaEstaEnCementerio(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnCementerio(carta);
    }

    public boolean cartaEstaEnRegionMonstruos(CartaMonstruo carta)
    {
        return areaDeCartas.cartaEstaEnRegionMonstruos(carta);
    }

    public int cantidadDeCartasEnMano()
    {

        return this.cartasEnMano.cantidadDeCartas();
    }

    public int obtenerModificadorDePuntosDeAtaque()
    {

        return this.areaDeCartas.obtenerModificadorDePuntosDeAtaque();
    }

    public int obtenerModificadorDePuntosDeDefensa()
    {

        return this.areaDeCartas.obtenerModificadorDePuntosDeDefensa();
    }

    public CartaMonstruo obtenerMonstruoConMenorAtaque()
    {

        return this.areaDeCartas.obtenerMonstruoConMenorAtaque();
    }
}

