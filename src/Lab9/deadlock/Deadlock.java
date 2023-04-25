package Lab9.deadlock;

public class Deadlock {
    private static final Object obj1 = new Object();
    private static final Object obj2 = new Object();

    public static void main(String[] args){
        Thread1 lock1 = new Thread1();
        Thread2 lock2 = new Thread2();

        lock1.start();
        lock2.start();

        try {
            lock1.join();
            lock2.join();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("End.");
    }

    static class Thread1 extends Thread{
        public void run(){
            synchronized (obj1) {
                System.out.println("Thread 1: Holding lock 1");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Thread 1: Interrupted");
                }
                System.out.println("Thread 1: Waiting for lock 2");

                // deadlock here
                synchronized (obj2) {
                    System.out.println("Thread 1: Holding lock 1 & 2"); // never reached
                }
            }
            System.out.println("Thread 1: Released lock 1 & 2"); // never reached
        }
    }
    static class Thread2 extends Thread{
        public void run(){
            synchronized (obj2) {
                System.out.println("Thread 2: Holding lock 2");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Thread 2: Interrupted");
                }
                System.out.println("Thread 2: Waiting for lock 1");

                // deadlock here
                synchronized (obj1) {
                    System.out.println("Thread 2: Holding lock 1 & 2"); // never reached
                }
            }
            System.out.println("Thread 2: Released lock 1 & 2"); // never reached
        }
    }
}
