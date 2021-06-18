package edu.waketech.test;

import java.util.Random;

import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;

/**
 * Class containing static method to generate a Student object and add him or her to the
 * StudentBody.
 * <p>
 * Because we want the same student in multiple courses, the list of possible student ids is
 * limited and our random student id generator will generate duplicate student ids.
 * </p>
 * <p>
 * Because we want the student associated with a given id to have only one name, we
 * base the name on the generated id.  So, if student 111 is "Sophie Princess," everytime
 * the random number generates 111, that Student object will always be given
 * the name "Sophie Princess."
 * </p>
 * <p>
 * There will be lots and lots of student ids whose name is Sophie Princess, however.
 * </p>
 *
 * @author parks
 *
 */
public class StudentGenerator {

    /**
     * Lowest student id number, inclusive
     */
    public static final int LOW_ID_NUMBER = 100;

    /**
     * Highest student id number, inclusive
     */
    public static final int HI_ID_NUMBER = 200;

    /**
     * All possible names for students
     */
    public static final NAMES[] NAME_ROOTS = {
            new NAMES("Sophie", "Princess"),
            new NAMES("Sally", "Sweet"),
            new NAMES("Jack", "Hunter")
    };

    /**
     * Generate a random student and add him or her to the StudentBody.
     * Duplicate students can be created,
     * and StudentBody will gracefully handle them.  Note that duplicate students
     * will always have the same name.
     *
     * @return a new student with a random id between LOW_ID_NUMBER and HI_ID_NUMBER.  Duplicate
     * students may be generated, but all such students will have the same name.
     */
    public static Student genStudent() {
        Random rand = new Random();
        int id = rand.nextInt(HI_ID_NUMBER - LOW_ID_NUMBER + 1) + LOW_ID_NUMBER;
        int nameChoice = id % NAME_ROOTS.length;
        String f = NAME_ROOTS[nameChoice].first;
        String l = NAME_ROOTS[nameChoice].last;
        Student student = new Student(l, f, id);
        StudentBody.getInstance().add(student);
        return student;
    }

    /**
     * Q&D way to avoid parallel arrays--make a class for one row, and then have a single
     * dimension array of a complete name.
     *
     * @author parks
     *
     */
    private static class NAMES {
        private String first;
        private String last;
        private NAMES(String f, String l) {
            first = f;
            last = l;
        }
    }
}
