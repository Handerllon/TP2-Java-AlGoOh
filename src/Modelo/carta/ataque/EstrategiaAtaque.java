package Modelo.carta.ataque;

import Modelo.carta.monstruo.CartaMonstruo;

public interface EstrategiaAtaque
{
    void ejecutar(CartaMonstruo atacante, CartaMonstruo atacada);

    void ejecutar(CartaMonstruo atacante);
}
