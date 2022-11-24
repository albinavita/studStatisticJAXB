package io;

import enums.StudyProfile;
import model.Student;
import model.University;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XlsReader {
    private static final Logger LOGGER = Logger.getLogger(XlsReader.class.getName());

    private static final String UNIVERS = "Университеты";
    private static final String STUDENTS = "Студенты";

    private XlsReader() {
    }

    public static List<University> readSheetUniversity(String fileName) {
        List<University> universities = new ArrayList<>();

        LOGGER.log(Level.INFO, "Reading the book about the University is started.");
        Workbook book = createBook(fileName);
        Sheet sheet = book.getSheet(UNIVERS);
        Iterator<Row> rows = sheet.iterator();
        rows.next();
        while (rows.hasNext()) {
            Row current = rows.next();
            University university = new University();
            university.setId(current.getCell(0).getStringCellValue());
            university.setFullName(current.getCell(1).getStringCellValue());
            university.setShortName(current.getCell(2).getStringCellValue());
            university.setYearOfFoundation((int) current.getCell(3).getNumericCellValue());
            university.setMainProfile(StudyProfile.valueOf(current.getCell(4).getStringCellValue()));

            universities.add(university);
        }
        close(book);

        LOGGER.log(Level.INFO, "Reading the book about the University is successfully.");
        return universities;
    }

    public static List<Student> readSheetStudent(String fileName) {
        List<Student> studenties = new ArrayList<>();

        LOGGER.log(Level.INFO, "Reading the book about the student is started.");
        Workbook book = createBook(fileName);
        Sheet sheet = book.getSheet(STUDENTS);
        Iterator<Row> rows = sheet.iterator();
        rows.next();
        while (rows.hasNext()) {
            Row current = rows.next();
            Student student = new Student();
            student.setUniversityId(current.getCell(0).getStringCellValue());
            student.setFullName(current.getCell(1).getStringCellValue());
            student.setCurrentCourseNumber((int) current.getCell(2).getNumericCellValue());
            student.setAvgExamScore((float) current.getCell(3).getNumericCellValue());

            studenties.add(student);
        }
        close(book);
        LOGGER.log(Level.INFO, "Reading the book about the student is successfully.");
        return studenties;
    }

    public static Workbook createBook(String fileName) {
        Workbook book = null;
        try (FileInputStream stream = new FileInputStream(fileName)) {
            book = new XSSFWorkbook(stream);

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Writing to file filed.", e);
        }
        return book;
    }

    public static void close(Workbook book) {
        try {
            book.close();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "File don't close.");
        }
    }

}
