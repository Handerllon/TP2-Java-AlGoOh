package Modelo.carta;

import Modelo.Jugador;
import Modelo.carta.campo.CartaCampo;
import Modelo.carta.campo.FabricaCartasCampo;
import Modelo.carta.magica.CartaMagica;
import Modelo.carta.magica.FabricaCartasMagicas;
import Modelo.carta.monstruo.CartaMonstruo;
import Modelo.carta.monstruo.FabricaCartasMonstruo;
import Modelo.carta.trampa.CartaTrampa;
import Modelo.carta.trampa.FabricaCartasTrampa;

import java.util.ArrayList;

public class FabricaCartas
{
    private FabricaCartasCampo fabricaCartasCampo;
    private FabricaCartasMagicas fabricaCartasMagicas;
    private FabricaCartasMonstruo fabricaCartasMonstruo;
    private FabricaCartasTrampa fabricaCartasTrampa;

    public FabricaCartas(Jugador jugador, Jugador oponente)
    {
        this.fabricaCartasCampo = new FabricaCartasCampo(jugador, oponente);
        this.fabricaCartasMagicas = new FabricaCartasMagicas(jugador, oponente);
        this.fabricaCartasMonstruo = new FabricaCartasMonstruo(jugador, oponente);
        this.fabricaCartasTrampa = new FabricaCartasTrampa(jugador, oponente);
    }

    public CartaCampo crearCartaCampo(String nombre)
    {
        return this.fabricaCartasCampo.getCarta(nombre);
    }

    public CartaMagica crearCartaMagica(String nombre)
    {
        return this.fabricaCartasMagicas.getCarta(nombre);
    }

    public CartaMonstruo crearCartaMonstruo(String nombre)
    {
        return this.fabricaCartasMonstruo.getCarta(nombre);
    }

    public CartaTrampa crearCartaTrampa(String nombre)
    {
        return this.fabricaCartasTrampa.getCarta(nombre);
    }

    public ArrayList<String> getNombresCartasCampo()
    {
        return this.fabricaCartasCampo.getNombres();
    }

    public ArrayList<String> getNombresCartasMagicas()
    {
        return this.fabricaCartasMagicas.getNombres();
    }

    public ArrayList<String> getNombresCartasTrampa()
    {
        return this.fabricaCartasTrampa.getNombres();
    }

    public ArrayList<String> getNombresCartasMonstruosNormales()
    {
        return this.fabricaCartasMonstruo.getNombresMonstruosNormales();
    }

    public ArrayList<String> getNombresCartasMonstruosNoNormales()
    {
        return this.fabricaCartasMonstruo.getNombresMonstruosNoNormales();
    }
}