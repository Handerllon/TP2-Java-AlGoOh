package Vista.carta;

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

    public FlowPaneDeMano(Vista vista)
    {

        this.vista = vista;
        this.flowPane = new FlowPane();
        this.flowPane.setAlignment(Pos.CENTER);
        this.botones = new ArrayList<BotonCartaEnMano>();
    }

    public FlowPane getFlowPane()
    {

        return this.flowPane;
    }

    public void clear()
    {

        this.flowPane = new FlowPane();
        this.flowPane.setAlignment(Pos.CENTER);
        this.botones = new ArrayList<BotonCartaEnMano>();
    }

    public void actualizarMano(ArrayList<Carta> cartasEnLaManoDelJugador)
    {

        for (int i = 0; i < cartasEnLaManoDelJugador.size(); i++)
        {

            BotonCartaEnMano boton = new BotonCartaEnMano(this.vista, cartasEnLaManoDelJugador.get(i));
            botones.add(boton);
        }

        for (int j = 0; j < botones.size(); j++)
        {

            this.flowPane.getChildren().add(botones.get(j));
        }
    }
}
