package interfaceJuego;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InicializadorDeGrid
{
    public GridPane inicializarGrid(GridPane grid, Stage primaryStage)
    {

        grid.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader().getResource("resources/imagenes/tablero/tablero yogioh.png").toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        
        grid.setGridLinesVisible(false);
        grid.setPadding(new Insets(5));
        ColumnConstraints column0 = new ColumnConstraints(300);
        ColumnConstraints column1 = new ColumnConstraints(1310);
        ColumnConstraints column2 = new ColumnConstraints(300);
        RowConstraints row0 = new RowConstraints(160);
        RowConstraints row1 = new RowConstraints(160);
        RowConstraints row2 = new RowConstraints(160);
        RowConstraints row3 = new RowConstraints(160);
        RowConstraints row4 = new RowConstraints(160);
        RowConstraints row5 = new RowConstraints(160);
        grid.getColumnConstraints().addAll(column0, column1, column2);
        grid.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5);

        Rectangle mazo1 = new Rectangle(95.4, 139);
        mazo1.setFill(Color.SADDLEBROWN);
        Rectangle mazo2 = new Rectangle(95.4, 139);
        mazo2.setFill(Color.SADDLEBROWN);
        Rectangle campo1 = new Rectangle(95.4, 139);
        campo1.setFill(Color.GREEN);
        Rectangle campo2 = new Rectangle(95.4, 139);
        campo2.setFill(Color.GREEN);
        Rectangle cementerio1 = new Rectangle(95.4, 139);
        cementerio1.setFill(Color.GREY);
        Rectangle cementerio2 = new Rectangle(95.4, 139);
        cementerio2.setFill(Color.GREY);

        grid.add(mazo1, 0, 0);
        grid.add(mazo2, 2, 5);
        grid.add(campo1, 2, 1);
        grid.add(campo2, 0, 4);
        grid.add(cementerio1, 2, 2);
        grid.add(cementerio2, 0, 3);

        grid.setHalignment(mazo1, HPos.CENTER);
        grid.setHalignment(mazo2, HPos.CENTER);
        grid.setHalignment(campo1, HPos.CENTER);
        grid.setHalignment(campo2, HPos.CENTER);
        grid.setHalignment(cementerio1, HPos.CENTER);
        grid.setHalignment(cementerio2, HPos.CENTER);

        grid.add(this.crearRegion(Color.DARKORANGE, primaryStage), 1, 3);
        grid.add(this.crearRegion(Color.DARKORANGE, primaryStage), 1, 2);
        grid.add(this.crearRegion(Color.DARKTURQUOISE, primaryStage), 1, 1);
        grid.add(this.crearRegion(Color.DARKTURQUOISE, primaryStage), 1, 4);

        return grid;
    }

    private GridPane crearRegion(Color colorBuscado, Stage primaryStage)
    {

        GridPane gridPane = new GridPane();

        gridPane.setGridLinesVisible(false);

        ColumnConstraints columnMonstruos1 = new ColumnConstraints(262);
        ColumnConstraints columnMonstruos2 = new ColumnConstraints(262);
        ColumnConstraints columnMonstruos3 = new ColumnConstraints(262);
        ColumnConstraints columnMonstruos4 = new ColumnConstraints(262);
        ColumnConstraints columnMonstruos5 = new ColumnConstraints(262);
        RowConstraints filaMonstruos1 = new RowConstraints(160);

        gridPane.getColumnConstraints().addAll(columnMonstruos1, columnMonstruos2, columnMonstruos3, columnMonstruos4, columnMonstruos5);
        gridPane.getRowConstraints().addAll(filaMonstruos1);

        CreadorDeBotones creadorDeBotones = new CreadorDeBotones();

        Button b1 = new Button();
        b1 = creadorDeBotones.nuevoBoton(b1, primaryStage);
        Button b2 = new Button();
        b2 = creadorDeBotones.nuevoBoton(b2, primaryStage);
        Button b3 = new Button();
        b3 = creadorDeBotones.nuevoBoton(b3, primaryStage);
        Button b4 = new Button();
        b4 = creadorDeBotones.nuevoBoton(b4, primaryStage);
        Button b5 = new Button();
        b5 = creadorDeBotones.nuevoBoton(b5, primaryStage);

        gridPane.add(b1, 0, 0);
        gridPane.add(b2, 1, 0);
        gridPane.add(b3, 2, 0);
        gridPane.add(b4, 3, 0);
        gridPane.add(b5, 4, 0);

        gridPane.setHalignment(b1, HPos.CENTER);
        gridPane.setHalignment(b2, HPos.CENTER);
        gridPane.setHalignment(b3, HPos.CENTER);
        gridPane.setHalignment(b4, HPos.CENTER);
        gridPane.setHalignment(b5, HPos.CENTER);

        return gridPane;
    }
}
