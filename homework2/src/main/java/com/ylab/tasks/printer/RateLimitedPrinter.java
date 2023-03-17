package homework2.src.main.java.com.ylab.tasks.printer;

public class RateLimitedPrinter {

    public int interval;
    public long lastPrintTime;

    public RateLimitedPrinter(int interval) {
        this.interval = interval;
        this.lastPrintTime = System.currentTimeMillis();
    }

    public void print(String message) {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - lastPrintTime;
        if (timeDifference >= interval) {
            System.out.println(message);
            lastPrintTime = System.currentTimeMillis();
        }
    }
}
