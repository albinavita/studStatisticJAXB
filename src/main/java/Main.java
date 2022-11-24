import comparator.IStudentComparator;
import comparator.IUniversityComparator;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import exception.StateException;
import io.JsonWriter;
import io.XlsReader;
import io.XlsWriter;
import io.XmlWriter;
import model.Root;
import model.Statistics;
import model.Student;
import model.University;
import util.ComparatorUtil;
import util.StatisticsUtil;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
    private static final String FILE_NAME = "src/main/resources/universityInfo.xlsx";
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws StateException {

        readLogFile();
        LOGGER.log(Level.INFO, "Log configuration");

        List<University> universities =
                XlsReader.readSheetUniversity(FILE_NAME);
        IUniversityComparator universityComparator =
                ComparatorUtil.getUniversityComparator(UniversityComparatorType.YEAR);
        universities.sort(universityComparator);

        List<Student> students =
                XlsReader.readSheetStudent("src/main/resources/universityInfo.xlsx");
        IStudentComparator studentComparator =
                ComparatorUtil.getStudentComparator(StudentComparatorType.AVG_EXAM_SCORE);
        students.sort(studentComparator);
        List<Statistics> statistics = StatisticsUtil.createStatistics(students, universities);
        XlsWriter.createXlsTable(statistics, "statistics.xlsx");

        Root root = new Root();
        root.setStudents(students);
        root.setUniversities(universities);
        root.setStatistics(statistics);
        root.setProcessedDate(new Date());

        XmlWriter.createXmlReq(root);
        JsonWriter.createJsonReq(root);
        LOGGER.log(Level.INFO, "Application finished");
    }

    private static void readLogFile() {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getResourceAsStream("/logging.properties"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Could not setup logger configuration.", e.toString());
        }
    }
}
