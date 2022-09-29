import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ArraysMain {

    public static List<Double> myArray = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException,ExecutionException {

        arrayFill(30_000_000);

        long startTime = System.currentTimeMillis();

        int i = Runtime.getRuntime().availableProcessors();
        int step = myArray.size()/i;
        int start = 0;
        int stop = step;

        final List<ConcurrentCalculator> callables = new ArrayList<>();

        for (int j = 0; j < i; j++) {
            List<Double> cutArray = myArray.subList(start,stop);
            start+=step;
            stop+=step;
            callables.add(new ConcurrentCalculator(cutArray));
        }

        final ExecutorService myThreadsPool = Executors.newFixedThreadPool(i);
        final List<Future<Double>> result = myThreadsPool.invokeAll(callables);

        double concurrentSum = 0;

        concurrentSum+=result.stream().mapToDouble(x-> {
            try {
                return x.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).sum();


        double concurrentAverage = concurrentSum/myArray.size();

        System.out.println("Concurrent sum is: " + concurrentSum);
        System.out.println("Concurrent average is: " + concurrentAverage);
        System.out.println("Concurrent time: "+ (System.currentTimeMillis()-startTime));
        myThreadsPool.shutdown();

        long startTime2 = System.currentTimeMillis();
        double sum = sum();
        double average = average(sum);
        System.out.println("Line sum is: "+sum);
        System.out.println("Line sum is: "+average);
        System.out.println("Line time: "+ (System.currentTimeMillis()-startTime2));

    }

    public static void arrayFill(int quantity) {
        for (int i = 0; i < quantity; i++) {
            myArray.add(Math.random()*100);
        }
    }

    public static Double sum() {
        double result = myArray.stream().mapToDouble(x -> x).sum();
        return result;
    }

    public static Double average(double sum) {
        double result = sum/myArray.size();
        return result;
    }

}


