package Vista.escena;

import Modelo.ModeloObservable;
import Vista.EstadosJuegoBotones;
import Vista.VidaVista;
import Vista.Vista;
import Vista.carta.MazosVista;
import Vista.carta.mano.ManosVista;
import Vista.region.RegionesCampoVista;
import Vista.region.RegionesCementerioVista;
import Vista.region.RegionesMagicasYTrampasVista;
import Vista.region.RegionesMonstruoVista;
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

public final class EscenaTableroPrincipal implements Escena
{
    private static EscenaTableroPrincipal instancia = null;
    private GridPane grid;
    private String RUTA_TABLERO = "resources/imagenes/fondoTableroGeneral.png";
    private String direccion_sonido_batalla = "src/resources/audio/battle.mp3";
    private Vista vista;
    private MediaPlayer mplayer;
    private RegionesMonstruoVista regionesMonstruo;
    private RegionesMagicasYTrampasVista regionesMagicasYTrampas;
    private RegionesCampoVista regionesCampo;
    private RegionesCementerioVista regionesCementerio;
    private ManosVista manos;
    private MazosVista mazos;
    private VidaVista vidas;
    private ModeloObservable modelo;
    private Stage primaryStage;
    private Scene escenaTableroPrincipal;
    private EstadosJuegoBotones estadosJuegoBotones;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaTableroPrincipal(ModeloObservable modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        this.primaryStage = this.vista.getPrimaryStage();

        this.grid = new GridPane();

        this.regionesMonstruo = new RegionesMonstruoVista(this.vista);
        this.regionesMagicasYTrampas = new RegionesMagicasYTrampasVista(this.vista);
        this.regionesCampo = new RegionesCampoVista(this.vista);
        this.regionesCementerio = new RegionesCementerioVista(this.vista);

        this.manos = new ManosVista(this.vista);
        this.mazos = new MazosVista(this.vista);
        this.vidas = new VidaVista(this.vista);

        this.estadosJuegoBotones = new EstadosJuegoBotones(this.vista);

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
        this.escenaTableroPrincipal = new Scene(grid, this.vista.getResolucionHorizontal(), this.vista.getResolucionVertical());
        this.primaryStage.setScene(this.escenaTableroPrincipal);
        this.primaryStage.setMaximized(true);

        //grid.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                //.getResource(RUTA_TABLERO).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        grid.setPrefSize(this.vista.getResolucionHorizontal(), this.vista.getResolucionVertical());

        // -------------------------------
        // Estructura de la vista.
        // -------------------------------
        grid.setGridLinesVisible(true);
        grid.setPadding(new Insets(5));
        //Se uso una resoulcion de 1080x1920 como base para los tamanos
        ColumnConstraints column0 = new ColumnConstraints((this.vista.getResolucionHorizontal()*300)/1920);
        ColumnConstraints column1 = new ColumnConstraints((this.vista.getResolucionHorizontal()*1310)/1920);
        ColumnConstraints column2 = new ColumnConstraints((this.vista.getResolucionHorizontal()*300)/1920);
        RowConstraints row0 = new RowConstraints((this.vista.getResolucionVertical()*170)/1080);
        RowConstraints row1 = new RowConstraints((this.vista.getResolucionVertical()*170)/1080);
        RowConstraints row2 = new RowConstraints((this.vista.getResolucionVertical()*170)/1080);
        RowConstraints row3 = new RowConstraints((this.vista.getResolucionVertical()*170)/1080);
        RowConstraints row4 = new RowConstraints((this.vista.getResolucionVertical()*170)/1080);
        RowConstraints row5 = new RowConstraints((this.vista.getResolucionVertical()*170)/1080);
        grid.getColumnConstraints().addAll(column0, column1, column2);
        grid.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5);

        // -------------------------------
        // Regiones.
        // -------------------------------
        grid.add(regionesMonstruo.getGridJugador(), 1, 3);
        grid.setHalignment(regionesMonstruo.getGridJugador(), HPos.CENTER);
        grid.add(regionesMonstruo.getGridOponente(), 1, 2);
        grid.setHalignment(regionesMonstruo.getGridOponente(), HPos.CENTER);

        grid.add(regionesMagicasYTrampas.getGridJugador(), 1, 4);
        grid.setHalignment(regionesMagicasYTrampas.getGridJugador(), HPos.CENTER);
        grid.add(regionesMagicasYTrampas.getGridOponente(), 1, 1);
        grid.setHalignment(regionesMagicasYTrampas.getGridOponente(), HPos.CENTER);

        grid.add(regionesCampo.getRegionCampoJugador(), 0, 4);
        grid.setHalignment(regionesCampo.getRegionCampoJugador(), HPos.CENTER);
        grid.add(regionesCampo.getRegionCampoOponente(), 2, 1);
        grid.setHalignment(regionesCampo.getRegionCampoOponente(), HPos.CENTER);

        grid.add(regionesCementerio.getCementerioJugador(), 0, 3);
        grid.setHalignment(regionesCementerio.getCementerioJugador(), HPos.CENTER);
        grid.add(regionesCementerio.getCementerioOponente(), 2, 2);
        grid.setHalignment(regionesCementerio.getCementerioOponente(), HPos.CENTER);

        // -------------------------------
        // Manos.
        // -------------------------------
        grid.add(manos.getManoJugador(), 1, 5);
        grid.setHalignment(manos.getManoJugador(), HPos.CENTER);
        grid.add(manos.getManoOponente(), 1, 0);
        grid.setHalignment(manos.getManoOponente(), HPos.CENTER);

        // -------------------------------
        // Mazos.
        // -------------------------------
        grid.add(mazos.getMazoJugador(), 2, 5);
        grid.setHalignment(mazos.getMazoJugador(), HPos.CENTER);
        grid.add(mazos.getMazoOponente(), 0, 0);
        grid.setHalignment(mazos.getMazoOponente(), HPos.CENTER);

        // -------------------------------
        // Vida.
        // -------------------------------
        grid.add(vidas.getVidaJugador(), 0, 5);
        grid.setHalignment(vidas.getVidaJugador(), HPos.CENTER);
        grid.add(vidas.getVidaOponente(), 2, 0);
        grid.setHalignment(vidas.getVidaOponente(), HPos.CENTER);

        // -------------------------------
        // Botones de estado de juego.
        // -------------------------------
        grid.add(estadosJuegoBotones.getBotonFinDeFase(), 2, 4);
        grid.setHalignment(estadosJuegoBotones.getBotonFinDeFase(), HPos.CENTER);
        grid.add(estadosJuegoBotones.getBotonFinDeTurno(), 2, 3);
        grid.setHalignment(estadosJuegoBotones.getBotonFinDeTurno(), HPos.CENTER);

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
    public Escena cambiarEscena()
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
        this.regionesCampo.huboCambios();
        this.regionesMagicasYTrampas.huboCambios();
        this.regionesMonstruo.huboCambios();
        this.regionesCementerio.huboCambios();
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
