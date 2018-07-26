package Controlador.estadosJuego;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class MaquinaTurnos
{
    private static MaquinaTurnos instancia = null;
    private Fase faseActual;
    private Jugador jugador1, jugador2, jugadorActual;
    private ArrayList<Carta> cartasConCambioDeOrientacionEnTurno;
    private boolean esPrimerTurno;
    private ArrayList<CartaMonstruo> cartasAtacaronEnTurnoActual;
    private ArrayList<Carta> cartasColocadasEnRegionEnTurnos;
    private boolean seTomoCartaEnTurno;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private MaquinaTurnos(Jugador jugador1, Jugador jugador2)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.faseActual = FaseInicial.getInstancia(this);
        this.cartasConCambioDeOrientacionEnTurno = new ArrayList<>();
        this.esPrimerTurno = true;
        this.seTomoCartaEnTurno = false;
        this.cartasAtacaronEnTurnoActual = new ArrayList<>();
        this.cartasColocadasEnRegionEnTurnos = new ArrayList<>();

        this.sortearJugadorInicial();
    }

    public static MaquinaTurnos getInstancia(Jugador jugador1, Jugador jugador2)
    {
        if (instancia == null)
        {
            instancia = new MaquinaTurnos(jugador1, jugador2);
        }
        return instancia;
    }

    public MaquinaTurnos clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    public void sortearJugadorInicial()
    {
        // nextInt produce un conjunto abierto, asique se le suma 1 para incluir el límite superior.
        int randomNum = ThreadLocalRandom.current().nextInt(0, 1 + 1);
        if (randomNum == 0)
        {
            this.jugadorActual = this.jugador1;
        } else
        {
            this.jugadorActual = this.jugador2;
        }
    }

    // --------------------------------------------------------------------
    // Métodos de fases y turnos.
    // --------------------------------------------------------------------
    public void avanzarProximaFase()
    {
        this.faseActual = this.faseActual.avanzar();
    }

    public void terminarTurno()
    {
        this.cartasConCambioDeOrientacionEnTurno.clear();
        this.cartasAtacaronEnTurnoActual.clear();
        this.cartasColocadasEnRegionEnTurnos.clear();
        this.swapJugadorActual();
        this.reiniciarFases();
        this.esPrimerTurno = false;
        this.seTomoCartaEnTurno = false;
    }

    private void swapJugadorActual()
    {
        if (this.getJugadorActual() == this.jugador1)
        {
            this.jugadorActual = this.jugador2;
        } else
        {
            this.jugadorActual = this.jugador1;
        }
    }

    private void reiniciarFases()
    {
        this.faseActual = FaseInicial.getInstancia(this);
    }

    public Jugador getJugadorActual()
    {
        return this.jugadorActual;
    }

    public Fase faseActual()
    {
        return this.faseActual;
    }

    // ------------------------------------
    // Métodos de acciones.
    // ------------------------------------
    public void seCambioOrientacionCarta(Carta carta)
    {
        this.cartasConCambioDeOrientacionEnTurno.add(carta);
    }

    public void seColocoCartaEnRegion(Carta carta)
    {
        this.cartasColocadasEnRegionEnTurnos.add(carta);
    }

    public void cartaAtaco(CartaMonstruo cartaMonstruo)
    {
        this.cartasAtacaronEnTurnoActual.add(cartaMonstruo);
    }

    public void seTomoCartaEnTurno()
    {
        this.seTomoCartaEnTurno = true;
    }

    // ------------------------------------
    // Métodos de consulta de acciones.
    // ------------------------------------
    public boolean esPrimerTurnoJuego()
    {
        return this.esPrimerTurno;
    }

    public boolean yaAtacoEnTurno(CartaMonstruo cartaMonstruo)
    {
        return this.cartasAtacaronEnTurnoActual.contains(cartaMonstruo);
    }

    public boolean yaColocoEnTurno(Carta carta)
    {
        return this.cartasConCambioDeOrientacionEnTurno.contains(carta);
    }

    public boolean yaMandoMonstruoARegionEnTurno()
    {
        return this.cartasColocadasEnRegionEnTurnos.isEmpty();
    }

    public boolean yaTomoCartaEnTurno()
    {
        return this.seTomoCartaEnTurno;
    }
}

