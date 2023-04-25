package Lab8.matrix.ParallelMatrixProduct;

import Lab8.matrix.UsualMatrix;

public class ParallelMatrixProductMain {
    public static void main(String[] args){
        UsualMatrix matrix1 = new UsualMatrix(3, 4);
        matrix1.makeRandom();

        UsualMatrix matrix2 = new UsualMatrix(4, 3);
        matrix2.makeRandom();

        System.out.println("Matrix 1:");
        System.out.println(matrix1);
        System.out.println("Matrix 2:");
        System.out.println(matrix2);

        ThreadsMatrixProduct result = new ThreadsMatrixProduct(5);
        System.out.println("Result matrix:");
        System.out.println(result.getProduct(matrix1, matrix2));

    }
}
