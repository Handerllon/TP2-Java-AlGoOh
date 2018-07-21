package Controlador.estadosJuego;

public final class FaseInicial implements Fase
{
    private static final String nombreFase = "Inicial";
    private static FaseInicial instancia = null;
    private final MaquinaTurnos maquinaTurnos;

    // --------------------------------------------------------------------
    // Métodos de construcción e inicialización.
    // --------------------------------------------------------------------
    private FaseInicial(MaquinaTurnos maquinaTurnos)
    {
        this.maquinaTurnos = maquinaTurnos;
    }

    public static FaseInicial obtenerInstancia(MaquinaTurnos maquinaTurnos)
    {
        if (instancia == null)
        {
            instancia = new FaseInicial(maquinaTurnos);
        }
        return instancia;
    }

    public FaseInicial clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException();
    }

    // --------------------------------------------------------------------
    // Métodos de fase.
    // --------------------------------------------------------------------
    @Override
    public Fase avanzar()
    {
        return FasePreparacion.obtenerInstancia(this.maquinaTurnos);
    }

    @Override
    public String nombre()
    {
        return nombreFase;
    }

    @Override
    public boolean enFaseInicial()
    {
        return true;
    }

    @Override
    public boolean esFasePreparacion()
    {
        return false;
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
