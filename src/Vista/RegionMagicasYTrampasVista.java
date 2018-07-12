package Vista;

import Modelo.Jugador;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class RegionMagicasYTrampasVista implements ObjectoObservador {

    private GridPane gridJugador;
    private Stage stage;

    public RegionMagicasYTrampasVista(Stage primaryStage, Jugador jugador) {

        gridJugador = new GridPane();
        stage = primaryStage;
    }

    public void inicializarRegion() {

        // TODO: no hardcodear los valores. Usar un resultado en funcion de la resolucion.
        ColumnConstraints columna0 = new ColumnConstraints(262);
        ColumnConstraints columna1 = new ColumnConstraints(262);
        ColumnConstraints columna2 = new ColumnConstraints(262);
        ColumnConstraints columna3 = new ColumnConstraints(262);
        ColumnConstraints columna4 = new ColumnConstraints(262);
        RowConstraints fila1 = new RowConstraints(160);

        gridJugador.getColumnConstraints().addAll(columna0, columna1, columna2, columna3, columna4);
        gridJugador.getRowConstraints().addAll(fila1);

        for (int i = 0; i < 5; i++) {

            Button boton = new Button();
            boton.setPrefSize(95.4, 139);
            boton.setStyle("-fx-background-color: Transparent ; -fx-border-width: 5px ; -fx-border-color: Black");

            gridJugador.add(boton, i, 0);

            GridPane.setHalignment(boton, HPos.CENTER);

        }
    }

    public GridPane getNodo() {


        return gridJugador;
    }

    @Override
    public void update() {
        // TODO: pedir las cartas de la region, y pedirles sus imagenes para actualizar la vista de la region.
    }


}
