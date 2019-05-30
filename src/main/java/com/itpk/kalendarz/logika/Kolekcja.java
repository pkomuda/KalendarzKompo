package com.itpk.kalendarz.logika;

import java.util.List;

public interface Kolekcja<T>
{
    List<T> getLista();
    void setLista(List<T> t);
    boolean dodaj(T t);
    boolean usun(T t);
    String toString();
}