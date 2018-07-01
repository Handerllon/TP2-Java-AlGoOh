package interfaceJuego;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfacePrincipal extends Application
{
    public static void main(String[] args)
    {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        primaryStage.setTitle("AlGoOh");

        GridPane root = new GridPane();

        Scene scenePrincipal = new Scene(root, 1920, 1000);
        primaryStage.setScene(scenePrincipal);

        InicializadorDeGrid inicializador = new InicializadorDeGrid();

        root = inicializador.inicializarGrid(root, primaryStage);

        primaryStage.show();
    }
}
