package Controlador.estadosJuego;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.CartaMonstruoNula;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public final class MaquinaTurnos
{
    private static MaquinaTurnos instancia = null;
    private Fase faseActual;
    private Jugador jugador1, jugador2, jugadorActual;
    private ArrayList<Carta> cartasQueCambiaronOrientacionEnTurnoActual;
    private boolean esElPrimerTurnoDelJuego;
    private ArrayList<CartaMonstruo> cartasQueAtacaronEnTurnoActual;
    private CartaMonstruo cartaMonstruoColocadaEnRegionEnTurnoActual;
    private boolean seTomoUnaCartaEnTurno;
    private boolean seColocoCartaMonstruoEnRegionEnTurnoActual;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private MaquinaTurnos(Jugador jugador1, Jugador jugador2)
    {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.faseActual = FaseInicial.getInstancia(this);

        // ---------------------------------------------------
        // Sobre orientación de cartas.
        // ---------------------------------------------------
        this.cartasQueCambiaronOrientacionEnTurnoActual = new ArrayList<>();

        // ---------------------------------------------------
        // Sobre colocación de cartas.
        // ---------------------------------------------------
        this.seTomoUnaCartaEnTurno = false;
        this.cartaMonstruoColocadaEnRegionEnTurnoActual = CartaMonstruoNula.getInstancia();
        this.seColocoCartaMonstruoEnRegionEnTurnoActual = false;

        // ---------------------------------------------------
        // Sobre ataques de cartas.
        // ---------------------------------------------------
        this.cartasQueAtacaronEnTurnoActual = new ArrayList<>();

        // ---------------------------------------------------
        // Sobre fases y turno.
        // ---------------------------------------------------
        this.esElPrimerTurnoDelJuego = true;

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
    public void avanzarFase()
    {
        this.faseActual = this.faseActual.avanzar();
    }

    public void retrocederFase()
    {
        this.faseActual = this.faseActual.retroceder();
    }

    public void terminarTurno()
    {
        // ---------------------------------------------------
        // Sobre orientación de cartas.
        // ---------------------------------------------------
        this.cartasQueCambiaronOrientacionEnTurnoActual.clear();

        // ---------------------------------------------------
        // Sobre colocación de cartas.
        // ---------------------------------------------------
        this.seTomoUnaCartaEnTurno = false;
        this.seColocoCartaMonstruoEnRegionEnTurnoActual = false;
        this.cartaMonstruoColocadaEnRegionEnTurnoActual = CartaMonstruoNula.getInstancia();

        // ---------------------------------------------------
        // Sobre ataques de cartas.
        // ---------------------------------------------------
        this.cartasQueAtacaronEnTurnoActual.clear();

        // ---------------------------------------------------
        // Sobre fases y turno.
        // ---------------------------------------------------
        this.swapJugadorActual();
        this.reiniciarFases();
        this.esElPrimerTurnoDelJuego = false;
    }

    private void swapJugadorActual()
    {
        if (this.jugadorActualEsIgualA(this.jugador1))
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

    public Fase getFaseActual()
    {
        return this.faseActual;
    }

    public void setFaseActual(Fase fase)
    {
        this.faseActual = fase;
    }

    public boolean jugadorActualEsIgualA(Jugador jugador)
    {
        return getJugadorActual() == jugador;
    }

    // ---------------------------------------------------
    // Verificación de orientación de cartas en turno.
    // ---------------------------------------------------
    public void seCambiaOrientacionCartaEnTurnoActual(Carta carta)
    {
        this.cartasQueCambiaronOrientacionEnTurnoActual.add(carta);
    }

    public boolean yaCambioOrientacionEnTurnoActual(Carta carta)
    {
        return this.cartasQueCambiaronOrientacionEnTurnoActual.contains(carta);
    }

    // ---------------------------------------------------
    // Verificación de colocación de cartas en turno.
    // ---------------------------------------------------
    public void seTomaCartaEnTurnoActual()
    {
        this.seTomoUnaCartaEnTurno = true;
    }

    public boolean yaTomoCartaEnTurnoActual()
    {
        return this.seTomoUnaCartaEnTurno;
    }

    public void seColocaCartaMonstruoEnRegionEnTurnoActual(CartaMonstruo carta)
    {
        this.seColocoCartaMonstruoEnRegionEnTurnoActual = true;
        this.cartaMonstruoColocadaEnRegionEnTurnoActual = carta;
    }

    public boolean yaMandoMonstruoARegionEnTurnoActual()
    {
        return this.seColocoCartaMonstruoEnRegionEnTurnoActual;
    }

    // ---------------------------------------------------
    // Verificación de ataques de cartas en turno.
    // ---------------------------------------------------
    public boolean esElPrimerTurnoDelJuego()
    {
        return this.esElPrimerTurnoDelJuego;
    }

    public void cartaAtacaEnTurnoActual(CartaMonstruo cartaMonstruo)
    {
        this.cartasQueAtacaronEnTurnoActual.add(cartaMonstruo);
    }

    public boolean cartaYaAtacoEnTurnoActual(CartaMonstruo cartaMonstruo)
    {
        return this.cartasQueAtacaronEnTurnoActual.contains(cartaMonstruo);
    }

	
}

