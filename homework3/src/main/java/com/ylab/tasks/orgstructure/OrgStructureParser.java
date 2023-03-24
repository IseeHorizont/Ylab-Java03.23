package homework3.src.main.java.com.ylab.tasks.orgstructure;

import java.io.File;
import java.io.IOException;

public interface OrgStructureParser {

    Employee parseStructure(File csvFile) throws IOException;
}
