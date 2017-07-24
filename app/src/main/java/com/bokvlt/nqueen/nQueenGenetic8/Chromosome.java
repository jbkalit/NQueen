package com.bokvlt.nqueen.nQueenGenetic8;

/**
 * Created by BOKVLT on 11/16/2016.
 */

public class Chromosome {
    public int[] gens;
    public int sample;
    public double avgSample;


    public Chromosome clone(){
        Chromosome c = new Chromosome();
        c.gens = this.gens;
        c.sample = this.sample;
        c.avgSample = this.sample;
        return c;
    }

}
