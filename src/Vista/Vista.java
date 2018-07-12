package Vista;

import Controlador.Controlador;
import Modelo.Modelo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Vista extends Application {

    // TODO: levantar la resolucion automaticamente con alguna llamada al sistema.
    private static int RESOLUCION_HORIZONTAL = 1920;
    private static int RESOLUCION_VERTICAL = 1000;
    private GridPane root;

    private Modelo modelo;
    private Controlador controlador;

    public Vista() {

        this.root = new GridPane();
        //this.controlador = new Controlador();

    }

    public static void main(String[] args) {

        /*
        Primero se crea el modelo.
        Model      model      = new Model();
        Despues la vista, y le pasamos una referencia al modelo para que le pida lo que necesita.
        View       view       = new View(model);
        Por ultimo creamos el controlador y le pasamos las referencias del modelo y la vista.
        Controller controller = new Controller(model, view);

        Inicia todo.
        view.setVisible(true);
        */
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("AlGoOh");

        Scene scenePrincipal = new Scene(root, RESOLUCION_HORIZONTAL, RESOLUCION_VERTICAL);
        primaryStage.setScene(scenePrincipal);

        // TODO: arreglar esto para que esto no se inicialice aca. Deberia ir a un main?
        Modelo modelo = new Modelo("Manu", "Nico");
        this.modelo = modelo;
        this.controlador = new Controlador(this.modelo, this);


        InicializadorDeVista inicializador = new InicializadorDeVista();
        root = inicializador.inicializarVista(root, primaryStage, this.controlador, this.modelo.obtenerJugador(), this.modelo.obtenerOponente());
        primaryStage.show();

    }
}
