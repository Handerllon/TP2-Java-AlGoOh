package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.Sacrificio;
import Modelo.carta.excepciones.NoHayTresDragonesBlancosParaSacrificioError;

public class BlueEyesUltimateDragon extends CartaMonstruo
{
    private static int PUNTOS_DEFENSA = 3800;
    private static int PUNTOS_ATAQUE = 4500;
    private static String rutaImagen = "resources/imagenes/monstruo/BlueEyesUltimateDragon.png";

    public BlueEyesUltimateDragon(Jugador jugador, Jugador oponente)
    {

        super(PUNTOS_DEFENSA, PUNTOS_ATAQUE, jugador, oponente, rutaImagen);
        this.estrellas = 12;
        this.nombre = "Blue-Eyes Ultimate Dragon";
    }

    public void summon(Sacrificio sacrificio)
    {
        int cantidadDragonesBlancosNecesarios = 3;

        // TODO: ver esto.
        // Se crea una Modelo.carta Dragón Blanco de Ojos Azules para obtener su nombre, y no hardcodearlo en el código, ya
        // que si este nombre cambia, habría que cambiar todos los lugares donde se hubiera hardcodeado.
        CartaMonstruo cartaBlueEyesWhiteDragonMock = new BlueEyesWhiteDragon(null, null);

        if (sacrificio.cantidadSacrificiosDe(cartaBlueEyesWhiteDragonMock.getNombre()) < cantidadDragonesBlancosNecesarios)
        {
            throw new NoHayTresDragonesBlancosParaSacrificioError();
        } else
        {
            CartaMonstruo cartaASacrificar;

            for (int i = 0; i < cantidadDragonesBlancosNecesarios; i++)
            {
                cartaASacrificar = sacrificio.getMonstruo(cartaBlueEyesWhiteDragonMock.getNombre());
                this.jugador.destruirMonstruo(cartaASacrificar);
            }
        }

        this.jugador.enviarARegion(this);
    }

    @Override
    public boolean requiereSacrificio()
    {
        return true;
    }
}
