package carta.monstruo;

import AlGoOh.Jugador;
import carta.CartaMonstruo;
import carta.Sacrificio;
import carta.excepciones.NoHayTresDragonesBlancosParaSacrificioError;

public class BlueEyesUltimateDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 3800;
    private static int PUNTOS_ATAQUE = 4500;

    public BlueEyesUltimateDragon(Jugador jugador, Jugador oponente,String locacionDeImagen)
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, locacionDeImagen);
        this.estrellas = 12;
        this.nombre = "Blue-Eyes Ultimate Dragon";
    }

    public void invocar(Sacrificio sacrificio)
    {
        int cantidadDragonesBlancosNecesarios = 3;

        // Se crea una carta Dragón Blanco de Ojos Azules para obtener su nombre, y no hardcodearlo en el código, ya
        // que si este nombre cambia, habría que cambiar todos los lugares donde se hubiera hardcodeado.
        CartaMonstruo cartaBlueEyesWhiteDragonMock = new BlueEyesWhiteDragon(this.jugador, this.oponente,"resources/imagenes/monstruo/BlueEyesWhiteDragon.png");

        if (sacrificio.cantidadSacrificiosDe(cartaBlueEyesWhiteDragonMock.obtenerNombre()) < cantidadDragonesBlancosNecesarios)
        {
            throw new NoHayTresDragonesBlancosParaSacrificioError();
        } else
        {
            CartaMonstruo cartaASacrificar;

            for (int i = 0; i < cantidadDragonesBlancosNecesarios; i++)
            {
                cartaASacrificar = sacrificio.getMonstruo(cartaBlueEyesWhiteDragonMock.obtenerNombre());
                this.jugador.destruirMonstruo(cartaASacrificar);
            }
        }

        this.jugador.jugarCarta(this);
    }
}
