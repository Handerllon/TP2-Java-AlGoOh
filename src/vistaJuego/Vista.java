package vistaJuego;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.FabricaCartas;
import controlador.Controlador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Vista extends Application
{
	
	private GridPane root;
	private Controlador controlador;
	
	public Vista(){
		
		this.root = new GridPane();
		this.controlador = new Controlador();
		
	}

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        primaryStage.setTitle("AlGoOh");

        Scene scenePrincipal = new Scene(root, 1920, 1000);
        primaryStage.setScene(scenePrincipal);

        Jugador jugador = new Jugador("Manu");
        Jugador oponente = new Jugador("Nico");
        
        jugador.establecerOponente(oponente);
        oponente.establecerOponente(jugador);

        jugador.crearMazo();
        oponente.crearMazo();
        
        this.controlador.establecerJugadores(jugador, oponente);
        
        InicializadorDeVista inicializador = new InicializadorDeVista();

        
        root = inicializador.inicializarVista(root, primaryStage,this.controlador,jugador,oponente);
        

        
        primaryStage.show();
        
    }
    
    public static void main (String[] args){
    	
    	launch(args);
    	
    }
}
