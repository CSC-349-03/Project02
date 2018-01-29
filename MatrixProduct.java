/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 2
 **  1-26-2018
 */

import com.sun.javaws.exceptions.InvalidArgumentException;

import java.lang.Math;

public class MatrixProduct {
    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) throws IllegalArgumentException{
        Matricies matrices = new Matricies(A, B);
        if(!checkValidity(matrices)){
            throw new IllegalArgumentException("Matrices must be squares, identical sizes, and have rows and columns that are a power of 2");
        }
        int[][] result = calculateMatrixProduct_DAC(matrices, 0, 0, 0, 0, matrices.array1.length);
        return result;
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

    private static int[][] combineMatricies(int[][] matrix1, int[][] matrix2, int[][] matrix3, int[][] matrix4){ // Verified working
        int rows = matrix1.length + matrix3.length;
        int cols = matrix1[0].length + matrix2[0].length;
        int[][] result = new int[rows][cols];

        // Copy matrix1 into result matrix
        for(int i = 0; i < matrix1.length; i++){
            System.arraycopy(matrix1[i], 0, result[i], 0, matrix1.length);
            System.arraycopy(matrix3[i], 0, result[i + matrix1.length], 0, matrix3.length);
            System.arraycopy(matrix2[i], 0, result[i], matrix2.length, matrix2.length);
            System.arraycopy(matrix4[i], 0, result[i + matrix1.length], matrix4.length, matrix4.length);
        }
        return result;
    }

    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) throws InvalidArgumentException {
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            throw new InvalidArgumentException(new String[]{"Matrices of different dimensions cannot be directly added"});
        }
        int[][] result = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++){
            for (int j = 0; j < matrix1[0].length; j++){
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) throws IllegalArgumentException {
        Matricies matrices = new Matricies(A, B);
        if (!checkValidity(matrices)) {
            throw new IllegalArgumentException("Matrices must be squares, identical sizes, and have rows and columns that are a power of 2");
        }
        return null;
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
