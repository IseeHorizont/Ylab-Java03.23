package homework3.src.main.java.com.ylab.tasks.orgstructure;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class OrgStructureParserImpl implements OrgStructureParser {

    private static List<Employee> employeeList = new ArrayList<>();
    private static Map<Long, Employee> employeeById = new HashMap<>();

    @Override
    public Employee parseStructure(File csvFile) throws IOException {

        try(Scanner scanner = new Scanner(csvFile)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String[] currentReadLine = scanner.nextLine().split(";");
                Employee currentEmployee = new Employee(
                        Long.parseLong(currentReadLine[0]),
                        currentReadLine[1].isEmpty() ? null : Long.parseLong(currentReadLine[1]),
                        currentReadLine[2],
                        currentReadLine[3]
                );
                employeeList.add(currentEmployee);
            }
        }

        // fill Map with objects 'Employee' by id
        fillEmployeeById();

        // setting each employee link to boss and list of subordinates
        setBossLinkAndSubordinate();

        // print list of all employees
        printAllStructureInOrder();

        Employee boss = getBoss();
        return boss;
    }

    // searching boss of the bosses
    public static Employee getBoss() {
        return employeeList.stream()
                .filter(employee -> employee.getBossId() == null)
                .findFirst()
                .get();
    }

    // setting each employee link to boss and list of subordinates
    public static void setBossLinkAndSubordinate() {
        for (Employee employee : employeeList) {
            Long bossId = employee.getBossId();
            if (bossId != null) {
                employee.setBoss(employeeById.get(bossId));
                employeeById.get(bossId).getSubordinate().add(employee);
            }
        }
    }

    // fill Map with objects 'Employee' by id
    public static void fillEmployeeById() {
        for (Employee employee : employeeList) {
            employeeById.put(employee.getId(), employee);
        }
    }

    // print list of all employees
    public static void printAllStructureInOrder() {
        employeeList.stream()
                .sorted(Comparator.comparing((Employee::getId)))
                .forEach(employee -> System.out.printf(
                        "%nEmployee: %s %nBoss: %s %nSubordinate: %s %n",
                        employee, employee.getBoss(), employee.getSubordinate()
                ));
    }
}
