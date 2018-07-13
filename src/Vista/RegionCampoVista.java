package Vista;

import Modelo.Jugador;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCampoVista implements ObjectoObservador
{
    private Button boton;
    private Stage stage;

    public RegionCampoVista(Stage primaryStage, Jugador jugador)
    {

        boton = new Button();

        stage = primaryStage;

        //jugador.obtenerRegionCampo().agregarObsevador(this);

    }

    public void inicializarRegion()
    {

        boton.setPrefSize(95.4, 139);
        boton.setStyle("-fx-background-color: Transparent");
    }

    public Button getNodo()
    {

        return boton;
    }

    @Override
    public void update()
    {
        // TODO: pedir las cartas de la region, y pedirles sus imagenes para actualizar la vista de la region.
    }
}
