package Lab8;

public class TimeChecker {
    long startTime;

    public TimeChecker(){
        startTime = System.currentTimeMillis();
    }
    public void setNewStart(){
        startTime = System.currentTimeMillis();
    }
    public String printTimeFromStart() {
        StringBuilder res = new StringBuilder();
        res.append(System.currentTimeMillis() - startTime);
        return res + " ms";
    }
}
