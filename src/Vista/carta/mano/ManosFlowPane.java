package Vista.carta.mano;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class ManosFlowPane extends FlowPane implements ObservadorDeModelo
{
    private FlowPane flowPane;
    private ArrayList<ManoBoton> botones;
    private Vista vista;
    private Jugador jugadorAsociado;
    private double horizontalGapFlowPaneButtons = 0.02;
    private double verticalGapFlowPaneButtons = 0.02;

    public ManosFlowPane(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;

        vista.getModelo().registrarObsevador(this);

        this.flowPane = new FlowPane(this.vista.getResolucionHorizontal() * horizontalGapFlowPaneButtons,
                this.vista.getResolucionVertical() * verticalGapFlowPaneButtons);

        this.flowPane.setAlignment(Pos.CENTER);
        this.botones = new ArrayList<>();
    }

    public FlowPane getFlowPane()
    {
        return this.flowPane;
    }

    public void clear()
    {
        this.flowPane = new FlowPane(this.vista.getResolucionHorizontal() * horizontalGapFlowPaneButtons,
                this.vista.getResolucionVertical() * verticalGapFlowPaneButtons);
        this.flowPane.setAlignment(Pos.CENTER);
        this.botones = new ArrayList<>();
    }

    public void actualizar(ArrayList<Carta> cartasEnMano)
    {
        cartasEnMano.forEach(carta -> this.botones.add(new ManoBoton(this.vista, carta, this.jugadorAsociado)));
        this.botones.forEach(boton -> this.flowPane.getChildren().add(boton.getBoton()));
    }

    @Override
    public void seTomoCartaDeMazo()
    {

    }

    @Override
    public void ingresoCartaAMano()
    {
        clear();
        actualizar(jugadorAsociado.getMano().getCartas());
        this.vista.actualizarDibujo();
    }

    @Override
    public void egresoCartaAMano()
    {
        clear();
        actualizar(jugadorAsociado.getMano().getCartas());
        this.vista.actualizarDibujo();
    }

    @Override
    public void ingresoCartaARegion()
    {

    }

    @Override
    public void egresoCartaARegion()
    {

    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {

    }

    @Override
    public void cartaCambioDeOrientacion()
    {

    }

    @Override
    public void cartaCambioDeModo()
    {

    }
}
