import Controlador.Controlador;
import Modelo.Modelo;
import Vista.Vista;
import javafx.application.Application;
import javafx.stage.Stage;

public class AlGoOh extends Application
{
    private static Modelo modelo;
    private static Controlador controlador;
    private static Vista vista;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        // TODO: solicitar nombres usando una escena previa a la principal.
        this.modelo = new Modelo("Manu", "Nico");
        this.vista = new Vista(modelo, primaryStage);
        this.controlador = new Controlador(modelo, vista);
    }
}
