package Vista;

import Modelo.Jugador;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class InicializadorDeVista {
    public GridPane inicializarVista(GridPane grid, Stage primaryStage, Jugador jugador, Jugador oponente) {

        grid.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource("resources/imagenes/tablero/tablero yogioh.png").toString())), CornerRadii.EMPTY, Insets.EMPTY)));

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

        RegionMonstruosVista regionMonstruosJugador = new RegionMonstruosVista(primaryStage, jugador);
        regionMonstruosJugador.inicializarRegion();

        RegionMonstruosVista regionMonstruosOponente = new RegionMonstruosVista(primaryStage, oponente);
        regionMonstruosOponente.inicializarRegion();

        RegionMagicasYTrampasVista regionMagicasYTrampasJugador = new RegionMagicasYTrampasVista(primaryStage, jugador);
        regionMagicasYTrampasJugador.inicializarRegion();

        RegionMagicasYTrampasVista regionMagicasYTrampasOponente = new RegionMagicasYTrampasVista(primaryStage, oponente);
        regionMagicasYTrampasOponente.inicializarRegion();

        RegionCampoVista regionCampoJugador = new RegionCampoVista(primaryStage, jugador);
        regionCampoJugador.inicializarRegion();

        RegionCampoVista regionCampoOponente = new RegionCampoVista(primaryStage, oponente);
        regionCampoOponente.inicializarRegion();

        RegionCementerioVista regionCementerioJugador = new RegionCementerioVista(primaryStage, jugador);
        regionCementerioJugador.inicializarRegion();

        RegionCementerioVista regionCementerioOponente = new RegionCementerioVista(primaryStage, oponente);
        regionCementerioOponente.inicializarRegion();

        ManoVista manoJugador = new ManoVista(primaryStage, jugador);

        ManoVista manoOponente = new ManoVista(primaryStage, oponente);

        MazoVista mazoJugador = new MazoVista(primaryStage, jugador);
        mazoJugador.inicializar();

        MazoVista mazoOponente = new MazoVista(primaryStage, oponente);
        mazoOponente.inicializar();

        grid.add(regionMonstruosJugador.getNodo(), 1, 3);

        grid.add(regionMonstruosOponente.getNodo(), 1, 2);

        grid.add(regionMagicasYTrampasJugador.getNodo(), 1, 4);

        grid.add(regionMagicasYTrampasOponente.getNodo(), 1, 1);

        grid.add(regionCampoJugador.getNodo(), 0, 4);

        grid.add(regionCampoOponente.getNodo(), 2, 1);

        grid.add(regionCementerioJugador.getNodo(), 0, 3);

        grid.add(regionCementerioOponente.getNodo(), 2, 2);

        grid.add(manoJugador.getNodo(), 1, 5);

        grid.add(manoOponente.getNodo(), 1, 0);

        grid.add(mazoJugador.getNodo(), 2, 5);

        grid.add(mazoOponente.getNodo(), 0, 0);

        grid.setHalignment(regionCampoJugador.getNodo(), HPos.CENTER);
        grid.setHalignment(regionCampoOponente.getNodo(), HPos.CENTER);
        grid.setHalignment(regionCementerioJugador.getNodo(), HPos.CENTER);
        grid.setHalignment(regionCementerioOponente.getNodo(), HPos.CENTER);
        grid.setHalignment(manoJugador.getNodo(), HPos.CENTER);
        grid.setHalignment(manoOponente.getNodo(), HPos.CENTER);
        grid.setHalignment(mazoJugador.getNodo(), HPos.CENTER);
        grid.setHalignment(mazoOponente.getNodo(), HPos.CENTER);

        return grid;
    }

}
