package Modelo.carta.monstruo;

import Modelo.Jugador;
import Modelo.carta.excepciones.SacrificiosInsuficientesError;

import java.util.ArrayList;

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

    @Override
    protected ArrayList<CartaMonstruo> getSacrificios()
    {
        // Se crea un Dragón Blanco de Ojos Azules para obtener su nombre, y no hardcodearlo en el código, ya
        // que si este nombre cambia, habría que cambiar todos los lugares donde se hubiera hardcodeado.
        CartaMonstruo cartaBlueEyesWhiteDragonMock = new BlueEyesWhiteDragon(null, null);

        // Se verifica si hay la cantidad de Blue-Eyes White Dragons requeridos.
        ArrayList<CartaMonstruo> cartasEnRegionMonstruo = this.getPropietario().getRegionMonstruos().getCartas();

        ArrayList<CartaMonstruo> cartasASacrificar = new ArrayList<>();

        CartaMonstruo cartaMonstruo;

        int cantidadSacrificiosContabilizados = 0;
        for (int k = 0; k < cartasEnRegionMonstruo.size(); k++)
        {

            cartaMonstruo = cartasEnRegionMonstruo.get(k);

            if (cartaMonstruo.getNombre() == cartaBlueEyesWhiteDragonMock.getNombre())
            {
                cantidadSacrificiosContabilizados++;
                cartasASacrificar.add(cartaMonstruo);
            }
        }

        if (cantidadSacrificiosContabilizados < getCantidadSacrificiosRequeridos())
        {
            throw new SacrificiosInsuficientesError();
        } else
        {
            return cartasASacrificar;
        }
    }

    @Override
    public boolean requiereSacrificios()
    {
        return true;
    }

    @Override
    public int getCantidadSacrificiosRequeridos()
    {
        // Este monstruo requiere 3 Blue-Eyes White Dragon.
        return 3;
    }

    @Override
    public boolean seCumplenCondicionesDeSacrificiosRequeridos()
    {
        CartaMonstruo cartaBlueEyesWhiteDragonMock = new BlueEyesWhiteDragon(null, null);
        ArrayList<CartaMonstruo> cartasEnRegionMonstruo = this.getPropietario().getRegionMonstruos().getCartas();

        int cantidadSacrificiosContabilizados = 0;
        for (int k = 0; k < cartasEnRegionMonstruo.size(); k++)
        {
            if (cartasEnRegionMonstruo.get(k).getNombre() == cartaBlueEyesWhiteDragonMock.getNombre())
            {
                cantidadSacrificiosContabilizados++;
            }
        }

        if (cantidadSacrificiosContabilizados < getCantidadSacrificiosRequeridos())
        {
            return false;
        }

        return true;
    }
}
