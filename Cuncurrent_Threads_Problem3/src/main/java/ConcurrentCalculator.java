import java.util.List;
import java.util.concurrent.Callable;

public class ConcurrentCalculator implements Callable<Double> {

    List<Double> list;

    public ConcurrentCalculator(List list) {
        this.list = list;
    }

    @Override
    public Double call() {
        return list.stream().mapToDouble(x->x).sum();
    }
}
