package Vista;

import Modelo.Modelo;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Vista implements ObjectoObservador{

    // TODO: levantar la resolucion automaticamente con alguna llamada al sistema.
    private static int RESOLUCION_HORIZONTAL = 1920;
    private static int RESOLUCION_VERTICAL = 1000;
    private static Modelo modelo;
    private GridPane root = new GridPane();

    public Vista(Modelo modelo, Stage primaryStage) {
        this.modelo = modelo;

        // TODO: realizar subscripciones al modelo.

        primaryStage.setTitle("AlGoOh");
        Scene scenePrincipal = new Scene(root, RESOLUCION_HORIZONTAL, RESOLUCION_VERTICAL);
        primaryStage.setScene(scenePrincipal);

        InicializadorDeVista inicializador = new InicializadorDeVista();
        root = inicializador.inicializarVista(root, primaryStage, this.modelo.obtenerJugador(), this.modelo.obtenerOponente());
        primaryStage.show();

    }

    @Override
    public void update() {

    }
}
