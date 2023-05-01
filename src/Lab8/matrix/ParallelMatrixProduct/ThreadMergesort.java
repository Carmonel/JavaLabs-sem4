package Lab8.matrix.ParallelMatrixProduct;

class ThreadMergesortClass<T> implements Runnable{
    Comparable<T>[] array;
    Thread[] threads;
    int usedThreads;
    int left, mid, right;

    public ThreadMergesortClass(Comparable<T>[] array, Thread[] threads, int usedThreads, int left, int right) {
        this.array = array;
        this.threads = threads;
        this.usedThreads = usedThreads;
        this.left = left;
        this.right = right;
    }
    void merge(int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        Integer[] L = new Integer[n1];
        Integer[] R = new Integer[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = (Integer) array[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = (Integer) array[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = (Comparable<T>) L[i];
                i++;
            }
            else {
                array[k] = (Comparable<T>) R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            array[k] = (Comparable<T>) L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            array[k] = (Comparable<T>) R[j];
            j++;
            k++;
        }
    }
    void sort(int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(l, m);
            sort(m + 1, r);

            // Merge the sorted halves
            merge(l, m, r);
        }
    }
    @Override
    public void run() {
        if (left < right){
            mid = left + (right - 1) / 2;

            if (threads.length - usedThreads > 1){
                threads[usedThreads] = new Thread(new ThreadMergesortClass<>(array, threads, usedThreads+1, left, mid));
                threads[usedThreads++].start();
                threads[usedThreads] = new Thread(new ThreadMergesortClass<>(array, threads, usedThreads+1, mid + 1, right));
                threads[usedThreads++].start();
                return;
            }
            if (threads.length - usedThreads == 1){
                threads[usedThreads] = new Thread(new ThreadMergesortClass<>(array, threads, usedThreads+1, left, mid));
                threads[usedThreads++].start();
                sort(mid + 1, right);
                return;
            }
            sort(left, mid);
            sort(mid + 1, right);

            merge(left, mid, right);
        }
    }
    void printArray()
    {
        int n = array.length;
        for (int i = 0; i < n; ++i)
            System.out.print(array[i] + " ");
        System.out.println();
    }
}

public class ThreadMergesort {
    public static void main(String[] args){
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++){
            threads[i] = new Thread();
        }


        int size = 10;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++){
            array[i] = (int)(100*Math.random());
        }
        ThreadMergesortClass<Integer> a = new ThreadMergesortClass<>(array, threads, 0, 0, array.length);
        a.printArray();
    }
}
