package Lab8.matrix.ParallelMatrixProduct;

public class TimeChecher {
    long startTime;

    TimeChecher(){
        startTime = System.currentTimeMillis();
    }
    void setNewStart(){
        startTime = System.currentTimeMillis();
    }
    String printTimeFromStart(){
        StringBuilder res = new StringBuilder();
        res.append(System.currentTimeMillis() - startTime);
        return res + " ms";
    }
}
