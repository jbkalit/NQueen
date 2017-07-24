package com.bokvlt.nqueen;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bokvlt.nqueen.nQueenBacktracking4.nQueenBacktracking4;

/**
 * Created by BOKVLT on 11/15/2016.
 */

public class Backtracking4 extends AppCompatActivity {

    nQueenBacktracking4 solverr;
    ImageView[] queenss;

    ViewGroup.MarginLayoutParams tampill;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backtracking4);

        solverr = new nQueenBacktracking4(4);

        inisialisasiKotak();

        Log.w("TAG", solverr.getBanyakSolusis()+"");

        TextView txt = (TextView) findViewById(R.id.txtState);

        txt.setText(solverr.getBanyakSolusis() + " state");

        Button btn = (Button) findViewById(R.id.btnShow);
        if ( btn != null){
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tampilkanJawaban();
                }
            });
        }
    }


    void inisialisasiKotak(){
        queenss = new ImageView[4];
        queenss[0] = (ImageView) findViewById(R.id.queen0);
        queenss[1] = (ImageView) findViewById(R.id.queen1);
        queenss[2] = (ImageView) findViewById(R.id.queen2);
        queenss[3] = (ImageView) findViewById(R.id.queen3);
    }


    void tampilkanJawaban(){
        int index = (int) (Math.random() * solverr.getBanyakSolusis());
        int[] pilihSolusi = solverr.getSolusis()[index];

        for (int i=0;i<4;i++){
            tampill = (ViewGroup.MarginLayoutParams) queenss[i].getLayoutParams();
            DisplayMetrics dm = queenss[i].getResources().getDisplayMetrics();

            tampill.setMargins(convertDpToPx(65 * i, dm),convertDpToPx(65 * (pilihSolusi[i]-1),dm),0,0);
            queenss[i].setLayoutParams(tampill);

        }
    }

    private int ukuranLayar(){
        int density= getResources().getDisplayMetrics().densityDpi;
        int size =0;
        switch(density) {
            case DisplayMetrics.DENSITY_LOW:
                size = 38;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                size = 35;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                size = 33;
                break;
        }

        return size;
    }

    private int convertDpToPx(int dp, DisplayMetrics displayMetrics){
        float pxs = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
        return Math.round(pxs);
    }


}
