package cz.janousek;

public class SimpleVectorClockViolation {

    // contract:

    public volatile Subject s;
	public volatile int a = 0;

    public static void main(String[] args) {
	// write your code here
        SimpleVectorClockViolation m = new SimpleVectorClockViolation();
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

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//                synchronized (s) {
                    s.m1();
//                }
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
