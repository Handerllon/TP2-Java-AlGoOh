package Vista.carta;

import Modelo.Modelo;
import Modelo.ObservadorModelo;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MazoVista implements ObservadorModelo
{
    private Stage stage;
    private Modelo modelo;
    private Button mazoJugador;
    private Button mazoOponente;

    public MazoVista(Stage primaryStage, Modelo modelo)
    {

        this.modelo = modelo;

        stage = primaryStage;

        this.mazoJugador = inicializar();
        this.mazoOponente = inicializar();
    }

    public Button inicializar()
    {

        Button boton = new Button();

        boton.setPrefSize(95.4, 139);
        boton.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass().getClassLoader()
                .getResource("resources/imagenes/tablero/Back.jpg").toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        

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
    public void actualizar()
    {
        this.actualizarMazoJugador(this.modelo.obtenerNumeroDeCartasRestantesEnMazoJugador());
        this.actualizarMazoOponente(this.modelo.obtenerNumeroDeCartasRestantesEnMazoOponente());
    }

    private void actualizarMazoJugador(int numeroDeCartas) {
    	
    	Tooltip tp = new Tooltip();
    	tp.setText("Cartas restantes en el mazo: \n" + Integer.valueOf(numeroDeCartas).toString());
    	mazoJugador.setTooltip(tp);
    	
    }
    
	private void actualizarMazoOponente(int numeroDeCartas) {
		
		Tooltip tp = new Tooltip();
    	tp.setText("Cartas restantes en el mazo: \n" + Integer.valueOf(numeroDeCartas).toString());
    	mazoOponente.setTooltip(tp);
	}

}
