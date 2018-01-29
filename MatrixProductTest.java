import static org.junit.Assert.*;

/**
 * Created by Andrew LeDawson on 1/29/2018.
 */
public class MatrixProductTest {

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

    @org.junit.Test
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
    }
}