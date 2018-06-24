package carta;

public class ModoAtaque implements Modo {
    public void cambiarModo(CartaMonstruo carta) {
        carta.establecerModo(new ModoDefensa());
    }
}
