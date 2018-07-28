package Vista.escenas;

import Modelo.ModeloObservable;
import Vista.Botones.BotonesDeControl;
import Vista.EscenaVista;
import Vista.Vista;
import Vista.areaDeJuego.*;
import Vista.carta.ManoVista;
import Vista.carta.MazoVista;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.File;

public final class EscenaTableroPrincipal implements EscenaVista
{
    private static String RUTA_TABLERO = "resources/imagenes/tablero/tablero yogioh.png";
    private static String direccion_sonido_batalla = "src/resources/audio/battle.mp3";
    private static Vista vista;
    private static MediaPlayer mplayer;
    private static EscenaTableroPrincipal instancia = null;
    private RegionMonstruosVista regionMonstruos;
    private RegionMagicasYTrampasVista regionMagicasYTrampas;
    private RegionCampoVista regionCampo;
    private RegionCementerioVista regionCementerio;
    private ManoVista manos;
    private MazoVista mazos;
    private ModeloObservable modelo;
    private Stage primaryStage;
    private Scene escenaTableroPrincipal;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaTableroPrincipal(ModeloObservable modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        this.primaryStage = this.vista.getPrimaryStage();

        this.inicializarEscena();
    }

    public static EscenaTableroPrincipal getInstancia(ModeloObservable modelo, Vista vista)
    {
        if (instancia == null)
        {
            instancia = new EscenaTableroPrincipal(modelo, vista);
        }
        return instancia;
    }

    public EscenaTableroPrincipal clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    private void inicializarEscena()
    {
        GridPane grid = new GridPane();
        this.escenaTableroPrincipal = new Scene(grid, this.vista.getResolucionHorizontal(), this.vista.getResolucionVertical());
        this.primaryStage.setScene(this.escenaTableroPrincipal);
        this.primaryStage.setMaximized(true);

        grid.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(RUTA_TABLERO).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

        // -------------------------------
        // Estructura de la vista.
        // -------------------------------
        grid.setGridLinesVisible(false);
        // TODO: número mágico.
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

        // -------------------------------
        // Regiones.
        // -------------------------------
        RegionMonstruosVista regionMonstruos = new RegionMonstruosVista(this.vista);
        grid.add(regionMonstruos.getGridJugador(), 1, 3);
        grid.setHalignment(regionMonstruos.getGridJugador(), HPos.CENTER);
        grid.add(regionMonstruos.getGridOponente(), 1, 2);
        grid.setHalignment(regionMonstruos.getGridOponente(), HPos.CENTER);

        RegionMagicasYTrampasVista regionMagicasYTrampas = new RegionMagicasYTrampasVista(this.vista);
        grid.add(regionMagicasYTrampas.getGridJugador(), 1, 4);
        grid.setHalignment(regionMagicasYTrampas.getGridJugador(), HPos.CENTER);
        grid.add(regionMagicasYTrampas.getGridOponente(), 1, 1);
        grid.setHalignment(regionMagicasYTrampas.getGridOponente(), HPos.CENTER);

        RegionCampoVista regionCampo = new RegionCampoVista(this.vista);
        grid.add(regionCampo.getRegionCampoJugador(), 0, 4);
        grid.setHalignment(regionCampo.getRegionCampoJugador(), HPos.CENTER);
        grid.add(regionCampo.getRegionCampoOponente(), 2, 1);
        grid.setHalignment(regionCampo.getRegionCampoOponente(), HPos.CENTER);

        RegionCementerioVista regionCementerio = new RegionCementerioVista(this.vista);
        grid.add(regionCementerio.getCementerioJugador(), 0, 3);
        grid.setHalignment(regionCementerio.getCementerioJugador(), HPos.CENTER);
        grid.add(regionCementerio.getCementerioOponente(), 2, 2);
        grid.setHalignment(regionCementerio.getCementerioOponente(), HPos.CENTER);

        // -------------------------------
        // Manos.
        // -------------------------------
        ManoVista manos = new ManoVista(this.vista);
        grid.add(manos.getManoJugador(), 1, 5);
        grid.setHalignment(manos.getManoJugador(), HPos.CENTER);
        grid.add(manos.getManoOponente(), 1, 0);
        grid.setHalignment(manos.getManoOponente(), HPos.CENTER);

        // -------------------------------
        // Mazos.
        // -------------------------------
        MazoVista mazos = new MazoVista(this.vista);
        grid.add(mazos.getMazoJugador(), 2, 5);
        grid.setHalignment(mazos.getMazoJugador(), HPos.CENTER);
        grid.add(mazos.getMazoOponente(), 0, 0);
        grid.setHalignment(mazos.getMazoOponente(), HPos.CENTER);

        // -------------------------------
        // Vida.
        // -------------------------------

        VidaVista vidas = new VidaVista(this.vista);
        grid.add(vidas.getVidaJugador(), 0, 5);
        grid.setHalignment(vidas.getVidaJugador(), HPos.CENTER);
        grid.add(vidas.getVidaOponente(), 2, 0);
        grid.setHalignment(vidas.getVidaOponente(), HPos.CENTER);

        // -------------------------------
        // Definición de botones.
        // -------------------------------
        BotonesDeControl botones = new BotonesDeControl(this.vista);
        grid.add(botones.getBotonFinDeFase(), 2, 4);
        grid.setHalignment(botones.getBotonFinDeFase(), HPos.CENTER);
        grid.add(botones.getBotonFinDeTurno(), 2, 3);
        grid.setHalignment(botones.getBotonFinDeTurno(), HPos.CENTER);

        // -------------------------------
        // Audio.
        // -------------------------------
        File f = new File(direccion_sonido_batalla);
        Media media = new Media(f.toURI().toString());
        this.mplayer = new MediaPlayer(media);
        this.mplayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------

    @Override
    public EscenaVista cambiarEscena()
    {
        return EscenaFinDeJuego.getInstancia(this.modelo, this.vista);
    }

    @Override
    public boolean terminoElJuego()
    {
        return false;
    }

    @Override
    public void dibujarEscena()
    {
        this.playMedia();
        this.primaryStage.show();
        this.vista.mostrarJugadorActual();
        this.vista.mostrarFaseActual();
    }

    @Override
    public void actualizarEstado()
    {
        this.regionCampo.huboCambios();
        this.regionMagicasYTrampas.huboCambios();
        this.regionMonstruos.huboCambios();
        this.regionCementerio.huboCambios();
        this.manos.huboCambios();
        this.mazos.huboCambios();
    }

    @Override
    public void finDeJuego()
    {
        this.stopMedia();
        this.vista.setProximaEscena(EscenaFinDeJuego.getInstancia(this.modelo, this.vista));
    }

    @Override
    public void playMedia()
    {
        this.mplayer.play();
    }

    @Override
    public void stopMedia()
    {
        this.mplayer.stop();
    }

    @Override
    public void cerrar()
    {
        this.stopMedia();
        this.primaryStage.close();
    }
}
