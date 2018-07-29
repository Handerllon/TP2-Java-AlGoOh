package Vista.carta.mano;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Vista.Vista;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class ManosFlowPane extends FlowPane
{
    private FlowPane flowPane;
    private ArrayList<ManoBoton> botones;
    private Vista vista;
    private Jugador jugadorAsociado;

    public ManosFlowPane(Vista vista, Jugador jugador)
    {
        this.jugadorAsociado = jugador;
        this.vista = vista;
        this.flowPane = new FlowPane();
        this.flowPane.setAlignment(Pos.CENTER);
        this.botones = new ArrayList<>();
    }

    public FlowPane getFlowPane()
    {
        return this.flowPane;
    }

    public void clear()
    {
        this.flowPane = new FlowPane();
        this.flowPane.setAlignment(Pos.CENTER);
        this.botones = new ArrayList<>();
    }

    public void actualizar(ArrayList<Carta> cartasEnMano)
    {
        cartasEnMano.forEach(carta -> this.botones.add(new ManoBoton(this.vista, carta, this.jugadorAsociado)));
        this.botones.forEach(boton -> this.flowPane.getChildren().add(boton));
    }
}
