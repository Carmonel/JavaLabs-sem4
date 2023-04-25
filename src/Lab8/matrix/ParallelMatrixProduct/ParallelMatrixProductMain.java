package Lab8.matrix.ParallelMatrixProduct;

import Lab8.matrix.UsualMatrix;

public class ParallelMatrixProductMain {
    public static void main(String[] args){
        int size = 1000;
        UsualMatrix matrix1 = new UsualMatrix(size);
        matrix1.makeRandom();

        UsualMatrix matrix2 = new UsualMatrix(size);
        matrix2.makeRandom();

        TimeChecher time = new TimeChecher();
        UsualMatrix resDefault = matrix1.product(matrix2);
        System.out.println("Default product time: " + time.printTimeFromStart());

        time.setNewStart();
        ThreadsMatrixProduct result = new ThreadsMatrixProduct(4);
        UsualMatrix parallelResult = result.getProduct(matrix1, matrix2);
        System.out.println("Parallel product matrix: " + time.printTimeFromStart());

        if (parallelResult.equals(resDefault)){
            System.out.println("Matrces are equals.");
        }
    }
}
