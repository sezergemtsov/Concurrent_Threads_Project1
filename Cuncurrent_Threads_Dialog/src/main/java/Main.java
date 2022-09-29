public class Main {
    public static void main(String[] args) {

        Thread t  = new MyThread();
        t.setName("Thread 1");
        Thread t1 = new MyThread();
        t1.setName("Thread 2");
        Thread t2 = new MyThread();
        t2.setName("Thread 3");

        t.start();
        t1.start();
        t2.start();

        threadsStop(t,t1,t2);

    }

    static void threadsStop(Thread ... t) {
        Thread tt = Thread.currentThread();
        try {
            tt.sleep(10000);
            for (int i = 0; i < t.length; i++) {
                t[i].interrupt();
            }
            System.out.println("All threads were terminated");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
