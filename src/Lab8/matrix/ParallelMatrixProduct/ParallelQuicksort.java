package Lab8.matrix.ParallelMatrixProduct;

import java.util.Arrays;

class Quicksort<T> extends Thread {
    private final Comparable<T>[] arr;
    private final int low;
    private final int high;
    public static int numThreads = Runtime.getRuntime().availableProcessors();
    public static int count = 0;

    public Quicksort(Comparable<T>[] arr, int low, int high){
        this.arr = arr;
        this.low = low;
        this.high = high;
        run();
    }

    public void run(){
        parallelQuicksort(arr,low,high);
    }

    public void quicksort(Comparable<T>[] arr, int low, int high){
        if (high>low){
            int i = partition(arr,low,high);
            quicksort(arr,low,i-1);
            quicksort(arr,i+1,high);
        }
    }

    public void parallelQuicksort(Comparable<T>[] arr, int low, int high){
        if (high>low){
            int i = partition(arr,low,high);
            if (count < numThreads){
                count++;
                Quicksort<T> quicksort  = new Quicksort<T>(arr, low, i-1);
                quicksort.start();
                try{
                    quicksort.join();
                }
                catch (InterruptedException ignored){}
            }
            else{
                quicksort(arr,low,i-1);
            }
            if (count < numThreads){
                count++;
                Quicksort<T> quicksort  = new Quicksort<T>(arr, i+1, high);
                quicksort.start();
                try{
                    quicksort.join();
                }
                catch (InterruptedException ignored){}
            }
            else{
                quicksort(arr,i+1,high);
            }
        }
    }

    public int partition(Comparable<T>[] A, int l,int r){
        Comparable<T> pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j].compareTo((T) pivot) < 0) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, high);
        return (i + 1);
    }

    public void swap(Comparable<T>[] A,int i,int j){
        Comparable<T> temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < arr.length; i++){
            if (i == 0){
                stringBuilder.append(arr[i]).append(",");
                continue;
            }
            if (i == arr.length-1){
                stringBuilder.append(" ").append(arr[i]);
                continue;
            }
            stringBuilder.append(" ").append(arr[i]).append(",");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}

public class ParallelQuicksort {
    public static void main(String[] args){
        int size = 10;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i ++){
            array[i] = (int)(100*Math.random());
        }

        System.out.println(Arrays.toString(array));
        Quicksort<Integer> result = new Quicksort<>(array, 0, size-1);
        System.out.println(result);
    }
}
