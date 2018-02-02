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
        int[][] result = calculateMatrixProduct_DAC(matrices, 0, 0, 0, 0, matrices.array1.length);
        return result;
    }

    private static int[][] calculateMatrixProduct_DAC(Matricies matricies, int rows1Start, int cols1Start, int rows2Start, int cols2Start, int sizeToProcess) throws IllegalArgumentException{
        //int[][] result = new int[sizeToProcess][sizeToProcess];
        if(sizeToProcess == 1){
            int[][] result = new int[1][1];
            result[0][0] = matricies.array1[rows1Start][cols1Start] * matricies.array2[rows2Start][cols2Start];
            return result;
        }
        int[][] a11b11 = calculateMatrixProduct_DAC(matricies, rows1Start, cols1Start, rows2Start, cols2Start, sizeToProcess/2);
        int[][] a12b21 = calculateMatrixProduct_DAC(matricies, rows1Start, cols1Start + sizeToProcess / 2, rows2Start + sizeToProcess / 2, cols2Start, sizeToProcess / 2);
        int[][] result11 = addMatrices(a11b11, a12b21);
        int[][] a11b12 = calculateMatrixProduct_DAC(matricies, rows1Start, cols1Start, rows2Start, cols2Start + sizeToProcess / 2, sizeToProcess/2);
        int[][] a12b22 = calculateMatrixProduct_DAC(matricies, rows1Start, cols1Start + sizeToProcess / 2, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] result12 = addMatrices(a11b12, a12b22);
        int[][] a21b11 = calculateMatrixProduct_DAC(matricies, rows1Start + sizeToProcess / 2, cols1Start, rows2Start, cols2Start, sizeToProcess/2);
        int[][] a22b21 = calculateMatrixProduct_DAC(matricies, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, rows2Start + sizeToProcess / 2, cols2Start, sizeToProcess / 2);
        int[][] result21 = addMatrices(a21b11, a22b21);
        int[][] a21b12 = calculateMatrixProduct_DAC(matricies, rows1Start + sizeToProcess / 2, cols1Start, rows2Start, cols2Start + sizeToProcess / 2, sizeToProcess/2);
        int[][] a22b22 = calculateMatrixProduct_DAC(matricies, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] result22 = addMatrices(a21b12, a22b22);
        return combineMatricies(result11, result12, result21, result22);
    }

    public static int[][] matrixProduct_Strassen(int[][] A, int[][] B) throws IllegalArgumentException {
        Matricies matrices = new Matricies(A, B);
        if (!checkValidity(matrices)) {
            throw new IllegalArgumentException("Matrices must be squares, identical sizes, and have rows and columns that are a power of 2");
        }
        return null;
    }

    private static int[][] calculateMatrixProduct_Strassen(Matricies matricies, int rows1Start, int cols1Start, int rows2Start, int cols2Start, int sizeToProcess) throws IllegalArgumentException{
        if(sizeToProcess == 1){
            int[][] result = new int[1][1];
            result[0][0] = matricies.array1[rows1Start][cols1Start] * matricies.array2[rows2Start][cols2Start];
            return result;
        }
        // Compute operation matrices
        int[][] s1 = subtractdMatricesPartial(matricies.array2, matricies.array2, rows2Start, cols2Start + sizeToProcess / 2, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s2 = addMatricesPartial(matricies.array1, matricies.array1, rows1Start, cols1Start, rows1Start, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s3 = addMatricesPartial(matricies.array1, matricies.array1, rows1Start + sizeToProcess / 2, cols1Start, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s4 = subtractdMatricesPartial(matricies.array2, matricies.array2, rows2Start + sizeToProcess / 2, cols2Start, rows2Start, cols2Start, sizeToProcess / 2);
        int[][] s5 = addMatricesPartial(matricies.array1, matricies.array1, rows1Start, cols1Start, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s6 = addMatricesPartial(matricies.array2, matricies.array2, rows2Start, cols2Start, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s7 = subtractdMatricesPartial(matricies.array1, matricies.array1, rows1Start, cols1Start + sizeToProcess / 2, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s8 = addMatricesPartial(matricies.array2, matricies.array2, rows2Start + sizeToProcess / 2, cols2Start, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s9 = subtractdMatricesPartial(matricies.array1, matricies.array1, rows1Start, cols1Start, rows1Start + sizeToProcess / 2, cols1Start, sizeToProcess / 2);
        int[][] s10 = addMatricesPartial(matricies.array2, matricies.array2, rows2Start, cols2Start, rows2Start, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        // Compute incomplete matrices

        // Compute partial matrices
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

    private static int[][] addMatrices(int[][] matrix1, int[][] matrix2) throws IllegalArgumentException {
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            throw new IllegalArgumentException("Matrices of different dimensions cannot be directly added");
        }
        int[][] result = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++){
            for (int j = 0; j < matrix1[0].length; j++){
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return result;
    }

    private static int[][] addMatricesPartial(int[][] matrix1, int[][] matrix2, int rows1Start, int cols1Start, int rows2Start, int cols2Start, int sizeToProcess) throws IllegalArgumentException {
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            throw new IllegalArgumentException("Matrices of different dimensions cannot be directly added");
        }
        int[][] result = new int[sizeToProcess][sizeToProcess];
        for (int i = rows1Start; i < rows1Start + sizeToProcess; i++){
            for (int j = cols1Start; j < cols1Start + sizeToProcess; j++){
                int matrix2i = i + rows2Start - rows1Start;
                int matrix2j = j + cols2Start - cols1Start;
                result[i - rows1Start][j - cols1Start] = matrix1[i][j] + matrix2[matrix2i][matrix2j];
            }
        }
        return result;
    }

    private static int[][] subtractMatrices(int[][] matrix1, int[][] matrix2) throws IllegalArgumentException {
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            throw new IllegalArgumentException("Matrices of different dimensions cannot be directly subtracted");
        }
        int[][] result = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++){
            for (int j = 0; j < matrix1[0].length; j++){
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return result;
    }

    private static int[][]subtractdMatricesPartial(int[][] matrix1, int[][] matrix2, int rows1Start, int cols1Start, int rows2Start, int cols2Start, int sizeToProcess) throws IllegalArgumentException {
        if(matrix1.length != matrix2.length || matrix1[0].length != matrix2[0].length){
            throw new IllegalArgumentException("Matrices of different dimensions cannot be directly added");
        }
        int[][] result = new int[sizeToProcess][sizeToProcess];
        for (int i = rows1Start; i < rows1Start + sizeToProcess; i++){
            for (int j = cols1Start; j < cols1Start + sizeToProcess; j++){
                int matrix2i = i + rows2Start - rows1Start;
                int matrix2j = j + cols2Start - cols1Start;
                result[i - rows1Start][j - cols1Start] = matrix1[i][j] - matrix2[matrix2i][matrix2j];
            }
        }
        return result;
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
