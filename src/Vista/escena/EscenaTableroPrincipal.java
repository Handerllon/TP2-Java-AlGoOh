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
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.File;

public final class EscenaTableroPrincipal implements Escena
{
    private static EscenaTableroPrincipal instancia = null;
    private static double porcentajeDePantallaVertical = 0.157;
    private static double porcentajeDePantallaHorizontal = 0.520;
    private GridPane gridPane;
    private String RUTA_TABLERO = "resources/imagenes/fondoTableroGeneral.png";
    private String direccion_sonido_batalla = "src/resources/audio/battle.mp3";
    private Vista vista;
    private MediaPlayer mplayer;
    private RegionesMonstruoVista regionesMonstruoVista;
    private RegionesMagicasYTrampasVista regionesMagicasYTrampasVista;
    private RegionesCampoVista regionesCampoVista;
    private RegionesCementerioVista regionesCementerioVista;
    private ManosVista manosVista;
    private MazosVista mazosVista;
    private VidaVista vidasVista;
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

        this.gridPane = new GridPane();

        this.regionesMonstruoVista = new RegionesMonstruoVista(this.vista);
        this.regionesMagicasYTrampasVista = new RegionesMagicasYTrampasVista(this.vista);
        this.regionesCampoVista = new RegionesCampoVista(this.vista);
        this.regionesCementerioVista = new RegionesCementerioVista(this.vista);

        this.manosVista = new ManosVista(this.vista);
        this.mazosVista = new MazosVista(this.vista);
        this.vidasVista = new VidaVista(this.vista);

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
        this.escenaTableroPrincipal = new Scene(gridPane, this.vista.getResolucionHorizontal(), this.vista.getResolucionVertical());
        this.primaryStage.setScene(this.escenaTableroPrincipal);
        this.primaryStage.setMaximized(true);

        gridPane.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
        		.getResource(RUTA_TABLERO).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setPrefSize(this.vista.getResolucionHorizontal(), this.vista.getResolucionVertical());

        // -------------------------------
        // Estructura de la vista.
        // -------------------------------
        gridPane.setGridLinesVisible(false);
        gridPane.setPadding(new Insets(5));
        //Se uso una resoulcion de 1080x1920 como base para los tamanos
        ColumnConstraints column0 = new ColumnConstraints(this.vista.getResolucionHorizontal()*(1-porcentajeDePantallaHorizontal)/2);
        ColumnConstraints column1 = new ColumnConstraints(this.vista.getResolucionHorizontal()*porcentajeDePantallaHorizontal);
        ColumnConstraints column2 = new ColumnConstraints(this.vista.getResolucionHorizontal()*(1-porcentajeDePantallaHorizontal)/2);
        RowConstraints row0 = new RowConstraints(this.vista.getResolucionVertical()*porcentajeDePantallaVertical);
        RowConstraints row1 = new RowConstraints(this.vista.getResolucionVertical()*porcentajeDePantallaVertical);
        RowConstraints row2 = new RowConstraints(this.vista.getResolucionVertical()*porcentajeDePantallaVertical);
        RowConstraints row3 = new RowConstraints(this.vista.getResolucionVertical()*porcentajeDePantallaVertical);
        RowConstraints row4 = new RowConstraints(this.vista.getResolucionVertical()*porcentajeDePantallaVertical);
        RowConstraints row5 = new RowConstraints(this.vista.getResolucionVertical()*porcentajeDePantallaVertical);
        gridPane.getColumnConstraints().addAll(column0, column1, column2);
        gridPane.getRowConstraints().addAll(row0, row1, row2, row3, row4, row5);

        gridPane.setGridLinesVisible(true);
        
        // Se configura la grid pane.
        setGridPaneVista();

        // -------------------------------
        // Multimedia.
        // -------------------------------
        File f = new File(direccion_sonido_batalla);
        Media media = new Media(f.toURI().toString());
        this.mplayer = new MediaPlayer(media);
        this.mplayer.setCycleCount(MediaPlayer.INDEFINITE);
    }

    private void setGridPaneVista()
    {
        // -------------------------------
        // Regiones.
        // -------------------------------
        gridPane.add(regionesMonstruoVista.getGridJugador(), 1, 3);
        gridPane.setHalignment(regionesMonstruoVista.getGridJugador(), HPos.CENTER);
        gridPane.add(regionesMonstruoVista.getGridOponente(), 1, 2);
        gridPane.setHalignment(regionesMonstruoVista.getGridOponente(), HPos.CENTER);

        gridPane.add(regionesMagicasYTrampasVista.getGridJugador(), 1, 4);
        gridPane.setHalignment(regionesMagicasYTrampasVista.getGridJugador(), HPos.CENTER);
        gridPane.add(regionesMagicasYTrampasVista.getGridOponente(), 1, 1);
        gridPane.setHalignment(regionesMagicasYTrampasVista.getGridOponente(), HPos.CENTER);

        gridPane.add(regionesCampoVista.getRegionCampoJugador(), 0, 4);
        gridPane.setHalignment(regionesCampoVista.getRegionCampoJugador(), HPos.RIGHT);
        gridPane.add(regionesCampoVista.getRegionCampoOponente(), 2, 1);
        gridPane.setHalignment(regionesCampoVista.getRegionCampoOponente(), HPos.LEFT);

        gridPane.add(regionesCementerioVista.getCementerioJugador(), 0, 3);
        gridPane.setHalignment(regionesCementerioVista.getCementerioJugador(), HPos.RIGHT);
        gridPane.add(regionesCementerioVista.getCementerioOponente(), 2, 2);
        gridPane.setHalignment(regionesCementerioVista.getCementerioOponente(), HPos.LEFT);

        // -------------------------------
        // Manos.
        // -------------------------------
        gridPane.add(manosVista.getManoJugador(), 1, 5);
        gridPane.setHalignment(manosVista.getManoJugador(), HPos.CENTER);
        gridPane.add(manosVista.getManoOponente(), 1, 0);
        gridPane.setHalignment(manosVista.getManoOponente(), HPos.CENTER);

        // -------------------------------
        // Mazos.
        // -------------------------------
        gridPane.add(mazosVista.getMazoJugador(), 2, 5);
        gridPane.setHalignment(mazosVista.getMazoJugador(), HPos.LEFT);
        gridPane.add(mazosVista.getMazoOponente(), 0, 0);
        gridPane.setHalignment(mazosVista.getMazoOponente(), HPos.RIGHT);

        // -------------------------------
        // Vida.
        // -------------------------------
        gridPane.add(vidasVista.getVidaJugador(), 0, 5);
        gridPane.setHalignment(vidasVista.getVidaJugador(), HPos.RIGHT);
        gridPane.add(vidasVista.getVidaOponente(), 2, 0);
        gridPane.setHalignment(vidasVista.getVidaOponente(), HPos.LEFT);

        // -------------------------------
        // Botones de estado de juego.
        // -------------------------------
        gridPane.add(estadosJuegoBotones.getBotonFinDeFase(), 2, 4);
        gridPane.setHalignment(estadosJuegoBotones.getBotonFinDeFase(), HPos.LEFT);
        gridPane.add(estadosJuegoBotones.getBotonFinDeTurno(), 2, 3);
        gridPane.setHalignment(estadosJuegoBotones.getBotonFinDeTurno(), HPos.LEFT);
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
    public void mostrar()
    {
        this.playMedia();
        this.primaryStage.show();
        this.vista.mostrarJugadorActual();
        //this.vista.mostrarFaseActual();
    }

    @Override
    public void actualizarEstado()
    {
        this.regionesCampoVista.huboCambios();
        this.regionesMagicasYTrampasVista.huboCambios();
        this.regionesMonstruoVista.huboCambios();
        this.regionesCementerioVista.huboCambios();
        this.manosVista.huboCambios();
        this.mazosVista.huboCambios();
        this.actualizarDibujo();
    }

    private void actualizarDibujo()
    {
        gridPane.getChildren().clear();
        setGridPaneVista();
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

    @Override
    public GridPane getGridPaneEscena()
    {
        return this.gridPane;
    }
}
