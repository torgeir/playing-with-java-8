package main.java;

public class FunctionalInterfaces {

    @FunctionalInterface // optional
    public interface Receiver {
        public /* abstract */ void nameDoesNotMatter();
    }

    public static void foo(Receiver receiver) {
        receiver.nameDoesNotMatter();
    }

    public static void main(String[] args) {

//        foo(new Receiver() {
//            @Override
//            public void nameDoesNotMatter() {
//                System.out.println("called..");
//            }
//        });

        foo(() -> System.out.println("called.."));
    }
}
