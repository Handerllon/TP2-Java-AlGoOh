package Vista.areaDeJuego;

import Modelo.Jugador;
import Modelo.Modelo;
import Observador.ObjetoObservador;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCampoVista implements ObjetoObservador
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    private Stage stage;
    private Modelo modelo;
    private Button botonCampoJugador;
    private Button botonCampoOponente;

    public RegionCampoVista(Stage primaryStage, Modelo modelo)
    {
    	stage = primaryStage;
    	this.modelo = modelo;

        this.botonCampoJugador = this.inicializarBoton();
        this.botonCampoOponente = this.inicializarBoton();
    }
    
    private Button inicializarBoton(){
    	
    	Button boton = new Button();
    	
    	// TODO: generalizar el hardcodeo de los numeros.
        boton.setPrefSize(95.4, 139);
        boton.setStyle(estiloRegion);
        
        return boton;
    }

    public Button getRegionCampoJugador()
    {

        return botonCampoJugador;
    }
    
    public Button getRegionCampoOponente()
    {

        return botonCampoOponente;
    }

    @Override
    public void actualizar()
    {
        // TODO: pedir las cartas de la region, y pedirles sus imagenes para actualizar la vista de la region.
    }
}
