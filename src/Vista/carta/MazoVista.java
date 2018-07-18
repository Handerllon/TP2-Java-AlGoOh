package Vista.carta;

import Modelo.Modelo;
import Modelo.ObservadorDeModelo;
import Vista.Vista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class MazoVista implements ObservadorDeModelo
{
    //TODO: Buscar las resoluciones del sistema
    private static double anchoDeCarta = 95.4;
    private static double altoDeCarta = 139;
    private Stage stage;
    private Vista vista;
    private Button mazoJugador;
    private Tooltip toolTipJugador;
    private Button mazoOponente;
    private Tooltip toolTipOponente;

    public MazoVista(Stage primaryStage, Vista vista)
    {

        this.vista = vista;

        stage = primaryStage;

        this.toolTipJugador = new Tooltip();
        this.toolTipOponente = new Tooltip();
                
        this.mazoJugador = inicializar();
        this.mazoJugador.setTooltip(toolTipJugador);
        this.mazoOponente = inicializar();
        this.mazoOponente.setTooltip(toolTipOponente);
        
        this.actualizarEstado();
    }

    public Button inicializar()
    {

        Button boton = new Button();

        boton.setPrefSize(anchoDeCarta, altoDeCarta);
        boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource("resources/imagenes/tablero/Back.jpg").toString())), CornerRadii.EMPTY, Insets.EMPTY)));
       
        boton.setOnAction(e -> tomarCartaBtn_Click());

        return boton;
    }

    public Button getMazoJugador()
    {

        return mazoJugador;
    }

    public Button getMazoOponente()
    {

        return mazoOponente;
    }

    @Override
    public void actualizarEstado()
    {
        this.actualizarMazoJugador(this.vista.obtenerModelo().obtenerNumeroDeCartasRestantesEnMazoJugador());
        this.actualizarMazoOponente(this.vista.obtenerModelo().obtenerNumeroDeCartasRestantesEnMazoOponente());
    }

    private void actualizarMazoJugador(int numeroDeCartas)
    {
    	this.toolTipJugador.setText(Integer.toString(numeroDeCartas));
    }


	private void actualizarMazoOponente(int numeroDeCartas)
    {
		this.toolTipOponente.setText(Integer.toString(numeroDeCartas));
        
    }
	private void tomarCartaBtn_Click() {
		
		this.vista.obtenerControlador().tomarCarta();
		
	}
}
