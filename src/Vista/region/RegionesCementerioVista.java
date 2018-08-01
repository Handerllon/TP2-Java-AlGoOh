package Vista.region;

import Modelo.observadores.ObservadorDeModelo;
import Vista.Vista;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class RegionesCementerioVista implements ObservadorDeModelo
{
    private static String estiloRegion = "-fx-background-color: Transparent";
    private static String rutaImagenReversoCarta = "resources/imagenes/cartaReverso.jpg";
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private Vista vista;
    private Button botonCementerioJugador;
    private Button botonCementerioOponente;
	private StackPane stackJugador;
	private StackPane stackOponente;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    public RegionesCementerioVista(Vista vista)
    {
        this.vista = vista;

        vista.getModelo().registrarObsevador(this);

        this.botonCementerioJugador = this.inicializarBoton();
        this.botonCementerioOponente = this.inicializarBoton();
        
        this.stackJugador = new StackPane();
        this.stackOponente = new StackPane();
    }

    private Button inicializarBoton()
    {

        Button boton = new Button();

        boton.setPrefSize((this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta), (this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta));
        boton.setStyle(estiloRegion);

        return boton;
    }

    public StackPane getCementerioJugador()
    {

        return stackJugador;
    }

    public StackPane getCementerioOponente()
    {

        return stackOponente;
    }

    // --------------------------------------------------------------------
    // Métodos de observador de modelo.
    // --------------------------------------------------------------------
    @Override
    public void seTomoCartaDeMazo()
    {

    }

    @Override
    public void ingresoCartaAMano()
    {

    }

    @Override
    public void egresoCartaAMano()
    {

    }

    @Override
    public void ingresoCartaARegion()
    {
        //TODO: cuando hay al menos 1 carta en el cementerio, se debería mostrar 'rutaImagenReversoCarta'.
    	
    	Rectangle mazo1 = new Rectangle((this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta), 
    			(this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta));
    	Rectangle mazo2 = new Rectangle((this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta), 
    			(this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta));
    	
    	Image img1 = new Image(rutaImagenReversoCarta);
    	mazo1.setFill(new ImagePattern(img1));
    	Image img2 = new Image(rutaImagenReversoCarta);
    	mazo2.setFill(new ImagePattern(img2));
    	
    	Label label1 = new Label();
    	Label label2 = new Label();
    	
    	label1.setAlignment(Pos.CENTER);
    	label2.setAlignment(Pos.CENTER);
    	
    	label1.setStyle("-fx-text-fill: white");
    	label2.setStyle("-fx-text-fill: white");
    	
    	label1.setText(Integer.toString(this.vista.getModelo().getJugador().getCantidadDeCartasEnMano()));
    	label2.setText(Integer.toString(this.vista.getModelo().getOponente().getCantidadDeCartasEnMano()));
    	    	
    	this.stackJugador.setAlignment(Pos.CENTER_RIGHT);
    	this.stackOponente.setAlignment(Pos.CENTER_LEFT);
    	
    	this.stackJugador.getChildren().addAll(mazo1,label1);
    	this.stackOponente.getChildren().addAll(mazo2,label2);
    	
        this.vista.actualizarDibujo();
    }

    @Override
    public void egresoCartaARegion()
    {

    }

    @Override
    public void cambiaronLosPuntosDeVida()
    {

    }

    @Override
    public void cartaCambioDeOrientacion()
    {

    }

    @Override
    public void cartaCambioDeModo()
    {

    }
}
