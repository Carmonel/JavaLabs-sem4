package Lab9.deadlock;

import java.util.LinkedList;
import java.util.Scanner;

public class InternetCafe {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter N (comp count) and M (tourists): ");
        InternetCafeClass ICC = new InternetCafeClass(sc.nextInt(), sc.nextInt());
    }
}

class Pair{
    public int num, time;
    Pair(int num){
        this.num = num;
        time = (int)(10000*Math.random());
    }
}

class InternetCafeClass{
    Thread[] computers;
    LinkedList<Pair> touristQueue;

    public InternetCafeClass(int threadsCount, int touristsCount){
        computers = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) computers[i] = new Thread();

        touristQueue = new LinkedList<Pair>();
        for (int i = 0; i < touristsCount; i++) touristQueue.add(new Pair(i));

        System.out.println("Cafe now is open!");
        while (!touristQueue.isEmpty()){
            for (int i = 0; i < threadsCount; i++){
                if (!computers[i].isAlive()){
                    computers[i] = new PC(touristQueue.poll());
                    computers[i].start();
                }
            }
        }
    }
}

class PC extends Thread {
    int time;
    int tourist;
    public PC(Pair values) {
        this.time = values.time;
        this.tourist = values.num;
    }

    @Override
    public void run() {
        System.out.println("Tourist " + tourist + " started job for " + time + " ms");
        // some code
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("--> Tourist " + tourist + " ended job");
    }
}