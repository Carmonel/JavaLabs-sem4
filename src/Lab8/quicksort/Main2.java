package Lab8.quicksort;

import java.util.Arrays;

public class Main2 {
    public static void main(String[] args){

        int size = 10000;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++){
            array[i] = (int)(Integer.MAX_VALUE * Math.random());
        }

        new AsyncQuickSort<Integer>((n1, n2) -> n1 - n2).sort(array);

        System.out.println(Arrays.deepToString(array));

    }
}
