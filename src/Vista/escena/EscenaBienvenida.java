package Vista.escena;

import Modelo.ModeloObservable;
import Modelo.carta.Carta;
import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import java.io.File;

    public final class EscenaBienvenida implements Escena
{
    private static String direccion_video_bienvenida = "src/resources/video/welcome_yugioh.mp4";
    private static int ANCHO_CAMPO_NOMBRE = 15;
    private static Vista vista;
    private static Escena proximaEscena = EscenaNula.getInstancia();
    private static MediaPlayer mplayer;
    private static EscenaBienvenida instancia = null;
    private ModeloObservable modelo;
    private Scene escenaBienvenida;
    private Stage primaryStage;
    private TextField txtJugador1, txtJugador2;
    private GridPane grid;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private EscenaBienvenida(ModeloObservable modelo, Vista vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        this.primaryStage = this.vista.getPrimaryStage();
        this.grid = new GridPane();

        this.inicializarEscena();
    }

    public static EscenaBienvenida getInstancia(ModeloObservable modelo, Vista vista)
    {
        if (instancia == null)
        {
            instancia = new EscenaBienvenida(modelo, vista);
        }
        return instancia;
    }

    public EscenaBienvenida clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    private void inicializarEscena()
    {
        this.escenaBienvenida = new Scene(this.grid);
        this.primaryStage.setScene(this.escenaBienvenida);

        Label lblTitulo = new Label("Ingresar nombres de jugadores");
        lblTitulo.setMinWidth(100);
        lblTitulo.setAlignment(Pos.BOTTOM_CENTER);
        lblTitulo.setStyle("-fx-font-weight: bolder; -fx-font-size: 12pt");

        Label lblJugador1 = new Label("Nombre del jugador 1:");
        lblJugador1.setMinWidth(100);
        lblJugador1.setAlignment(Pos.BOTTOM_RIGHT);
        lblJugador1.setStyle("-fx-font-weight: bolder; -fx-font-size: 12pt");
        this.txtJugador1 = new TextField();
        txtJugador1.setPrefColumnCount(ANCHO_CAMPO_NOMBRE);
        txtJugador1.setPromptText("Escribir el nombre del jugador 1 aquí.");

        Label lblJugador2 = new Label("Nombre del jugador 2:");
        lblJugador2.setMinWidth(100);
        lblJugador2.setAlignment(Pos.BOTTOM_RIGHT);
        lblJugador2.setStyle("-fx-font-weight: bolder; -fx-font-size: 12pt");
        this.txtJugador2 = new TextField();
        txtJugador2.setPrefColumnCount(ANCHO_CAMPO_NOMBRE);
        txtJugador2.setPromptText("Escribir el nombre del jugador 2 aquí.");

        // -------------------------------
        // Definición de botones.
        // -------------------------------
        Button btnOK = new Button("Ok");
        btnOK.setMinWidth(75);
        btnOK.setOnAction(e -> okBtn_click());

        Button btnSalir = new Button("Salir");
        btnSalir.setMinWidth(75);
        btnSalir.setOnAction(e -> salirBtn_click());

        HBox paneButtons = new HBox(10, btnOK, btnSalir);

        // -------------------------------
        // Multimedia.
        // -------------------------------
        File f = new File(direccion_video_bienvenida);
        Media media = new Media(f.toURI().toString());
        this.mplayer = new MediaPlayer(media);
        this.mplayer.setVolume(0.05);
        this.mplayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mview = new MediaView(mplayer);
        mview.setFitWidth(700);
        mview.setFitHeight(500);
        HBox paneVideo = new HBox(mview);

        // -------------------------------
        // Configuración de la grilla.
        // -------------------------------
        this.grid.setPadding(new Insets(10));
        this.grid.setHgap(10);
        this.grid.setVgap(10);
        this.grid.setMinWidth(500);

        this.grid.addRow(0, lblTitulo);
        this.grid.setColumnSpan(lblTitulo, 2);
        this.grid.setHalignment(lblTitulo, HPos.CENTER);
        this.grid.addRow(1, lblJugador1, txtJugador1);
        this.grid.addRow(2, lblJugador2, txtJugador2);
        this.grid.addRow(3, paneButtons);
        this.grid.addRow(4, paneVideo);
        this.grid.setColumnSpan(paneVideo, 2);
        this.grid.setHalignment(paneVideo, HPos.CENTER);
    }

    // --------------------------------------------------------------------
    // Implementación acción botones.
    // --------------------------------------------------------------------
    private void salirBtn_click()
    {
        this.vista.getControlador().confirmarSalirPrograma();
    }

    private void okBtn_click()
    {
        this.vista.getControlador().setNombreJugador(this.txtJugador1.getText());
        this.vista.getControlador().setNombreOponente(this.txtJugador2.getText());

        this.vista.setProximaEscena(this.cambiarEscena());
        this.cerrar();
        // Ahora pasamos a la escena donde se muestra qué jugador salió sorteado.
        this.vista.mostrar();
    }

    // --------------------------------------------------------------------
    // Métodos de escena.
    // --------------------------------------------------------------------
    @Override
    public Escena cambiarEscena()
    {
        return EscenaSorteoJugadorInicial.getInstancia(this.modelo, this.vista);
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

    }
}
