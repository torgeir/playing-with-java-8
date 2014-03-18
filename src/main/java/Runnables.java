package main.java;

public class Runnables {

    public static void main(String[] args) throws InterruptedException {

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("in another thread"));
//            }
//        });

        Thread thread = new Thread(() ->
            System.out.println("in another thread"));

        thread.start();
        System.out.println("in main");

        thread.join();
        System.out.println("done");
    }
}
