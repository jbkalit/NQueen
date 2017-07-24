package com.bokvlt.nqueen.nQueenGenetic8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by BOKVLT on 11/16/2016.
 */

public class nQueenGenetic8 {

    // max collisions can be 28
    public static final int MAX_FIT = 28;
    private Random random;
    int banyakSolusis;



    public int getBanyakSolusis() {

        return banyakSolusis;
    }

    public nQueenGenetic8()  {
        random = new Random(new Random().nextInt(Integer.MAX_VALUE));
    }

    public final void kawin(refObjek<ArrayList<Chromosome>> initPopulasi,
                               int generations, double probCrossver, double probMutation) {
        int totalSample = 0;
        refObjek<Integer> tempRef_totalFitness = new refObjek<>(totalSample);
        hitungSample(initPopulasi, tempRef_totalFitness);
        totalSample = tempRef_totalFitness.value;
        for (int generation = 0; generation < generations; generation++) {
            PrepareRuletteWheel(initPopulasi, totalSample);
            crossover(initPopulasi, probCrossver);
            mutasi(initPopulasi, probMutation);
            refObjek<Integer> tempRef_totalFitness2 = new refObjek<>(totalSample);
            hitungSample(initPopulasi, tempRef_totalFitness2);
            totalSample = tempRef_totalFitness2.value;
            if (initPopulasi.value.get(initPopulasi.value.size() - 1).sample == 28) {
                break;
            }
        }
        Collections.sort(initPopulasi.value, new SampleBanding());
    }

    public final void crossover (refObjek<ArrayList<Chromosome>> parents, double probability) {
        ArrayList<Chromosome> offspring = new ArrayList<>();
        for (int i = 0; i < parents.value.size(); i++) {
            if (test(probability)) //if the chance is to crossover
            {
                Chromosome parentX = testAcak(parents.value).clone();
                Chromosome parentY = testAcak(parents.value).clone();
                ArrayList<Integer> child = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    if (test(0.5)) //select from parentX
                    {
                        for (int k = 0; k < parentX.gens.length; k++) {
                            //instead of deleting the similar genes from parents select the next non-contained number
                            if (!child.contains(parentX.gens[k])) {
                                child.add(parentX.gens[k]);
                                break;
                            }
                        }
                    } else //select from parentY
                    {
                        for (int k = 0; k < parentY.gens.length; k++) {
                            //instead of deleting the similar genes from parents select the next non-contained number
                            if (!child.contains(parentY.gens[k])) {
                                child.add(parentY.gens[k]);
                                break;
                            }
                        }
                    }
                }
                Chromosome offSpr = new Chromosome();
                offSpr.gens = new int[child.size()];
                for (int m = 0; m < child.size(); m++) {
                    offSpr.gens[m] = child.get(m);
                }
                offspring.add(offSpr.clone());
            } else //else the chance is to clonning
            {
                Chromosome parentX = testAcak(parents.value).clone();
                offspring.add(parentX.clone());
            }
        }
        while (offspring.size() > parents.value.size()) {
            offspring.remove((int) getRandom(0, offspring.size() - 1));
        }
        parents.value = offspring;
    }

    private void PrepareRuletteWheel(refObjek<ArrayList<Chromosome>> parents, int total) {
        int currentTotalFitness = 0;
        for (int i = 0; i < parents.value.size(); i++) {
            currentTotalFitness += parents.value.get(i).sample;
            Chromosome temp = parents.value.get(i).clone();
            temp.avgSample = currentTotalFitness / (double) total;
            parents.value.set(i, temp.clone());
        }
    }

    private Chromosome testAcak(ArrayList<Chromosome> parents) {
        Chromosome selection = parents.get(0).clone();
        double probability = random.nextDouble();
        for (int i = 0; i < parents.size(); i++) {
            selection = parents.get(i).clone();
            if (parents.get(i).avgSample > probability) {
                break;
            }
        }
        return selection;
    }

    public final void mutasi(refObjek<ArrayList<Chromosome>> parents, double probability) {
        ArrayList<Chromosome> offsprings = new ArrayList<>();

        for (int i = 0; i < parents.value.size(); i++) {
            Chromosome offspring = parents.value.get(i).clone();
            for (int mutatePosition = 0; mutatePosition < 8; mutatePosition++) {
                if (test(probability)) //if the chance is to mutate
                {
                    int newGeneIndex = (int) (getRandom(0, 6) + 0.5);
                    if (newGeneIndex >= mutatePosition) {
                        newGeneIndex += 1;
                    }
                    int swapTemp = offspring.gens[mutatePosition];
                    offspring.gens[mutatePosition] = offspring.gens[newGeneIndex];
                    offspring.gens[newGeneIndex] = swapTemp;
                }
            }
            offsprings.add(offspring.clone());
        }
        parents.value = offsprings;
    }

    public final double getRandom(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }

    private boolean test(double probability) {
        return random.nextDouble() < probability;
    }

    public final void hitungSample(refObjek<ArrayList<Chromosome>> chromosome, refObjek<Integer> totalFitness) {
        int collisions = 0;
        totalFitness.value = 0;
        for (int k = 0; k < chromosome.value.size(); k++) {
            for (int i = 0; i < chromosome.value.get(k).gens.length - 1; i++) {
                int y = chromosome.value.get(k).gens[i];
                for (int j = i + 1; j < chromosome.value.get(k).gens.length; j++) {
                    if (Math.abs(j - i) == Math.abs(chromosome.value.get(k).gens[j] - y)) {
                        collisions++;
                    }
                }
            }
            Chromosome temp = chromosome.value.get(k).clone();
            temp.sample = MAX_FIT - collisions;
            chromosome.value.set(k, temp.clone());
            totalFitness.value += chromosome.value.get(k).sample;
            collisions = 0;
        }
    }

}
