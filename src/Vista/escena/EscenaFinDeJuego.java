package Vista.escena;

import Modelo.ModeloObservable;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.finDeJuego.CausaFinJuego;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

public class EscenaFinDeJuego implements Escena
{
    private static double soundGameEndVolume = 0.3;
    private static String direccion_imagen = "resources/imagenes/end.jpg";
    private static String direccion_sonido_gano = "src/resources/audio/winner_2.wav";
    private static String direccion_sonido_perdio = "src/resources/audio/gameover_2.wav";
    private static Vista vista;
    private static MediaPlayer mplayer;
    private ModeloObservable modelo;
    private Stage primaryStage;
    private Scene escenaFinDeJuego;
    private GridPane grid;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public EscenaFinDeJuego(ModeloObservable modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        this.primaryStage = this.vista.getPrimaryStage();

        this.grid = new GridPane();

        this.inicializarEscena();
    }

    private void inicializarEscena()
    {
        Image imagenBack = new Image(getClass().getClassLoader().getResource(this.direccion_imagen).toString());

        this.escenaFinDeJuego = new Scene(this.grid, imagenBack.getWidth(), imagenBack.getHeight());
        this.primaryStage.setScene(this.escenaFinDeJuego);

        File f;

        CausaFinJuego causaFinJuego = this.vista.getControlador().getCausaFinDeJuego();

        Label lblTitulo = new Label();

        lblTitulo.setMinWidth(100);
        lblTitulo.setAlignment(Pos.BOTTOM_CENTER);

        lblTitulo.setFont(new Font("Bauhaus 93", 20));
        lblTitulo.setStyle("-fx-font-weight: bolder; -fx-text-fill: yellow");

        this.grid.setBackground(new Background(new BackgroundFill(new ImagePattern(imagenBack), CornerRadii.EMPTY, Insets.EMPTY)));

        // -------------------------------
        // Causa de fin de juego.
        // -------------------------------
        f = new File(direccion_sonido_perdio);

        String nombreJugadorFinalizo = causaFinJuego.getNombreJugador();
        if (causaFinJuego.debidoACincoPartesDeExodiaReunidas())
        {
            f = new File(direccion_sonido_gano);
            lblTitulo.setText("Cinco partes de Exodia reuidas.\n" + nombreJugadorFinalizo + " gana el juego.");
        } else if (causaFinJuego.debidoAPuntosDeVidaNulos())
        {
            lblTitulo.setText("Puntos de vida nulos.\n" + nombreJugadorFinalizo + " pierde el juego.");
        } else if (causaFinJuego.debidoASinCartasEnMazo())
        {
            lblTitulo.setText("Sin cartas en el mazo.\n" + nombreJugadorFinalizo + " pierde el juego.");
        }

        // -------------------------------
        // Audio.
        // -------------------------------
        Media media = new Media(f.toURI().toString());
        this.mplayer = new MediaPlayer(media);
        this.mplayer.setVolume(this.soundGameEndVolume);

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
        this.grid.setPadding(new Insets(10));
        this.grid.setHgap(10);
        this.grid.setVgap(10);
        this.grid.setMinWidth(500);

        this.grid.addRow(0, lblTitulo);
        this.grid.setHalignment(lblTitulo, HPos.CENTER);
        this.grid.addRow(1, paneButtons);
        this.grid.setHalignment(paneButtons, HPos.CENTER);
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
    public Escena cambiarEscena()
    {
        return EscenaNula.getInstancia();
    }

    @Override
    public void mostrar()
    {
        this.playMedia();
        this.primaryStage.centerOnScreen();
        this.primaryStage.show();
    }

    @Override
    public void actualizarDibujo()
    {

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

    @Override
    public void solicitarCartaAAtacar(CartaMonstruo cartaAtacante)
    {

    }
}
