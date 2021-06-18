package edu.waketech;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import edu.waketech.academic.Assignment;
import edu.waketech.academic.Course;
import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;
import edu.waketech.test.StudentGenerator;
import edu.waketech.utils.Utils;

/**
 * A text-based Gradebook application.
 *
 *
 *  @author parks
 */
public class GradebookApp {

    /**
     * Top level menu
     */
    public static final String[] MENU = {
            "Create Course",
            "Populate a Course",
            "Add an Assignment",
            "Display all Labs for a Student in all courses",
            "Display all Students for a Course",
            "Display the average for a Student in a Course",
            "Display the average for an Assignment",
            "Display a student's average in all courses",
            "Display all students that are in all courses",
            "Exit", // must be last
            // // last
    };

    public static void main(String[] args) {

        // assign a StudentBody reference to allStudents
        StudentBody allStudents = StudentBody.getInstance();
        Scanner kybd = new Scanner(System.in);

        //create a List<Course> named courses to contain the courses defined by the user
        List<Course> courses = new ArrayList<Course>();

        String userChoice = "";

        // main loop for using interaction
        while (!MENU[MENU.length - 1].equals(userChoice)) {
            userChoice = Utils.userChoose(kybd, MENU);

            // "Create Course",
            if (MENU[0].equals(userChoice)) {
                System.out.print("What's the department? ");
                String dept = kybd.next();
                System.out.print("What's the course number? ");
                int courseNum = kybd.nextInt();
                // create the specified course and add it to the user's courses
                Course newCourse = new Course(dept, courseNum);
                courses.add(newCourse);

            }

            // "Populate a Course",
            else if (MENU[1].equals(userChoice)) {
                Course courseToHandle = whichCourse(kybd, courses);
                if (courseToHandle != null) {
                    System.out.print("How many students? ");
                    int seats = kybd.nextInt();
                    // use populateCourse to place students into the specified course.
                    // Note that because populateCourse may
                    // generate duplicate students, and duplicate students
                    // are ignored by the course, the resulting number
                    // of students in the course may be less than the
                    // requested number
                    populateCourse(courseToHandle, seats);
                }
            }

            // "Add an Assignment",
            else if (MENU[2].equals(userChoice)) {
                Course courseToHandle = whichCourse(kybd, courses);
                if (courseToHandle != null) {
                    System.out.print("What's the assignment name? ");
                    String assignmentName = kybd.next();
                    System.out.print("How many points?");
                    int possiblePoints = kybd.nextInt();
                    // use createAssignment to create Assignment objects with
                    // the given name and possible points
                    // and a random score for each student in the
                    // course
                    createAssignment(courseToHandle, assignmentName, possiblePoints);
                }
            }

            // "Display all Labs for a Student in all courses",
            else if (MENU[3].equals(userChoice)) {
                System.out.print("What's the student's id? ");
                for (String s: roster(allStudents.keySet())) {
                    System.out.println(s);
                }
                int studentId = kybd.nextInt();
                System.out.println(oneStudentResults(studentId, courses));
            }

            // "Display all Students for a Course",
            else if (MENU[4].equals(userChoice)) {
                Course courseToHandle = whichCourse(kybd, courses);
                if (courseToHandle != null) {
                    List<String> roster = courseRoster(courseToHandle);
                    for (String s: roster) {
                        System.out.println(s);
                    }
                }
            }

            // "Display the average for a Student in a Course",
            else if (MENU[5].equals(userChoice)) {
                Course courseToHandle = whichCourse(kybd, courses);
                if (courseToHandle != null) {
                    System.out.println("What's the student id? ");
                    for (String s: courseRoster(courseToHandle)) {
                        System.out.println(s);
                    }
                    int studentToAverage = kybd.nextInt();
                    // use calculateStudentAverageInOneCourse,
                    // then print the following:
                    // student last name, student first name, student id, course department, course number, student average
                    double avg = calculateStudentAverageInOneCourse(courseToHandle,studentToAverage);
                    Student st = allStudents.get(studentToAverage);
                    String dispLine = "For "  + st.getLastName() + "," + st.getFirstName() + " with id " +
                            studentToAverage + " in " + courseToHandle.getDepartment() + "-"
                            + courseToHandle.getCourseNumber() + " has average " + avg + "\n";
                    System.out.println(dispLine);
                }

            }

            // 	"Display the average for an Assignment",
            else if (MENU[6].equals(userChoice)) {
                Course courseToHandle = whichCourse(kybd, courses);
                if (courseToHandle != null) {
                    System.out.println("Which assignment? ");
                    // retrieve all assignment names for the course,
                    // ask the user which assignment to process,
                    // use courseAverageForAssignment to calculate the average,
                    // then display it
                    List<String> assignmentNames = courseToHandle.getAllAssignmentNames();
                    String assignmentSelection = Utils.userChoose(kybd, assignmentNames);
                    double avg = courseAverageForAssignment(courseToHandle, assignmentSelection);


                    System.out.println("The course average for that lab is " + avg);
                }
            }

            // 	"Display a student's average in all courses",
            else if (MENU[7].equals(userChoice)) {
                System.out.println("What's the student's id? ");
                List<String> students = roster(allStudents.keySet());
                for (String s: students) {
                    System.out.println(s);
                }
                int studentId = kybd.nextInt();
                // Look up and display student last name, first name, id,
                // for each course the student is in, use calculateStudentAverageInOneCourse
                // to calculate the student's average.
                // Display the course department and number, with the student's average
                Student st = allStudents.get(studentId);
                System.out.println(st.getFirstName() + " " + st.getLastName() + " with ID " + studentId);
                for (Course course : courses) {
                    if(course.getRoster().containsKey(studentId)) {
                        double avg = Math.round(calculateStudentAverageInOneCourse(course, studentId));
                        System.out.println("In " + course.getDepartment() + "-" + course.getCourseNumber() + " has an " +
                                "average of " + avg);
                    }
                }
            }

            // 	"Display all students that are in all courses",
            else if (MENU[8].equals(userChoice)) {
                // use getStudentsTakingEverything to find all students taking every course.
                // print the first name, last name and id of each student.
                List<Integer> ids = getStudentsTakingEverything(courses);
                for(Integer id : ids) {
                    Student students = allStudents.get(id);
                    System.out.println(students.getFirstName() + " " + students.getLastName() + " with ID " + id);
                }

            }
        }
    }

    //
    /**
     * Determine which students are taking <em>all</em> of the Course list.
     *
     * @param courses to be examined for common students
     *
     * @return ids for students in every course in courses
     */
    public static List<Integer> getStudentsTakingEverything(List<Course> courses) {
        List<Integer> retList = courses.get(0).getAllStudentIds();
        for(int j = 1; j < courses.size(); j++) {
            Map<Integer, List<Assignment>> searchMap = courses.get(j).getRoster();
            for(int i = 0; i < retList.size(); i++) {
                if(!searchMap.containsKey(retList.get(i))) {
                    retList.remove(i);
                    i--;
                }
            }
        }
        return retList;
    }

    //
    /**
     * Average the Assignments of a given name in one course (for all students)
     *
     * @param courseToHandle the course whose assignments are to be averaged
     *
     * @param labName the name of the Assignment
     *
     * @return the average (in percent) of all labName assignments
     */
    public static double courseAverageForAssignment(Course courseToHandle, String labName) {
        int score = 0;
        int totalPoints = 0;
        double avg = 0;
        List<Integer> studentIds = courseToHandle.getAllStudentIds();
        for(Integer id : studentIds) {
            List<Assignment> assignList = courseToHandle.getAssignment(id, labName);
            for(Assignment assignment : assignList) {
                score += assignment.getScore();
                totalPoints += assignment.getPossiblePoints();
            }
        }
        avg = ((double)score)/totalPoints;
        double percentage = Math.round(avg * 100);

        return percentage;
    }

    //
    /**
     * Calculate a given student's grade in one course
     *
     * @param courseToHandle the student's course
     *
     * @param studentToAverage the id of the student whose score is to be calculated
     *
     * @return the student's average for all Assignments, in percent
     */
    public static double calculateStudentAverageInOneCourse(Course courseToHandle, int studentToAverage) {
        List<Assignment> assignmentList = courseToHandle.getAssignmentsForStudent(studentToAverage);
        double score = 0;
        double totalPoints = 0;
        double avg = 0;
        for(Assignment assignment : assignmentList) {
            score += assignment.getScore();
            totalPoints += assignment.getPossiblePoints();
        }
        avg = Math.round((score/totalPoints) * 100);

        return avg;
    }

    // TODO complete this method
    /**
     * For each student associated with a set of studentIds,
     * fetch and
     * sort the students, and assemble
     * their toString results.
     * Students are sorted first by last name, then first name and finally studentId
     * <br>
     * Hint:  use a
     * <em>
     * Comparator
     * </em> to specify the sort order
     *
     * @param studentKeys list of student ids to return
     *
     * @return list of Student toString values for each studentId in sorted order
     */
    public static List<String> roster(Set<Integer> studentKeys) {
        StudentComparator c = new StudentComparator();
        List<Student> studentList = new ArrayList<>();
        StudentBody studentBody = StudentBody.getInstance();
        for(Integer ids : studentKeys) {
           Student student = studentBody.get(ids);
            studentList.add(student);
        }
        studentList.sort(c);

        List<String> retVal = new ArrayList<>();
        for(Student student : studentList) {
            retVal.add(student.toString());
        }
        return retVal;
    }

    /**
     * List the students in a given Course sorted by last name, first name, studentId
     *
     * @param courseToHandle the Course whose roster we will return
     *
     * @return sorted list of students, sorted by lastname, then firstname, and finally studentid
     * (one entry per student)
     */
    public static List<String> courseRoster(Course courseToHandle) {
        Set<Integer> keys = courseToHandle.getRoster().keySet();
        return roster(keys);
    }

    /**
     * Construct a String describing a given student's labs for all courses.
     * The format of the returned String should be similar to the following example:
     * <br>
     * <em>
     * for Sophie Princess
     * <br>
     * in course CSC251
     * <br>
     * lab00 40 out of 50 for 80%
     * <br>
     * lab01 81 out of 90 for 90%
     * <br>
     * in course CSC258
     * <br>
     * labSayHello 100 of 100 for 100%
     * <br>
     * </em>
     * etc.
     *
     * @param studentId whose name, id number, courses and labs in each course is to be returned
     *
     * @param courses list to search for the given studentId
     *
     * @return String formatted as above
     */
    // TODO Complete this method
    public static String oneStudentResults(int studentId, List<Course> courses) {
        StudentBody body = StudentBody.getInstance();
        Student student = body.get(studentId);
        StringBuilder rv = new StringBuilder("for " + student.getFirstName() + " " + student.getLastName() + "\n");
        for( Course course : courses) {
            if(course.getRoster().containsKey(studentId)) {
                rv.append("in course ").append(course.getDepartment()).append("-").append(course.getCourseNumber()).append("\n");
                List<Assignment> assignments = course.getAssignmentsForStudent(studentId);
                for(Assignment assignment : assignments) {
                    rv.append(assignment.getName()).append(" ").append(assignment.getScore()).append(" out of ").append(assignment.getPossiblePoints()).append(" for ").append(assignment.getScorePercent()).append("\n");
                }
            }
        }
        return rv.toString();
    }

    /**
     * Create a new Assignment for each student in the given Course.
     * <br>
     * The application
     * <em>
     * "should"
     * </em> ask the user
     * <br>
     * "give me the grade for Sophie,
     * <br>
     * now give me the grade for Sally,
     * <br>
     * now give me the grade for Jack...."
     * <p>
     * That's really too much
     * pain and agony for our purposes, so we'll just assign a random grade for each student
     * as we create the Assignment objects.
     * The assigned grades will be 70% or higher
     * for each person.
     * </p>
     * <p>
     * Note that since assignments are
     * identified by name, multiple attempts at an assignment (i.e., two assignments with the
     * same name) are allowed.
     * </p>
     * @param courseToHandle - the Assignments will be added to this Course
     *
     * @param assignmentName - name for the new Assignment objects
     *
     * @param possiblePoints - maximum number of points for these Assignment objects
     */
    public static void createAssignment(Course courseToHandle, String assignmentName, int possiblePoints) {
        Random rand = new Random();
        Map<Integer, List<Assignment>> roster = courseToHandle.getRoster();
        int scoreMin = (int) (possiblePoints * .7);
        int scoreRange = possiblePoints - scoreMin;
        for (int studentId: roster.keySet()) {
            int studentScore = rand.nextInt(scoreRange) + scoreMin;
            Assignment lab = new Assignment(assignmentName, possiblePoints, studentScore);
            courseToHandle.addAssignment(studentId, lab);
        }

    }

    /**
     * Present the user with a list of the current courses and ask which he or she
     * wishes to work on next.
     *
     * @param input I/O reader to determine user choice
     *
     * @param courses the courses the user may select
     *
     * @return the user-selected course
     */
    public static Course whichCourse(Scanner input, List<Course> courses) {
        System.out.print("Which Course? ");
        String courseSelection = Utils.userChoose(input, courses);
        // System.out.println(courseSelection);
        if (!"".equals(courseSelection)) {
            String[] divided = courseSelection.split("=|,");
            /*
             * for (String s: divided) { System.out.println(s); }
             */
            String courseDept = divided[1];
            String courseNumber = divided[3];

            // System.out.println("debug: courseDept: " + courseDept);
            // System.out.println("debug: courseNumber: " + courseNumber);

            for (Course c : courses) {
                if (c.getDepartment().equals(courseDept) && c.getCourseNumber() == Integer.parseInt(courseNumber)) {
                    return c;
                }
            }
        }
        System.out.println("Invalid course chosen, using [0]: " + courses.get(0).getDepartment() + "-"
                + courses.get(0).getCourseNumber());
        return courses.get(0);
    }

    /**
     * Add up to the given number of students to a given course.
     * <br>
     * Note that since our Student generator can generate duplicate students,
     * and since a Course will gracefully ignore adding duplicate students,
     * less than the specified students may wind up being added to the course.
     *
     * @param c - Course for the new Students
     *
     * @param seats - The number of students to be randomly generated and added
     * to the course.  See above why the course might actually wind up with
     * fewer Students than this parameter specifies.
     */
    public static void populateCourse(Course c, int seats) {
        for (int i = 0; i < seats; i++) {
            Student student = StudentGenerator.genStudent();
            StudentBody sb = StudentBody.getInstance();
            sb.add(student);
            c.addStudent(student.getId());
        }

    }
// comparator class and compare method created to help with Comparator used in Roster()
public static class StudentComparator implements Comparator<Student> {
    public int compare(Student o1, Student o2) {
        if(o1.getLastName().compareToIgnoreCase(o2.getLastName()) > 0) {
            return 1;
        }
        else if (o1.getLastName().compareToIgnoreCase(o2.getLastName()) < 0) {
            return -1;
        }
        else  {
            if(o1.getFirstName().compareToIgnoreCase(o2.getFirstName()) > 0) {
                return 1;
            }
            else if(o1.getFirstName().compareToIgnoreCase(o2.getFirstName()) < 0) {
                return -1;
            }
            else  {
                if(o1.getId() > o2.getId()) {
                    return 1;
                }
                else if(o1.getId() < o2.getId()) {
                    return -1;
                }
                else {
                    return 0;
                }
            }
        }
    }
}
}


