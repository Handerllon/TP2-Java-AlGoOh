package Vista.escenas;

import Modelo.ModeloObservable;
import Modelo.finDeJuego.CausaCincoPartesExodiaReunidas;
import Modelo.finDeJuego.CausaFinJuego;
import Vista.EscenaVista;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;

import java.io.File;

public final class EscenaFinDeJuego implements EscenaVista
{
    private static String direccion_imagen = "resources/imagenes/tablero/end.jpg";
    private static String direccion_sonido_gano = "src/resources/audio/winner.mp3";
    private static String direccion_sonido_perdio = "src/resources/audio/gameover.mp3";
    private static Vista vista;
    private static MediaPlayer mplayer;
    private static EscenaFinDeJuego instancia = null;
    private ModeloObservable modelo;
    private Stage primaryStage;
    private Scene escenaFinDeJuego;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaFinDeJuego(ModeloObservable modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        this.primaryStage = this.vista.obtenerPrimaryStage();

        this.inicializarEscena();
    }

    public static EscenaFinDeJuego obtenerInstancia(ModeloObservable modelo, Vista vista)
    {
        if (instancia == null)
        {
            instancia = new EscenaFinDeJuego(modelo, vista);
        }
        return instancia;
    }

    public EscenaFinDeJuego clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    private void inicializarEscena()
    {
        GridPane grid = new GridPane();
        this.escenaFinDeJuego = new Scene(grid, 395, 296);
        this.primaryStage.setScene(this.escenaFinDeJuego);

        File f;

        CausaFinJuego causaFinJuego = this.vista.obtenerControlador().obtenerCausaFinDeJuego();

        String nombreJugadorFinalizo = causaFinJuego.obtenerNombreJugador();
        Label lblTitulo = new Label();

        lblTitulo.setMinWidth(100);
        lblTitulo.setAlignment(Pos.BOTTOM_CENTER);
        lblTitulo.setStyle("-fx-font-weight: bolder; -fx-font-size: 12pt");

        if (causaFinJuego.obtenerCausa() == (new CausaCincoPartesExodiaReunidas(null)).obtenerCausa())
        {
            f = new File(direccion_sonido_gano);
            lblTitulo.setText(nombreJugadorFinalizo + " GANADOR");
        } else
        {
            f = new File(direccion_sonido_perdio);
            lblTitulo.setText(nombreJugadorFinalizo + " PERDEDOR");
        }

        grid.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource(this.direccion_imagen).toString())), CornerRadii.EMPTY, Insets.EMPTY)));

        // -------------------------------
        // Audio.
        // -------------------------------
        Media media = new Media(f.toURI().toString());
        this.mplayer = new MediaPlayer(media);
        this.mplayer.setCycleCount(MediaPlayer.INDEFINITE);

        // -------------------------------
        // Buttons definitions.
        // -------------------------------
        Button btnSalir = new Button("Salir del juego");
        btnSalir.setMinWidth(75);
        btnSalir.setOnAction(e -> salirBtn_click());

        HBox paneButtons = new HBox(10, btnSalir);

        // -------------------------------
        // Setting grid.
        // -------------------------------
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setMinWidth(500);

        grid.addRow(0, lblTitulo);
        grid.setHalignment(lblTitulo, HPos.CENTER);
        grid.addRow(1, paneButtons);
        grid.setHalignment(paneButtons, HPos.CENTER);
    }

    // --------------------------------------------------------------------
    // Buttons implementations.
    // --------------------------------------------------------------------
    private void salirBtn_click()
    {
        this.stopMedia();
        this.vista.cerrar();
    }

    @Override
    public EscenaVista cambiarEscena()
    {
        return EscenaNula.obtenerInstancia();
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
        this.primaryStage.close();
    }
}
