package com.itpk.kalendarz.logika;

import java.util.Comparator;

public class ComparatorPoDacie implements Comparator<Wydarzenie>
{
    @Override
    public int compare(Wydarzenie o1, Wydarzenie o2)
    {
        return o1.getData().compareTo(o2.getData());
    }
}