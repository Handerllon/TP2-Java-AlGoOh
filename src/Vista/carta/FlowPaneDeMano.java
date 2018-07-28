package Vista.carta;

import Modelo.Jugador;
import Modelo.carta.Carta;
import Vista.Botones.BotonCartaEnMano;
import Vista.Vista;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class FlowPaneDeMano extends FlowPane
{
    private FlowPane flowPane;
    private ArrayList<BotonCartaEnMano> botones;
    private Vista vista;
    private Jugador jugadorAsociado;

    public FlowPaneDeMano(Vista vista, Jugador jugador)
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

    public void actualizar(ArrayList<Carta> cartasEnLaManoDelJugador)
    {

        for (int i = 0; i < cartasEnLaManoDelJugador.size(); i++)
        {

            BotonCartaEnMano boton = new BotonCartaEnMano(this.vista, cartasEnLaManoDelJugador.get(i), this.jugadorAsociado);
            botones.add(boton);
        }

        for (int j = 0; j < botones.size(); j++)
        {

            this.flowPane.getChildren().add(botones.get(j));
        }
    }
}
