package com.bokvlt.nqueen.nQueenBacktracking8;

/**
 * Created by BOKVLT on 11/15/2016.
 */

public class nQueenBacktracking8 {

    int[] col;
    int[][] solusis;
    int banyakSolusis;


    public nQueenBacktracking8(int n) {
        col = new int[n+1];
        solusis = new int[100][n];
        solveNQueens(n,0);
    }

    public int getBanyakSolusis() {
        return banyakSolusis;
    }

    public int[][] getSolusis() {
        return solusis;
    }



    void solveNQueens(int n, int i){
        int j;
        if (safe(i)) {
            if (i == n) {
                System.arraycopy(col, 1, solusis[banyakSolusis], 0, n);
                banyakSolusis++;
            } else {
                for (j = 1; j <= n; j++) {
                    col[i + 1] = j;
                    solveNQueens(n, i + 1);
                }
            }
        }
    }


    boolean safe(int i){
        int k;
        boolean tukar;
        k = 1;
        tukar = true;
        while (k < i && tukar) {
            if (col[i] == col[k] || Math.abs(col[i] - col[k]) == i - k)
                tukar = false;
            k++;
        }
        return tukar;

    }
}

