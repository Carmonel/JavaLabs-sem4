package Lab8.matrix.ParallelMatrixProduct;


import java.util.Arrays;
import java.util.Objects;

class Quicksort<T> implements Runnable{
    private final Comparable<T>[] arr;
    private final int low;
    private final int high;
    Thread[] threads;
    boolean txt;

    public Quicksort(Comparable<T>[] arr, int low, int high, Thread[] threads, boolean outInfo){
        this.arr = arr;
        this.low = low;
        this.high = high;
        this.threads = threads;
        this.txt = outInfo;
    }

    public void run(){
        try {
            parallelQuicksort(arr,low,high);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void quicksort(Comparable<T>[] arr, int low, int high){
        if (high>low){
            int i = partition(arr,low,high);
            quicksort(arr,low,i-1);
            quicksort(arr,i+1,high);
        }
    }

    public void parallelQuicksort(Comparable<T>[] arr, int low, int high) throws InterruptedException {
        if (high > low){
            int mid = partition(arr, low, high);
            boolean founded1 = false;
            boolean founded2 = false;
            while (!founded2) {
                for (int i = 0; i < threads.length; i++) {
                    if ((!threads[i].isAlive()) && (!founded1)) {
                        threads[i] = new Thread(new Quicksort<T>(arr, low, mid - 1, threads, txt));
                        threads[i].start();
                        threads[i].join();
                        founded1 = true;
                        continue;
                    }
                    if (!threads[i].isAlive()) {
                        threads[i] = new Thread(new Quicksort<T>(arr, mid + 1, high, threads, txt));
                        threads[i].start();
                        threads[i].join();
                        founded2 = true;
                        break;
                    }
                }
            }
        }
    }

    public int partition(Comparable<T>[] A, int l,int r){
        Comparable<T> pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j].compareTo((T) pivot) < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    public void swap(int i,int j){
        if (txt) System.out.println("swap: " + arr[i] + " " + arr[j]);
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
        TimeChecher time = new TimeChecher();
        int size = 10000;
        Integer[] array = new Integer[size];
        int[] array2 = new int[size];
        for (int i = 0; i < size; i ++){
            array[i] = (int)(100*Math.random());
            array2[i] = array[i];
        }
        int threadCount = 150;
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) threads[i] = new Thread();

        Quicksort<Integer> result = new Quicksort<>(array, 0, array.length-1, threads, false);
        result.run();
        System.out.println("Threads -> " + time.printTimeFromStart());

        time.setNewStart();
        standartQuickSort a = new standartQuickSort();
        a.sort(array2, 0, array2.length-1);
        System.out.println("Standart -> " + time.printTimeFromStart());

        boolean check = true;
        for (int i = 0; i < size; i++){
            if (array[i] != array2[i]){
                check = false;
                break;
            }
        }
        if (check) System.out.println("true");
    }
}
