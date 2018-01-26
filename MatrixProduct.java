/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 2
 **  1-26-2018
 */

import java.lang.Math;

public class MatrixProduct {
    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) throws IllegalArgumentException{
        Matricies matrices = new Matricies(A, B);
        if(!checkValidity(matrices)){
            throw new IllegalArgumentException("Matrices must be squares, identical sizes, and have rows and columns that are a power of 2");
        }
    }

    private static int[][] calculateMatrixProduct_DAC(Matricies matricies, int rows1Start, int cols1Start, int rows2Start, int cols2Start, int sizeToProcess){
        int[][] result = new int[sizeToProcess][sizeToProcess];
        if(sizeToProcess == 1){
            result[0][0] = matricies.array1[rows1Start][cols1Start] * matricies.array2[rows2Start][cols2Start];
            return result;
        }
        // TODO
        return result;
    }

    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) throws IllegalArgumentException {
        Matricies matrices = new Matricies(A, B);
        if (!checkValidity(matrices)) {
            throw new IllegalArgumentException("Matrices must be squares, identical sizes, and have rows and columns that are a power of 2");
        }
    }

    private static boolean checkValidity(Matricies matricies){
        if(matricies.rows1() != matricies.cols1() || matricies.rows1() != matricies.cols2() || matricies.rows2() != matricies.rows2()){
            return false;
        }
        if((Math.log10(matricies.cols1())/Math.log10(2))%1 != 0){
            return false;
        }
        return true;
    }
}
