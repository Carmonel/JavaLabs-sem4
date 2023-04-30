package Lab8.matrix.ParallelMatrixProduct;

/*class Quicksort extends Thread {
    private final Object[] arr;
    private final int low;
    private final int high;
    public static int numThreads;
    public static int count = 0;

    public Quicksort(Object[] arr, int low, int high){
        this.arr = arr;
        this.low = low;
        this.high = high;
    }

    public void run(){
        parallelQuicksort(arr,low,high);
    }

    public static void quicksort(Object[] arr, int low, int high){
        if (high>low){
            int i = partition(arr,low,high);
            quicksort(arr,low,i-1);
            quicksort(arr,i+1,high);
        }
    }

    public static  void parallelQuicksort(Object[] arr, int low, int high){
        if (high>low){
            int i = partition(arr,low,high);
            if (count < numThreads){
                count++;
                Quicksort quicksort  = new Quicksort(arr, low, i-1);
                quicksort.start();
                try{
                    quicksort.join();
                }
                catch (InterruptedException e){}
            }
            else{
                quicksort(arr,low,i-1);
            }
            if (count < numThreads){
                count++;
                Quicksort quicksort  = new Quicksort(arr, i+1, high);
                quicksort.start();
                try{
                    quicksort.join();
                }
                catch (InterruptedException e){}
            }
            else{
                quicksort(arr,i+1,high);
            }
        }
    }

    public static int partition(Object[] arr, int low,int high){
        Object pivot = arr[high];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (arr[j] < pivot){

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    public static void swap(Object[] arr,int i,int j){
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int median(Object[] arr,int l,int mid,int r){

    }
}

public class ParallelQuicksort {
    public static void main(String[] args){
        int size = 1000;
        Integer[] array = new Integer[size];
        for (Integer i : array){
            i = (int)(100*Math.random());
        }

        //Quicksort result = new
    }
}
*/