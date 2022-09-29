import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class CallableMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        final ArrayList<CallableThread> callables = new ArrayList<>();

        final CallableThread ct1 = new CallableThread("Thread 1");
        final CallableThread ct2 = new CallableThread("Thread 2");
        final CallableThread ct3 = new CallableThread("Thread 3");
        final CallableThread ct4 = new CallableThread("Thread 4");

        callables.add(ct1);
        callables.add(ct2);
        callables.add(ct3);
        callables.add(ct4);

        final ExecutorService myThreadsPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        final List<Future<CallableThread>> result = myThreadsPool.invokeAll(callables);

        System.out.println("Results by threads:");

        result.forEach(x-> {
            try {
                System.out.println(x.get().name+ " - count of attempts were: "+ x.get().getCnt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        myThreadsPool.shutdown();

    }
}
