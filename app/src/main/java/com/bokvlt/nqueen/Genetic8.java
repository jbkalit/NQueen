package com.bokvlt.nqueen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bokvlt.nqueen.nQueenGenetic8.Chromosome;
import com.bokvlt.nqueen.nQueenGenetic8.nQueenGenetic8;
import com.bokvlt.nqueen.nQueenGenetic8.refObjek;

import java.util.ArrayList;
import java.util.Arrays;

public class Genetic8 extends AppCompatActivity {

    nQueenGenetic8 solver;
    ImageView[] queens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genetic8);
        initialCellsID();

        TextView txt = (TextView) findViewById(R.id.txtState);

        assert txt != null;

        //txt.setText(solver.getBanyakSolusis() + " state");

        Button btn = (Button) findViewById(R.id.btnShow);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                solver = new nQueenGenetic8();
                // setting the genetic algorithm parameters
                int population = 10;
                int generations = 100;
                double crossoverProbability = 0.70;
                double mutationProbability = 0.01;

                ArrayList<Chromosome> initPopulasi = getInitialPopulation(population);
                refObjek<ArrayList<Chromosome>> tempRef_initPopulation = new refObjek<>(initPopulasi);
                solver.kawin(tempRef_initPopulation, generations, crossoverProbability, mutationProbability);
                initPopulasi = tempRef_initPopulation.value;
                while (initPopulasi.get(0).sample != nQueenGenetic8.MAX_FIT) {
                    solver.kawin(tempRef_initPopulation, generations, crossoverProbability, mutationProbability);
                    initPopulasi = tempRef_initPopulation.value;
                }
                showRandomAnswer(initPopulasi.get(0));

            }
        });
    }

    private ArrayList<Chromosome> getInitialPopulation(int population) {
        ArrayList<Chromosome> initPop = new ArrayList<>();
        nQueenGenetic8 RandomGen = new nQueenGenetic8();
        for (int i = 0; i < population; i++) {
            ArrayList<Integer> genes = new ArrayList<>(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5, 6, 7}));
            Chromosome chromosome = new Chromosome();
            chromosome.gens = new int[8];
            for (int j = 0; j < 8; j++) {
                int geneIndex = (int) (RandomGen.getRandom(0, genes.size() - 1) + 0.5);
                chromosome.gens[j] = genes.get(geneIndex);
                genes.remove(geneIndex);
            }
            initPop.add(chromosome.clone());
        }
        return initPop;
    }

    void showRandomAnswer(Chromosome chrome) {
        ViewGroup.MarginLayoutParams marginParams;
        for (int i = 0; i < 8; i++) {
            if (queens[i] != null) {
                marginParams = (ViewGroup.MarginLayoutParams) queens[i].getLayoutParams();
                DisplayMetrics dm = queens[i].getResources().getDisplayMetrics();
                marginParams.setMargins(convertDpToPx(32 * i, dm), convertDpToPx(32 * chrome.gens[i], dm), 0, 0);
                queens[i].setLayoutParams(marginParams);
            }
        }
    }

    void initialCellsID() {
        queens = new ImageView[8];
        queens[0] = (ImageView) findViewById(R.id.queen0);
        queens[1] = (ImageView) findViewById(R.id.queen1);
        queens[2] = (ImageView) findViewById(R.id.queen2);
        queens[3] = (ImageView) findViewById(R.id.queen3);
        queens[4] = (ImageView) findViewById(R.id.queen4);
        queens[5] = (ImageView) findViewById(R.id.queen5);
        queens[6] = (ImageView) findViewById(R.id.queen6);
        queens[7] = (ImageView) findViewById(R.id.queen7);
    }

    private int convertDpToPx(int dp, DisplayMetrics displayMetrics) {
        float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pixels);
    }
}
