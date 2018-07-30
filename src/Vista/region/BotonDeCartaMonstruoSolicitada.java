package Vista.region;

import Modelo.carta.monstruo.CartaMonstruo;
import Vista.Vista;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Popup;

import java.net.URL;

public class BotonDeCartaMonstruoSolicitada
{
    private static double porcentajeDeAnchoDeLaCarta = 0.0496;
    private static double porcentajeDeAltoDeLaCarta = 0.1287;
    private static String backDeCartaLocacion = "resources/imagenes/cartaReverso.jpg";
    private Button botonDeCarta;
    private Vista vista;
    private CartaMonstruo cartaAtacante;
    private CartaMonstruo cartaSolicitada;
    private Popup popup;
    private AudioClip audioClipCardAttack;
    private double cardAttackVolume = 0.3;

    public BotonDeCartaMonstruoSolicitada(Vista vista, CartaMonstruo cartaAtacante, CartaMonstruo carta, Popup popup)
    {

        this.cartaAtacante = cartaAtacante;
        this.popup = popup;
        this.vista = vista;
        this.cartaSolicitada = carta;
        this.botonDeCarta = new Button();

        URL mediaUrl;
        mediaUrl = this.getClass().getClassLoader().getResource("resources/audio/card_attack.wav");
        this.audioClipCardAttack = new AudioClip(mediaUrl.toExternalForm());
        this.audioClipCardAttack.setVolume(cardAttackVolume);

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
        this.botonDeCarta.setPrefSize(this.vista.getResolucionHorizontal() * porcentajeDeAnchoDeLaCarta,
                this.vista.getResolucionVertical() * porcentajeDeAltoDeLaCarta);

        this.botonDeCarta.setOnAction(e -> seleccionDeCarataBtn_Click());
    }

    private void seleccionDeCarataBtn_Click()
    {

        this.vista.getControlador().atacarCarta(this.cartaAtacante.getPropietario(), this.cartaAtacante, this.cartaSolicitada);
        this.audioClipCardAttack.play();
        this.popup.hide();
    }

    public Button getBoton()
    {

        return this.botonDeCarta;
    }
}
