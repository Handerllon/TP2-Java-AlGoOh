package carta;

public abstract class Orientacion
{
    public void cambiarOrientacion(Carta carta){
        carta.establecerOrientacion(new OrientacionArriba());
    }
}
