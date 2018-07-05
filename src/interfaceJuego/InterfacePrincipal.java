package interfaceJuego;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.FabricaCartas;
import controlador.Controlador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InterfacePrincipal extends Application
{
	
	private GridPane root;
	private Controlador controlador;
	
	public InterfacePrincipal(){
		
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
        
        this.controlador.establecerJugadores(jugador, oponente);
        
        InicializadorDeGrid inicializador = new InicializadorDeGrid();

        
        root = inicializador.inicializarGrid(root, primaryStage,this.controlador);
        

        
        primaryStage.show();
        
    }
    
    public static void main (String[] args){
    	
    	launch(args);
    	
    }
}
