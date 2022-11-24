package util;

import comparator.*;
import enums.StudentComparatorType;
import enums.UniversityComparatorType;
import exception.StateException;

public class ComparatorUtil {

    private static final String UNIVER = "Университет";
    private static final String STUDENT = "Студент";

    private ComparatorUtil() {
    }

    public static IStudentComparator getStudentComparator(StudentComparatorType studentComparatorType) throws StateException {

        switch (studentComparatorType) {

            case UNIVERSITY_ID:
                return new StudentUniversityIdComparator();
            case FULL_NAME:
                return new StudentFullNameComparator();
            case COURSE:
                return new StudentCourseComparator();
            case AVG_EXAM_SCORE:
                return new StudentAvgExamScoreComparator();
            default:
                throw  new StateException(STUDENT);
        }
    }

    public static IUniversityComparator getUniversityComparator(UniversityComparatorType universityComparatorType) throws StateException {

        switch (universityComparatorType) {

            case ID:
                return new UniversityIdComparator();
            case FULL_NAME:
                return new UniversityFullNameComparator();
            case SHORT_NAME:
                return new UniversityShortNameComparator();
            case PROFILE:
                return new UniversityProfileComparator();
            case YEAR:
                return new UniversityYearComparator();
            default:
                throw  new StateException(UNIVER);
        }
    }
}
