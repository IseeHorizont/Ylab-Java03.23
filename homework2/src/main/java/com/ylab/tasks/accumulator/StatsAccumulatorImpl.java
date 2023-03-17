package homework2.src.main.java.com.ylab.tasks.accumulator;

public class StatsAccumulatorImpl implements StatsAccumulator {

    private int min = Integer.MAX_VALUE;
    private int max = Integer.MIN_VALUE;
    private int counter = 0;
    private double avgValue = 0.0;

    @Override
    public void add(int value) {
        double currentValueSum = avgValue * counter;
        counter++;
        if (value < min) {
            min = value;
        } else if (value > max) {
            max = value;
        }
        avgValue = (currentValueSum + value) / counter;
    }

    @Override
    public int getMin() {
        return min;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public int getCount() {
        return counter;
    }

    @Override
    public Double getAvg() {
        return avgValue;
    }
}
