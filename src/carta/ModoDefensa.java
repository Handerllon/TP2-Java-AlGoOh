package carta;

public class ModoDefensa implements Modo {
    public void cambiarModo(CartaMonstruo carta) {
        carta.establecerModo(new ModoAtaque());
    }
}
