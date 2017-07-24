package com.bokvlt.nqueen.nQueenGenetic8;

import java.util.Comparator;

/**
 * Created by BOKVLT on 11/16/2016.
 */

public class SampleBanding implements Comparator<Chromosome> {

    @Override
    public int compare(Chromosome x, Chromosome y) {

        if (x.sample == y.sample){
            return 0;
        } else if (x.sample < y.sample){
            return 1;
        } else {
            return -1;
        }
    }
}
