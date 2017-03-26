package cz.janousek;

public class NoViolation {

    // contract:

    public volatile Subject s;

    public static void main(String[] args) {
    // write your code here
        NoViolation m = new NoViolation();
        m.init();
    }

    private void init() {
        s = new Subject();

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                synchronized (s) {
                    s.m1();
                    s.m2();
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                synchronized (s) {
                    s.m1();
                }
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    class Subject{
        public void m1(){
            return;
        }

        public void m2(){
            return;
        }
    }
}
