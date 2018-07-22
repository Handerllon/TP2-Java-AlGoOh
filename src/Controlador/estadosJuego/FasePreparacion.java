package Controlador.estadosJuego;

public final class FasePreparacion implements Fase
{
    private static final String nombreFase = "Preparación";
    private static FasePreparacion instancia = null;
    private final MaquinaTurnos maquinaTurnos;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private FasePreparacion(MaquinaTurnos maquinaTurnos)
    {
        this.maquinaTurnos = maquinaTurnos;
    }

    public static FasePreparacion obtenerInstancia(MaquinaTurnos maquinaTurnos)
    {
        if (instancia == null)
        {
            instancia = new FasePreparacion(maquinaTurnos);
        }
        return instancia;
    }

    public FasePreparacion clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // --------------------------------------------------------------------
    // Métodos de fase.
    // --------------------------------------------------------------------
    @Override
    public Fase avanzar()
    {
        return FaseAtaque.obtenerInstancia(this.maquinaTurnos);
    }

    @Override
    public String nombre()
    {
        return nombreFase;
    }

    @Override
    public boolean esFaseInicial()
    {
        return false;
    }

    @Override
    public boolean esFasePreparacion()
    {
        return true;
    }

    @Override
    public boolean esFaseAtaque()
    {
        return false;
    }

    @Override
    public boolean esFaseTrampa()
    {
        return false;
    }

    @Override
    public boolean esFaseFinal()
    {
        return false;
    }

//    public void finalizarFase()
//    {
//        this.controlador.actualizarEstado();
//    }
}
