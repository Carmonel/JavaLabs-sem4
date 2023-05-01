package Lab8.quicksort;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class AsyncQuickSort<T> {

    private final ForkJoinPool fjp = new ForkJoinPool();
    private T[] array;

    private final Comparator<T> comparator;

    public AsyncQuickSort(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void sort(T[] array) {
        this.array = array;
        fjp.invoke(new SortTask(0, array.length - 1));
    }

    private class SortTask extends RecursiveTask<Void> {

        private final int l, r;

        SortTask(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public void swap(int i, int j){
            T temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        public int partition(int l,int r){
            T pivot = array[r]; // pivot element
            int i = (l - 1);

            for (int j = l; j <= r - 1; j++)
            {
                if (comparator.compare(array[j], pivot) < 0)
                {
                    i++; // increment index of smaller element
                    swap(i, j);
                }
            }

            swap(i+1, r);

            return (i + 1);
        }
        @Override
        protected Void compute() {
            if (l >= r) {
                return null;
            }

            int mid = partition(l, r);

            SortTask st1 = new SortTask(l, mid - 1),
                    st2 = new SortTask(mid + 1, r);
            st1.fork();
            st2.fork();
            st1.join();
            st2.join();
            return null;
        }
    }

}
