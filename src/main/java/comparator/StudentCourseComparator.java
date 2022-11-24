package comparator;

import model.Student;

public class StudentCourseComparator implements IStudentComparator {

    @Override
    public int compare(Student o1, Student o2) {
        return Integer.compare(o1.getCurrentCourseNumber(), o2.getCurrentCourseNumber());
    }
}
