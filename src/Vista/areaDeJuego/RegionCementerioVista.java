package Vista.areaDeJuego;

import Modelo.Modelo;
import Modelo.ObservadorModelo;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RegionCementerioVista implements ObservadorModelo
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    private Stage stage;
    private Modelo modelo;
    private Button botonCementerioJugador;
    private Button botonCementerioOponente;

    public RegionCementerioVista(Stage primaryStage, Modelo modelo)
    {

        this.stage = primaryStage;
        this.modelo = modelo;
        
        this.botonCementerioJugador = this.inicializarBoton();
        this.botonCementerioOponente = this.inicializarBoton();

    }

    private Button inicializarBoton(){
    	
    	Button boton = new Button();
    	
    	// TODO: generalizar el hardcodeo de los numeros.
        boton.setPrefSize(95.4, 139);
        boton.setStyle(estiloRegion);
        
        return boton;
    }

    public Button getCementerioJugador()
    {

        return botonCementerioJugador;
    }
    
    public Button getCementerioOponente()
    {

        return botonCementerioOponente;
    }

    @Override
    public void actualizar()
    {
        // TODO: pedir las cartas de la region, y pedirles sus imagenes para actualizar la vista de la region.
    }
}
