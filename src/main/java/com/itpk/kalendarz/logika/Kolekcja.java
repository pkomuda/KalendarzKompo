package com.itpk.kalendarz.logika;

import java.util.List;

/**
 * Interfejs z lista
 * @param <T> U nas moze byc to lista wydarzen lub dni
 */
public interface Kolekcja<T>
{
    List<T> getLista();
    void setLista(List<T> t);
    boolean dodaj(T t);
    boolean usun(T t);
    String toString();
}