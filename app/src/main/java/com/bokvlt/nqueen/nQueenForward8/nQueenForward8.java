package com.bokvlt.nqueen.nQueenForward8;

import java.util.LinkedHashMap;

/**
 * Created by BOKVLT on 11/21/2016.
 */

public class nQueenForward8 {


    int[] col;
    int[][] solusis;
    int banyakSolusis;



    public int getBanyakSolusis() {

        return banyakSolusis;
    }

    public int[][] getSolusis() {
        return solusis; 
    }

    public nQueenForward8(int n){
        col = new int[n+1];
        solusis = new int[100][n];
        banyakSolusis = 0;
        solveNQueens(col,n,0);
    }

    public boolean solveNQueens(int[] X, int N, int i){
        if(i==N){
            System.arraycopy(X, 0, solusis[banyakSolusis], 0, N);
            banyakSolusis++;
            return true;
        }else {
            for (int j = 0; j < N; j++) {
                X[i] = j;
                boolean flag = checking(X, i);
                if (!flag) continue;
                solveNQueens(X, N, i+1);
            }
        }
        return false;
    }

    public static boolean checking(int[] A, int r){
        for (int i = 0; i <= r-1; i++) {
            if(A[i]==A[r] || A[r]-(r-i)==A[i] || A[r]+(r-i)==A[i])
                return false;
        }
        return true;
    }

}
