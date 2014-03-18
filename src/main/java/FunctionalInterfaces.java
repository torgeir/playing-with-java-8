package main.java;

public class FunctionalInterfaces {

    @FunctionalInterface // optional
    public interface Receiver {
        public /* abstract */ void switchAnonymousInnerSubclassForClosure();
    }

    public static void foo(Receiver receiver) {
        receiver.switchAnonymousInnerSubclassForClosure();
    }

    public static void main(String[] args) {

//        foo(new Receiver() {
//            @Override
//            public void switchAnonymousInnerSubclassForClosure() {
//                System.out.println("called..");
//            }
//        });

        foo(() -> System.out.println("called.."));
    }
}
