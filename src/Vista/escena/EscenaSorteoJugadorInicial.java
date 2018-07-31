package Vista.escena;

import Modelo.ModeloObservable;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

public final class EscenaSorteoJugadorInicial implements Escena
{
    private static String direccion_video_sorteo = "src/resources/video/draw_galaxy.mp4";
    private static String direccion_sonido_sorteo = "src/resources/audio/player_draw.mp3";
    private static int ANCHO_CAMPO_NOMBRE = 15;
    private static Vista vista;
    private static Escena proximaEscena = EscenaNula.getInstancia();
    private static MediaPlayer mplayerVideo;
    private static MediaPlayer mplayerMusic;
    private static EscenaSorteoJugadorInicial instancia = null;
    private ModeloObservable modelo;
    private Scene escenaSorteoJugadorInicial;
    private Stage primaryStage;
    private GridPane grid;
    private AudioClip audioClipPlayButton;
    private double playButtonVolume = 0.3;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaSorteoJugadorInicial(ModeloObservable modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        this.primaryStage = this.vista.getPrimaryStage();

        this.grid = new GridPane();

        this.inicializarEscena();
    }

    public static EscenaSorteoJugadorInicial getInstancia(ModeloObservable modelo, Vista vista)
    {
        if (instancia == null)
        {
            instancia = new EscenaSorteoJugadorInicial(modelo, vista);
        }
        return instancia;
    }

    public EscenaSorteoJugadorInicial clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    private void inicializarEscena()
    {
        this.escenaSorteoJugadorInicial = new Scene(this.grid);
        this.primaryStage.setScene(this.escenaSorteoJugadorInicial);

        Label lblTitulo = new Label("Sorteo de jugador inicial.");
        lblTitulo.setMinWidth(100);
        lblTitulo.setAlignment(Pos.BOTTOM_CENTER);
        lblTitulo.setStyle("-fx-font-weight: bolder; -fx-font-size: 12pt");

        Label lblJugadorSorteado = new Label(this.vista.getControlador().getNombreJugadorActual());
        lblJugadorSorteado.setMinWidth(100);
        lblJugadorSorteado.setAlignment(Pos.BOTTOM_CENTER);
        lblJugadorSorteado.setStyle("-fx-font-weight: bolder; -fx-font-size: 12pt");

        // -------------------------------
        // Buttons definitions.
        // -------------------------------
        Button btnOK = new Button("Jugar");
        btnOK.setMinWidth(75);
        btnOK.setOnAction(e -> jugarBtn_click());

        Button btnSalir = new Button("Salir");
        btnSalir.setMinWidth(75);
        btnSalir.setOnAction(e -> salirBtn_click());

        HBox paneButtons = new HBox(10, btnOK, btnSalir);

        // -------------------------------
        // Multimedia.
        // -------------------------------
        File fVideo = new File(direccion_video_sorteo);
        Media video = new Media(fVideo.toURI().toString());
        this.mplayerVideo = new MediaPlayer(video);
        this.mplayerVideo.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mview = new MediaView(mplayerVideo);
        mview.setFitWidth(700);
        mview.setFitHeight(500);
        HBox paneVideo = new HBox(mview);

        File fMusic = new File(direccion_sonido_sorteo);
        Media music = new Media(fMusic.toURI().toString());
        this.mplayerMusic = new MediaPlayer(music);
        this.mplayerMusic.setVolume(0.05);
        this.mplayerMusic.setCycleCount(MediaPlayer.INDEFINITE);

        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/start.wav");
        this.audioClipPlayButton = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipPlayButton.setVolume(playButtonVolume);

        // -------------------------------
        // Setting grid.
        // -------------------------------
        this.grid.setPadding(new Insets(10));
        this.grid.setHgap(10);
        this.grid.setVgap(10);
        this.grid.setMinWidth(500);

        this.grid.addRow(0, lblTitulo);
        this.grid.addRow(1, lblJugadorSorteado);
        this.grid.addRow(2, paneButtons);
        this.grid.addRow(3, paneVideo);
    }

    // --------------------------------------------------------------------
    // Implementación de botones.
    // --------------------------------------------------------------------
    private void salirBtn_click()
    {
        this.vista.getControlador().confirmarSalirPrograma();
    }

    private void jugarBtn_click()
    {
        this.audioClipPlayButton.play();
        this.vista.setProximaEscena(this.cambiarEscena());
        this.cerrar();

        this.vista.getControlador().jugar();
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
    @Override
    public Escena cambiarEscena()
    {
        return EscenaTableroPrincipal.getInstancia(this.modelo, this.vista);
    }

    @Override
    public void mostrar()
    {
        this.playMedia();
        this.primaryStage.show();
    }

    @Override
    public void actualizarDibujo()
    {

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
        this.mplayerVideo.play();
        this.mplayerMusic.play();
    }

    @Override
    public void stopMedia()
    {
        this.mplayerVideo.stop();
        this.mplayerMusic.stop();
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

    }
}
