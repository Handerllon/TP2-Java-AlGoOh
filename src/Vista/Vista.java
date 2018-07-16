package Vista;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import Vista.areaDeJuego.RegionCampoVista;
import Vista.areaDeJuego.RegionCementerioVista;
import Vista.areaDeJuego.RegionMagicasYTrampasVista;
import Vista.areaDeJuego.RegionMonstruosVista;
import Vista.carta.ManoVista;
import Vista.carta.MazoVista;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

public class Vista implements ObservadorDeModelo
{
    protected static Modelo modelo;
    // TODO: levantar la resolucion automaticamente con alguna llamada al sistema.
    private static int RESOLUCION_HORIZONTAL = 1920;
    private static int RESOLUCION_VERTICAL = 1000;
    private static String RUTA_TABLERO = "resources/imagenes/tablero/tablero yogioh.png";
    private GridPane root = new GridPane();
    private RegionMonstruosVista regionMonstruos;
    private RegionMagicasYTrampasVista regionMagicasYTrampas;
    private RegionCampoVista regionCampo;
    private RegionCementerioVista regionCementerio;
    private ManoVista manos;
    private MazoVista mazos;

    public Vista(Modelo modelo, Stage primaryStage)
    {
        this.modelo = modelo;
        this.modelo.agregarObsevador(this);

        GridPane grid = root;

        primaryStage.setTitle("AlGoOh");
        Scene scenePrincipal = new Scene(grid, RESOLUCION_HORIZONTAL, RESOLUCION_VERTICAL);
        primaryStage.setScene(scenePrincipal);

        grid.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(RUTA_TABLERO).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

        // Estructura de la vista.
        grid.setGridLinesVisible(false);
        // TODO: generalizar el hardcodeo de los numeros.
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

        // ---------------- Regiones ----------------
        RegionMonstruosVista regionMonstruos = new RegionMonstruosVista(primaryStage, this.modelo);
        grid.add(regionMonstruos.getGridJugador(), 1, 3);
        grid.setHalignment(regionMonstruos.getGridJugador(), HPos.CENTER);
        grid.add(regionMonstruos.getGridOponente(), 1, 2);
        grid.setHalignment(regionMonstruos.getGridOponente(), HPos.CENTER);

        RegionMagicasYTrampasVista regionMagicasYTrampas = new RegionMagicasYTrampasVista(primaryStage, this.modelo);
        grid.add(regionMagicasYTrampas.getGridJugador(), 1, 4);
        grid.setHalignment(regionMagicasYTrampas.getGridJugador(), HPos.CENTER);
        grid.add(regionMagicasYTrampas.getGridOponente(), 1, 1);
        grid.setHalignment(regionMagicasYTrampas.getGridOponente(), HPos.CENTER);

        RegionCampoVista regionCampo = new RegionCampoVista(primaryStage, this.modelo);
        grid.add(regionCampo.getRegionCampoJugador(), 0, 4);
        grid.setHalignment(regionCampo.getRegionCampoJugador(), HPos.CENTER);
        grid.add(regionCampo.getRegionCampoOponente(), 2, 1);
        grid.setHalignment(regionCampo.getRegionCampoOponente(), HPos.CENTER);

        RegionCementerioVista regionCementerio = new RegionCementerioVista(primaryStage, this.modelo);
        grid.add(regionCementerio.getCementerioJugador(), 0, 3);
        grid.setHalignment(regionCementerio.getCementerioJugador(), HPos.CENTER);
        grid.add(regionCementerio.getCementerioOponente(), 2, 2);
        grid.setHalignment(regionCementerio.getCementerioOponente(), HPos.CENTER);

        // ---------------- Manos ----------------

        ManoVista manos = new ManoVista(primaryStage, this.modelo);
        grid.add(manos.getManoJugador(), 1, 5);
        grid.setHalignment(manos.getManoJugador(), HPos.CENTER);
        grid.add(manos.getManoOponente(), 1, 0);
        grid.setHalignment(manos.getManoOponente(), HPos.CENTER);

        // ---------------- Mazos ----------------

        MazoVista mazos = new MazoVista(primaryStage, this.modelo);
        grid.add(mazos.getMazoJugador(), 2, 5);
        grid.setHalignment(mazos.getMazoJugador(), HPos.CENTER);
        grid.add(mazos.getMazoOponente(), 0, 0);
        grid.setHalignment(mazos.getMazoOponente(), HPos.CENTER);

        primaryStage.show();
    }

    @Override
    public void actualizar()
    {
        this.regionCampo.actualizar();
        this.regionMagicasYTrampas.actualizar();
        this.regionMonstruos.actualizar();
        this.regionCementerio.actualizar();
        this.manos.actualizar();
        this.mazos.actualizar();
    }
}
