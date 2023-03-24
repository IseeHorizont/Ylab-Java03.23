package homework3.src.main.java.com.ylab.tasks.orgstructure;

import java.io.File;
import java.io.IOException;

public class OrgStructureParserTest {

    public static void main(String[] args) throws IOException {

        OrgStructureParserImpl orgStructureParser = new OrgStructureParserImpl();
        Employee boss = orgStructureParser.parseStructure(
                new File("homework3/src/main/java/com/ylab/tasks/orgstructure/employee")
        );
        System.out.println("Boss = " + boss);
    }
}
