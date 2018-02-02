/*  David Twyman, Andrew LeDawson
 **  dtwyman@calpoly.edu, aledawson@calpoly.edu
 **  CSC 349-03
 **  Project 2
 **  2-2-2018
 */

import java.lang.Math;

public class MatrixProduct {
    public static int[][] matrixProduct_DAC(int[][] A, int[][] B) throws IllegalArgumentException{
        Matricies matrices = new Matricies(A, B);
        if(!checkValidity(matrices)){
            throw new IllegalArgumentException("Matrices must be squares, identical sizes, and have rows and columns that are a power of 2");
        }
        return calculateMatrixProduct_DAC(matrices, 0, 0, 0, 0, matrices.array1.length);
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
        return calculateMatrixProduct_Strassen(matrices.array1, matrices.array2, 0, 0, 0, 0, matrices.array1.length);
    }

    private static int[][] calculateMatrixProduct_Strassen(int[][] array1, int[][] array2, int rows1Start, int cols1Start, int rows2Start, int cols2Start, int sizeToProcess) throws IllegalArgumentException{
        if(sizeToProcess == 1){
            int[][] result = new int[1][1];
            result[0][0] = array1[rows1Start][cols1Start] * array2[rows2Start][cols2Start];
            return result;
        }
        // Compute operation matrices
        int[][] s1 = subtractdMatricesPartial(array2, array2, rows2Start, cols2Start + sizeToProcess / 2, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s2 = addMatricesPartial(array1, array1, rows1Start, cols1Start, rows1Start, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s3 = addMatricesPartial(array1, array1, rows1Start + sizeToProcess / 2, cols1Start, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s4 = subtractdMatricesPartial(array2, array2, rows2Start + sizeToProcess / 2, cols2Start, rows2Start, cols2Start, sizeToProcess / 2);
        int[][] s5 = addMatricesPartial(array1, array1, rows1Start, cols1Start, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s6 = addMatricesPartial(array2, array2, rows2Start, cols2Start, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s7 = subtractdMatricesPartial(array1, array1, rows1Start, cols1Start + sizeToProcess / 2, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s8 = addMatricesPartial(array2, array2, rows2Start + sizeToProcess / 2, cols2Start, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] s9 = subtractdMatricesPartial(array1, array1, rows1Start, cols1Start, rows1Start + sizeToProcess / 2, cols1Start, sizeToProcess / 2);
        int[][] s10 = addMatricesPartial(array2, array2, rows2Start, cols2Start, rows2Start, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        // Compute incomplete matrices
        int[][] p1 = calculateMatrixProduct_Strassen(array1, s1, rows1Start, cols1Start, 0, 0, sizeToProcess / 2);
        int[][] p2 = calculateMatrixProduct_Strassen(s2, array2, 0, 0, rows2Start + sizeToProcess / 2, cols2Start + sizeToProcess / 2, sizeToProcess / 2);
        int[][] p3 = calculateMatrixProduct_Strassen(s3, array2, 0, 0, rows2Start, cols2Start, sizeToProcess / 2);
        int[][] p4 = calculateMatrixProduct_Strassen(array1, s4, rows1Start + sizeToProcess / 2, cols1Start + sizeToProcess / 2, 0, 0, sizeToProcess / 2);
        int[][] p5 = calculateMatrixProduct_Strassen(s5, s6, 0, 0, 0, 0, sizeToProcess / 2);
        int[][] p6 = calculateMatrixProduct_Strassen(s7, s8, 0, 0, 0, 0, sizeToProcess / 2);
        int[][] p7 = calculateMatrixProduct_Strassen(s9, s10, 0, 0, 0,0, sizeToProcess / 2);
        // Compute partial matrices
        int[][] c11 = addMatrices(addMatrices(p5, p4), subtractMatrices(p6, p2));
        int[][] c12 = addMatrices(p1, p2);
        int[][] c21 = addMatrices(p3, p4);
        int[][] c22 = addMatrices(subtractMatrices(p5, p3), subtractMatrices(p1, p7));
        // Combine for result to return
        return combineMatricies(c11, c12, c21, c22);
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

    private static int[][] subtractMatrices(int[][] minuend, int[][] subtrahend) throws IllegalArgumentException {
        if(minuend.length != subtrahend.length || minuend[0].length != subtrahend[0].length){
            throw new IllegalArgumentException("Matrices of different dimensions cannot be directly subtracted");
        }
        int[][] result = new int[minuend.length][minuend[0].length];
        for (int i = 0; i < minuend.length; i++){
            for (int j = 0; j < minuend[0].length; j++){
                result[i][j] = minuend[i][j] - subtrahend[i][j];
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
