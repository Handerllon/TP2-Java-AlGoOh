package Vista.carta;

import Modelo.carta.Carta;
import Vista.Botones.BotonCartaEnMano;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FlowPaneDeMano extends FlowPane
{
    private FlowPane flowPane;
    private Stage stage;
    private ArrayList<BotonCartaEnMano> botones;

    public FlowPaneDeMano(Stage primaryStage)
    {

        this.stage = primaryStage;
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

            BotonCartaEnMano boton = new BotonCartaEnMano(stage, cartasEnLaManoDelJugador.get(i));
            botones.add(boton);
        }

        for (int j = 0; j < botones.size(); j++)
        {

            this.flowPane.getChildren().add(botones.get(j));
        }
    }
}
