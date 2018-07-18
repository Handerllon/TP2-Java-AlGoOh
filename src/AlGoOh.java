import Controlador.Controlador;
import Modelo.Modelo;
import Vista.Vista;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AlGoOh extends Application
{
    private static Modelo modelo;
    private static Vista vista;
    private static Controlador controlador;

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle("AlGoOh");
        primaryStage.setOnCloseRequest(e -> onCloseRequest_click(e));

        this.modelo = Modelo.obtenerInstancia();
        this.controlador = Controlador.obtenerInstancia(this.modelo);

        this.vista = new Vista(this.modelo, this.controlador, primaryStage);

        this.controlador.establecerVista(this.vista);

        this.controlador.iniciar();
    }

    private void onCloseRequest_click(WindowEvent e)
    {
        e.consume();
        this.controlador.confirmarSalirPrograma();
    }

    @Override
    public void stop() throws Exception
    {

    }
}
