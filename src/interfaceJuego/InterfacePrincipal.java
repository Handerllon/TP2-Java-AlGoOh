package interfaceJuego;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class InterfacePrincipal extends Application
{
	
	private GridPane root;
	
	public InterfacePrincipal(){
		
		this.root = new GridPane();
		
	}

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        primaryStage.setTitle("AlGoOh");

        Scene scenePrincipal = new Scene(root, 1920, 1000);
        primaryStage.setScene(scenePrincipal);

        InicializadorDeGrid inicializador = new InicializadorDeGrid();

        root = inicializador.inicializarGrid(root, primaryStage);
        
        primaryStage.show();
        
    }
    
}
