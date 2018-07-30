package Vista.region;

import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;
import javafx.stage.Popup;

public class BotonDeCartaMonstruoSolicitada {

	private Button botonDeCarta;
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private static String backDeCartaLocacion = "resources/imagenes/cartaReverso.jpg";
	private Vista vista;
	private CartaMonstruo cartaAtacante;
	private CartaMonstruo cartaSolicitada;
	private Popup popup;
	
	
	public BotonDeCartaMonstruoSolicitada(Vista vista, CartaMonstruo cartaAtacante, CartaMonstruo carta, Popup popup) {
		
		this.cartaAtacante = cartaAtacante;
		this.popup = popup;
		this.vista = vista;
		this.cartaSolicitada = carta;
		this.botonDeCarta = new Button();
		
		//---------Imagen y tooltip del boton
		if (this.cartaSolicitada.enDefensa() && this.cartaSolicitada.estaBocaAbajo())
        {
            botonDeCarta.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(backDeCartaLocacion).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        } else if (this.cartaSolicitada.enDefensa())
        {
            botonDeCarta.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(this.cartaSolicitada.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        } else
        {
        	botonDeCarta.setBackground(new Background(new BackgroundFill(new ImagePattern(new Image(getClass()
                    .getClassLoader().getResource(this.cartaSolicitada.getLocacionDeImagen()).toString())), CornerRadii.EMPTY, Insets.EMPTY)));
        }
		
		//----------- Tamanio del boton
		this.botonDeCarta.setPrefSize(this.vista.getResolucionHorizontal()*porcentajeDeAnchoDeLaCarta, 
				this.vista.getResolucionVertical()*porcentajeDeAltoDeLaCarta);
		
		this.botonDeCarta.setOnAction(e -> seleccionDeCarataBtn_Click());
	}

	
	private void seleccionDeCarataBtn_Click() {
		
		this.vista.getControlador().atacarCarta(this.cartaAtacante.getPropietario(), this.cartaAtacante, this.cartaSolicitada);
		
		this.popup.hide();
		
	}


	public Button getBoton(){
		
		return this.botonDeCarta;
	}


}
