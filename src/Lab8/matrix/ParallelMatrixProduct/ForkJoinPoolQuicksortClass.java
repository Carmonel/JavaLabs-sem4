package Lab8.matrix.ParallelMatrixProduct;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolQuicksortClass<T> extends RecursiveTask<Comparable<T>[]> {
    private final Comparable<T>[] array;
    private final int low;
    private final int high;

    public ForkJoinPoolQuicksortClass(Comparable<T>[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }
    public void swap(int i, int j){
        Comparable<T> temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public int partition(int l,int r){
        Comparable<T> pivot = array[r]; // pivot element
        int i = (l - 1);

        for (int j = l; j <= r - 1; j++)
        {
            // If current element is smaller than the pivot
            if (array[j].compareTo((T) pivot) < 0)
            {
                i++; // increment index of smaller element
                Comparable<T> t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        Comparable<T> t = array[i+1];
        array[i+1] = array[r];
        array[r] = t;
        return (i + 1);
    }
    @Override
    protected Comparable<T>[] compute() {
        if (high - low > 1){
            int mid = partition(low, high);

            ForkJoinPoolQuicksortClass<T> left = new ForkJoinPoolQuicksortClass<T>(array, low, mid-1);
            ForkJoinPoolQuicksortClass<T> right = new ForkJoinPoolQuicksortClass<T>(array, mid+1, high);
            left.fork();
            right.fork();
        }
        return array;
    }
    public void printArr(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < array.length; i++){
            str.append(" ").append(array[i]).append(",");
        }
        str.append("]");
        System.out.println(str);
    }
}

class ForkJoinPoolQuicksort{
    public static void main(String[] args){
        int size = 50;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++){
            array[i] = (int)(100*Math.random());
        }

        ForkJoinPoolQuicksortClass<Integer> a = new ForkJoinPoolQuicksortClass<>(array,0, size-1);
        a.printArr();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(a);
        a.printArr();
    }
}