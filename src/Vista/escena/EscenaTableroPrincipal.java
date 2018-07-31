package Vista.escena;

import Modelo.ModeloObservable;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import Vista.carta.MazosVista;
import Vista.carta.mano.ManosVista;
import Vista.estadosJuego.EstadosJuegoBotones;
import Vista.estadosJuego.FaseActualVista;
import Vista.estadosJuego.TurnoActualVista;
import Vista.estadosJuego.VidaVista;
import Vista.region.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public final class EscenaTableroPrincipal implements Escena
{
    private static EscenaTableroPrincipal instancia = null;
    private static double porcentajeDePantallaVertical = 0.157;
    private static double porcentajeDePantallaHorizontal = 0.52;
    private GridPane gridPane;
    private String RUTA_TABLERO = "resources/imagenes/fondoTableroGeneral.png";
    private String direccion_sonido_batalla = "src/resources/audio/battle_2.wav";
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
    private TurnoActualVista turnoActualVista;
    private FaseActualVista faseActualVista;
    private double soundBattleVolume = 0.1;

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
        this.turnoActualVista = new TurnoActualVista(this.vista);
        this.faseActualVista = new FaseActualVista(this.vista);

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
        ColumnConstraints column0 = new ColumnConstraints(this.vista.getResolucionHorizontal() * (1 - porcentajeDePantallaHorizontal) / 2);
        ColumnConstraints column1 = new ColumnConstraints(this.vista.getResolucionHorizontal() * porcentajeDePantallaHorizontal);
        ColumnConstraints column2 = new ColumnConstraints(this.vista.getResolucionHorizontal() * (1 - porcentajeDePantallaHorizontal) / 2);
        RowConstraints row0 = new RowConstraints(this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        RowConstraints row1 = new RowConstraints(this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        RowConstraints row2 = new RowConstraints(this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        RowConstraints row3 = new RowConstraints(this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        RowConstraints row4 = new RowConstraints(this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
        RowConstraints row5 = new RowConstraints(this.vista.getResolucionVertical() * porcentajeDePantallaVertical);
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
        this.mplayer.setVolume(this.soundBattleVolume);

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

        // -------------------------------
        // Estados de juego.
        // -------------------------------
        gridPane.add(turnoActualVista.getDisplayTurnoActual(), 0, 1);
        gridPane.add(faseActualVista.getLabelFaseActual(), 0, 2);
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
    @Override
    public Escena cambiarEscena()
    {
        return new EscenaFinDeJuego(this.modelo, this.vista);
    }

    @Override
    public void mostrar()
    {
        this.playMedia();
        this.primaryStage.show();
        this.turnoActualVista.seTerminoElTurno();
        this.faseActualVista.seTerminoLaFase();
    }

    @Override
    public void actualizarDibujo()
    {
        gridPane.getChildren().clear();
        setGridPaneVista();
    }

    @Override
    public void finDeJuego()
    {
        this.stopMedia();
        this.vista.setProximaEscena(new EscenaFinDeJuego(this.modelo, this.vista));
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
    public void solicitarCartaAAtacar(CartaMonstruo cartaAtacante)
    {

        Popup popup = new Popup();
        ArrayList<BotonDeCartaMonstruoSolicitada> botonesDeCartas = new ArrayList<BotonDeCartaMonstruoSolicitada>();
        HBox hbox = new HBox();

        if (this.vista.getControlador().getJugadorActual() == this.modelo.getJugador())
        {
            this.vista.getModelo().getCartasEnRegionMonstruosOponente().forEach(cartaDelOponente
                    -> botonesDeCartas.add(new BotonDeCartaMonstruoSolicitada(this.vista, cartaAtacante, cartaDelOponente, popup)));
        } else
        {
            this.vista.getModelo().getCartasEnRegionMonstruosJugador().forEach(cartaDelOponente
                    -> botonesDeCartas.add(new BotonDeCartaMonstruoSolicitada(this.vista, cartaAtacante, cartaDelOponente, popup)));
        }

        Button b1 = new Button("Close");

        b1.setOnAction(e -> cerrarPopupBtn_Click(popup));

        botonesDeCartas.forEach(boton -> hbox.getChildren().add(boton.getBoton()));

        hbox.getChildren().add(b1);

        popup.show(this.vista.getPrimaryStage());

        popup.getContent().add(hbox);

        popup.setX(this.vista.getResolucionHorizontal() / 2);
        popup.setY(this.vista.getResolucionVertical() / 2);
    }

    private void cerrarPopupBtn_Click(Popup popup)
    {

        popup.hide();
    }
}
