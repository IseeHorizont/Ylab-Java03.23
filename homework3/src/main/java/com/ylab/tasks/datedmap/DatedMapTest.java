package homework3.src.main.java.com.ylab.tasks.datedmap;


public class DatedMapTest {

    public static void main(String[] args) {

        DatedMapImpl datedMap = new DatedMapImpl();

        // add test data
        datedMap.put("keyForTestingGet", "valueForTestingGet");
        waitMePlease();
        datedMap.put("keyForTestingContains", "valueForTestingContains");
        waitMePlease();
        datedMap.put("keyForTestingDelete", "valueForTestingDelete");

        // checking methods
        System.out.printf("Check 'get' operation: '%s' (added '%s').%n",
                datedMap.get("keyForTestingGet"), datedMap.getKeyLastInsertionDate("keyForTestingGet"));
        System.out.printf("Check 'containsKey' operation: '%s' (added '%s').%n",
                datedMap.containsKey("keyForTestingContains"),
                datedMap.getKeyLastInsertionDate("keyForTestingContains"));
        System.out.println("KeySet: " + datedMap.keySet());

        System.out.printf("Before delete: '%s' (added '%s').%n",
                datedMap.get("keyForTestingDelete"), datedMap.getKeyLastInsertionDate("keyForTestingDelete"));
        datedMap.remove("keyForTestingDelete");
        System.out.printf("After delete: '%s' (added '%s').%n",
                datedMap.get("keyForTestingDelete"), datedMap.getKeyLastInsertionDate("keyForTestingDelete"));
        System.out.println("KeySet: " + datedMap.keySet());
    }

    // for difference in timestamps
    public static void waitMePlease() {
        try {
            System.out.println("Wait me please...");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Waiting didn't work...");
        }
    }

}
