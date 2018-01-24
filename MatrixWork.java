/*  David Twyman, Andrew LeDawson
**  dtwyman@calpoly.edu, aledawson@calpoly.edu
**  CSC 349-03
**  Project 2
**  1-22-2018
*/

import java.io.*;
import java.util.*;

public class MatrixWork
{
   public static void main (String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter the data file name");
      String fname = scanner.next();
      File file = new File(fname);
      Matricies matricies;
      try {
         FileReader inputFile = new FileReader(file);
         BufferedReader lineBuffer = new BufferedReader(inputFile);
         matricies = scanFile(lineBuffer);
      } catch (FileNotFoundException fileError) {
         System.out.println("ERROR: File not found!");
      } catch (IllegalArgumentException argError) {
         System.out.println("ERROR: Could not parse file!");
      } catch (IOException readError) {
         System.out.println("ERROR: Could not read file!");
      } catch (InputMismatchException readError) {
         System.out.println("ERROR: Could not parse file!");
      } catch (NoSuchElementException readError) {
         System.out.println("ERROR: Could not parse file!");
      }
   }

   public static Matricies scanFile(BufferedReader inputBuffer) throws IllegalArgumentException, IOException, InputMismatchException, NoSuchElementException{
      Matricies matricies = new Matricies();
      String readLine;
      Scanner lineScanner1 = new Scanner(inputBuffer.readLine()).useDelimiter(" ");
      matricies.rows1 = lineScanner1.nextInt();
      matricies.cols1 = lineScanner1.nextInt();
      int current = 0;
      while((readLine = inputBuffer.readLine()) != null){ // Read size of first Matrix
         if(readLine.equals("")){ // Stop loop at empty line (need to scan next matrix)
            break;
         }
         Scanner matrixScanner = new Scanner(readLine).useDelimiter(" ");
         boolean shouldContinue = true;
         int col = 0;
         while(shouldContinue) {
            try {
               matricies.array1[current][col] = matrixScanner.nextInt();
            } catch (NoSuchElementException endOfLine) {
               shouldContinue = false;
            }
            col++;
         }
         current++;
      }
      Scanner lineScanner2 = new Scanner(inputBuffer.readLine()).useDelimiter(" ");
      matricies.rows2 = lineScanner2.nextInt();
      matricies.cols2 = lineScanner2.nextInt();
      current = 0;
      while((readLine = inputBuffer.readLine()) != null){ // Read size of first Matrix
         if(readLine.equals("")){ // Stop loop at empty line (need to scan next matrix)
            break;
         }
         Scanner matrixScanner = new Scanner(readLine).useDelimiter(" ");
         boolean shouldContinue = true;
         int col = 0;
         while(shouldContinue) {
            try {
               matricies.array1[current][col] = matrixScanner.nextInt();
            } catch (NoSuchElementException endOfLine) {
               shouldContinue = false;
            }
            col++;
         }
         current++;
      }
      return matricies;
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
