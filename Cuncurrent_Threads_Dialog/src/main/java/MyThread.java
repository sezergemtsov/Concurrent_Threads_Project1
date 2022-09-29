public class MyThread extends Thread{

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Thread.sleep(2500);
                System.out.println("Current thread is: " + this.getName());
            }
        } catch (InterruptedException e) {
        }
    }
}
