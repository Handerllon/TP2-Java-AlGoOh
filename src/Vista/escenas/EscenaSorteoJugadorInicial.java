package Vista.escenas;

import Modelo.Modelo;
import Vista.EscenaVista;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

public final class EscenaSorteoJugadorInicial implements EscenaVista
{
    private static String direccion_video_sorteo = "src/resources/video/draw_galaxy.mp4";
    private static int ANCHO_CAMPO_NOMBRE = 15;
    private static Vista vista;
    private static EscenaVista proximaEscena = EscenaNula.obtenerInstancia();
    private static MediaPlayer mplayer;
    private static EscenaSorteoJugadorInicial instancia = null;
    private Modelo modelo;
    private Scene escenaSorteoJugadorInicial;
    private Stage primaryStage;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaSorteoJugadorInicial(Modelo modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        this.primaryStage = this.vista.obtenerPrimaryStage();
        this.inicializarEscena();
    }

    public static EscenaSorteoJugadorInicial obtenerInstancia(Modelo modelo, Vista vista)
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
        GridPane grid = new GridPane();
        this.escenaSorteoJugadorInicial = new Scene(grid);
        this.primaryStage.setScene(this.escenaSorteoJugadorInicial);

        Label lblTitulo = new Label("Sorteo de jugador inicial.");
        lblTitulo.setMinWidth(100);
        lblTitulo.setAlignment(Pos.BOTTOM_CENTER);
        lblTitulo.setStyle("-fx-font-weight: bolder; -fx-font-size: 12pt");

        Label lblJugadorSorteado = new Label(this.vista.obtenerControlador().obtenerNombreJugadorActual());
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
        // Video.
        // -------------------------------
        File f = new File(direccion_video_sorteo);
        Media media = new Media(f.toURI().toString());
        this.mplayer = new MediaPlayer(media);
        this.mplayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mview = new MediaView(mplayer);
        mview.setFitWidth(700);
        mview.setFitHeight(500);
        HBox paneVideo = new HBox(mview);

        // -------------------------------
        // Setting grid.
        // -------------------------------
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setMinWidth(500);

        grid.addRow(0, lblTitulo);
        grid.addRow(1, lblJugadorSorteado);
        grid.addRow(2, paneButtons);
        grid.addRow(3, paneVideo);
    }

    // --------------------------------------------------------------------
    // Implementación de botones.
    // --------------------------------------------------------------------
    private void salirBtn_click()
    {
        this.vista.obtenerControlador().confirmarSalirPrograma();
    }

    private void jugarBtn_click()
    {
        this.vista.establecerProximaEscena(this.cambiarEscena());
        this.cerrar();

        // TODO: estas líneas deberían encapsular en un Command que levante el controlador.
        this.vista.obtenerControlador().jugar();
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
    @Override
    public EscenaVista cambiarEscena()
    {
        return EscenaTableroPrincipal.obtenerInstancia(this.modelo, this.vista);
    }

    @Override
    public void dibujarEscena()
    {
        this.playMedia();
        this.primaryStage.show();
    }

    @Override
    public void actualizarEstado()
    {

    }

    @Override
    public boolean terminoElJuego()
    {
        return false;
    }

    @Override
    public void finDeJuego()
    {
        this.stopMedia();
        this.vista.establecerProximaEscena(EscenaFinDeJuego.obtenerInstancia(this.modelo, this.vista));
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
