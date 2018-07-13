package Vista;

import Modelo.Jugador;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ManoVista implements ObjectoObservador
{
    private FlowPane flowPane;
    private Stage stage;

    public ManoVista(Stage primaryStage, Jugador jugador)
    {

        flowPane = new FlowPane();

        stage = primaryStage;

        //jugador.obtenerMano().agregarObsevador(this);

    }

    public void inicializarRegion()
    {

    }

    public FlowPane getNodo()
    {

        return flowPane;
    }

    @Override
    public void update()
    {
        // TODO: pedir las cartas a la mano y actualizar las vistas.
    }
}
