/*  David Twyman, Andrew LeDawson
**  dtwyman@calpoly.edu, aledawson@calpoly.edu
**  CSC 349-03
**  Project 2
**  1-22-2018
*/

import java.util.*;

public class MatrixWork
{
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the data file name");
      String fname = scanner.next();
      File file = new File(fname);
      Scanner fScanner = new Scanner(file);
      
   }
   public static int[][] matrixProduct (int [][] A, int[][] B) 
   {
      int arows = A.length;
      int acols = A[0].length;
      int brows = B.length;
      int bcols = B[0].length;
      int[][] C = new int[arows][bcols];
      if (arows != bcols)
      {
         throw new IllegalArgumentException();
      }
      for (int i = 0; i < arows; i++)
      {
         for (int j = 0; j < bcols; j++)
         {
            C[i][j] = dotMult(A, B, i, j, acols);
         }
      }
      return C;
   }

   private static int dotMult(int [][]A, int [][]B, int row, int col, int size)
   {
      int sum = 0;
      for (int i = 0; i < size; i++)
      {
         sum += A[row][i]*B[i][col];
      }
      return sum;
   }
}
