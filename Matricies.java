/**
 * Created by Andrew LeDawson on 1/24/2018.
 */
public class Matricies {
    int[][] array1;
    int[][] array2;

    public Matricies(int[][] array1In, int[][] array2In){
        array1 = array1In;
        array2 = array2In;

    }

    public int rows1(){
        return array1.length;
    }
    public int cols1(){
        return array1[0].length;
    }
    public int rows2(){
        return array2.length;
    }
    public int cols2(){
        return array2[0].length;
    }
}
