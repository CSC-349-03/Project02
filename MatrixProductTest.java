import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Andrew LeDawson on 1/29/2018.
 */
public class MatrixProductTest {
    @Test
    public void matrixProduct_DAC() {
        int[][] matrix1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
            int[][] matrix2 = {{17, 18, 19, 20}, {21, 22, 23, 24}, {25, 26, 27, 28}, {29, 30, 31, 32}};
        int[][] result = MatrixProduct.matrixProduct_DAC(matrix1, matrix2);
        int[][] knownGoodResult = MatrixWork.matrixProduct(matrix1, matrix2);
        assertArrayEquals(knownGoodResult, result);
    }

    @Test
    public void addMatricesPartial() {
        int[][] matrix1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][]result = MatrixProduct.addMatricesPartial(matrix1, matrix1, 2,2, 0, 0, 2);
        int[][] expected = {{12, 14}, {20, 22}};
        assertArrayEquals(result, expected);
    }

    /*@org.junit.Test
    public void combineMatricies() {
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{5, 6}, {7, 8}};
        int[][] matrix3 = {{9, 10}, {11, 12}};
        int[][] matrix4 = {{13, 14}, {15, 16}};
        int[][] expectedResult = {{1, 2, 5, 6}, {3, 4, 7, 8}, {9, 10, 13, 14}, {11, 12, 15, 16}};
        int[][] result = MatrixProduct.combineMatricies(matrix1, matrix2, matrix3, matrix4);
        assertArrayEquals(expectedResult, result);
    }*/

    /*@org.junit.Test
    public void addMatricies() {
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{5, 6}, {7, 8}};
        int[][] expectedResult = {{6, 8}, {10, 12}};
        try {
            int[][] result = MatrixProduct.addMatrices(matrix1, matrix2);
            assertArrayEquals(expectedResult, result);
        } catch (Exception e){
            System.out.println("Exception!");
        }
    }*/


}