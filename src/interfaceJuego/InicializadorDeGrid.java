package interfaceJuego;

import java.awt.Paint;
import java.util.ArrayList;

import areaDeJuego.RegionMonstruos;
import carta.CartaMonstruo;
import controlador.Controlador;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InicializadorDeGrid
{
    public GridPane inicializarGrid(GridPane grid, Stage primaryStage, Controlador controlador)
    {

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
        
        RegionMonstruosInterface regionMonstruosJugador = new RegionMonstruosInterface(primaryStage);
        regionMonstruosJugador.inicializarRegion();

        RegionMonstruosInterface regionMonstruosOponente = new RegionMonstruosInterface(primaryStage);
        regionMonstruosOponente.inicializarRegion();
        
        RegionMagicasYTrampasInterface regionMagicasYTrampasJugador = new RegionMagicasYTrampasInterface(primaryStage);
        regionMagicasYTrampasJugador.inicializarRegion();
        
        RegionMagicasYTrampasInterface regionMagicasYTrampasOponente = new RegionMagicasYTrampasInterface(primaryStage);
        regionMagicasYTrampasOponente.inicializarRegion();
        
        RegionCampoInterface regionCampoJugador = new RegionCampoInterface(primaryStage);
        regionCampoJugador.inicializarRegion();
        
        RegionCampoInterface regionCampoOponente = new RegionCampoInterface(primaryStage);
        regionCampoOponente.inicializarRegion();
        
        RegionCementerioInterface regionCementerioJugador = new RegionCementerioInterface(primaryStage);
        regionCementerioJugador.inicializarRegion();
        
        RegionCementerioInterface regionCementerioOponente = new RegionCementerioInterface(primaryStage);
        regionCementerioOponente.inicializarRegion();
        
        ManoInterface manoJugador = new ManoInterface(primaryStage);
        
        ManoInterface manoOponente = new ManoInterface(primaryStage);
        
        MazoInterface mazoJugador = new MazoInterface(primaryStage);
        mazoJugador.inicializar();
        
        MazoInterface mazoOponente = new MazoInterface(primaryStage);
        mazoOponente.inicializar();
        
        grid.add(regionMonstruosJugador.getNodo(), 1, 3);

        grid.add(regionMonstruosOponente.getNodo(), 1, 2);
        
        grid.add(regionMagicasYTrampasJugador.getNodo(), 1, 4);

        grid.add(regionMagicasYTrampasOponente.getNodo(), 1, 1);
        
        grid.add(regionCampoJugador.getNodo(), 0, 4);
        
        grid.add(regionCampoOponente.getNodo(), 2, 1);
        
        grid.add(regionCementerioJugador.getNodo(),0 , 3);
        
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
