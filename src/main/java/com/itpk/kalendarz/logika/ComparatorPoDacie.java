package com.itpk.kalendarz.logika;

import java.util.Comparator;

/**
 * Klasa implementujaca Comparator do porownywania wydarzen
 */
public class ComparatorPoDacie implements Comparator<Wydarzenie>
{
    /**
     * Porownanie wydarzen po ich roku, miesiacu i dniu
     * @param o1 Wydarzenie 1
     * @param o2 Wydarzenie 2
     * @return 1: gdy wydarzenie 1 jest nowsze
     *        -1: gdy wydarzenie 1 jest starsze
     *         0: gdy maja taka sama date
     */
    @Override
    public int compare(Wydarzenie o1, Wydarzenie o2)
    {
        return o1.getData().compareTo(o2.getData());
    }
}