package homework2.src.main.java.com.ylab.tasks.accumulator;

public class StatsAccumulatorTest {

    public static void main(String[] args) {
        StatsAccumulatorImpl statsAccumulator = new StatsAccumulatorImpl();

        statsAccumulator.add(1);
        statsAccumulator.add(2);
        System.out.println(statsAccumulator.getAvg());

        statsAccumulator.add(0);
        System.out.println(statsAccumulator.getMin());

        statsAccumulator.add(3);
        statsAccumulator.add(8);
        System.out.println(statsAccumulator.getMax());
        System.out.println(statsAccumulator.getCount());
    }
}
