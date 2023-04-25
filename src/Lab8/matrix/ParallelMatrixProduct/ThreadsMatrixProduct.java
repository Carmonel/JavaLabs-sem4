package Lab8.matrix.ParallelMatrixProduct;

import Lab8.matrix.UsualMatrix;

public class ThreadsMatrixProduct {

    Thread[] threads;

    ThreadsMatrixProduct(int threadsCount){
        threads = new Thread[threadsCount];
    }

    UsualMatrix getProduct(UsualMatrix matrix1, UsualMatrix matrix2){
        if (matrix1.getColumns() != matrix2.getRows()){
            throw new IllegalArgumentException("Matrix1 columns != Matrix2 rows");
        }

        UsualMatrix result = new UsualMatrix(matrix1.getRows(), matrix2.getColumns());

        // create threads
        for (int i = 0; i < threads.length; i++){
            int startRow = i * matrix1.getRows() / threads.length;
            int endRow = (i + 1) * matrix1.getRows() / threads.length;
            threads[i] = new Thread(new processElement(matrix1, matrix2, result, startRow, endRow));
            threads[i].start();
        }

        for (Thread i : threads){
            try{
                i.join();
            }
            catch (Exception e){
                System.out.println("ThreadsMatrixProduct.java::33 exception.");
                e.printStackTrace();
            }
        }

        return result;
    }
}

class processElement implements Runnable{
    UsualMatrix m1, m2, result;
    int startRow, endRow;

    public processElement(UsualMatrix m1, UsualMatrix m2, UsualMatrix result, int startRow, int endRow){
        this.m1 = m1;
        this.m2 = m2;
        this.startRow = startRow;
        this.endRow = endRow;
        this.result = result;
    }

    public void run(){
        // product of ROW to COLUMN
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < m2.getColumns(); j++) {
                double sum = 0;
                for (int k = 0; k < m1.getColumns(); k++) {
                    sum += m1.getElement(i, k) * m2.getElement(k, j);
                }
                result.setElement(i, j, sum);
            }
        }
    }
}
