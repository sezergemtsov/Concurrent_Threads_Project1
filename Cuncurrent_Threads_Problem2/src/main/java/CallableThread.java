import java.util.concurrent.Callable;

public class CallableThread implements Callable<CallableThread> {

    String name;
    int cnt = 0;

    CallableThread(String name) {
        this.name = name;
    }

    @Override
    public CallableThread call() {

        while (cnt<5) {
            try {
                Thread.sleep(2500);
                System.out.println("Current thread is: " + name);
                cnt++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return this;
    }

    public int getCnt() {
        return cnt;
    }

}
