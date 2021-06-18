package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.waketech.academic.Assignment;
import edu.waketech.academic.Course;
import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;
import edu.waketech.GradebookApp;

class GradebookAppTest {


    // add for grading
    @BeforeEach
    void testClearStudentBody() {
        StudentBody.getInstance().clear();
    }

    @Test
    void testCreateAssignment() {
        Course course = new Course(new String("CSC"), 251);
        Student s1 = new Student(new String("last1"), new String("first1"), 2);
        Student s2 = new Student(new String("last2"), new String("first2"), 3);
        StudentBody sb = StudentBody.getInstance();
        sb.add(s1);
        sb.add(s2);

        course.addStudent(s1.getId());
        course.addStudent(s2.getId());

        GradebookApp.createAssignment(course, new String("lab1"), 50);
        List<Assignment> labList = course.getAssignment(s1.getId(), new String("lab1"));
        Assignment foundLab = labList.get(0);
        assertEquals(new String("lab1"), foundLab.getName());
        assertEquals(50, foundLab.getPossiblePoints());
        assertTrue(foundLab.getScore() >= 35);
    }

    @Test
    void testCreateAssignment1() {
        Course course = new Course(new String("CSC"), 251);
        Student s1 = new Student(new String("last1"), new String("first1"), 2);
        Student s2 = new Student(new String("last2"), new String("first2"), 3);
        StudentBody sb = StudentBody.getInstance();
        sb.add(s1);
        sb.add(s2);

        course.addStudent(s1.getId());
        course.addStudent(s2.getId());

        GradebookApp.createAssignment(course, new String("lab1"), 50);
        List<Assignment> labList = course.getAssignment(s2.getId(), new String("lab1"));
        Assignment foundLab = labList.get(0);
        assertEquals(new String("lab1"), foundLab.getName());
        assertEquals(50, foundLab.getPossiblePoints());
        assertTrue(foundLab.getScore() >= 35);
    }

    @Test
    void testGetStudentsTakingEverything() {
        Course course1 = new Course(new String("CSC"), 251);
        Course course2 = new Course(new String("CSC"), 151);
        Course course3 = new Course(new String("DBA"), 120);

        Student s1 = new Student(new String("last1"), new String("first1"), 2);
        Student s2 = new Student(new String("last2"), new String("first2"), 3);
        Student s3 = new Student(new String("last3"), new String("first3"), 4);
        Student s4 = new Student(new String("last4"), new String("first4"), 5);

        StudentBody sb = StudentBody.getInstance();
        sb.add(s1);
        sb.add(s2);
        sb.add(s3);
        sb.add(s4);

        course1.addStudent(s1.getId());
        course1.addStudent(s2.getId());
        course1.addStudent(s3.getId());
        course1.addStudent(s4.getId());
        course2.addStudent(s1.getId());
        course2.addStudent(s2.getId());
        course2.addStudent(s4.getId());
        course3.addStudent(s2.getId());
        course3.addStudent(s1.getId());
        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        List<Integer> ids = GradebookApp.getStudentsTakingEverything(courses);
        assertTrue(ids.contains(s2.getId()));
    }

    @Test
    void testCourseAverageForAssignment() {
        Course course = new Course(new String("CSC"), 251);
        Student s1 = new Student(new String("last1"), new String("first1"), 2);
        Student s2 = new Student(new String("last2"), new String("first2"), 3);
        Student s3 = new Student(new String("last3"), new String("first3"), 4);
        StudentBody sb = StudentBody.getInstance();
        sb.add(s1);
        sb.add(s2);
        sb.add(s3);

        course.addStudent(s1.getId());
        course.addStudent(s2.getId());
        course.addStudent(s3.getId());
        //Since we need to check the average, GradebookApp's createAssignment can't be used
        // as it will randomize the points earned. instead, Course's add assignment will be used,
        // with 1 assignment being made for each student.
        Assignment assign1 = new Assignment("lab1", 100,80);
        Assignment assign2 = new Assignment("lab1", 100, 95);
        Assignment assign3 = new Assignment("lab1", 100, 60);
        double expavg = (double)(80 + 95 + 60)/(300);
        double expPercent = Math.round(expavg * 100);
        course.addAssignment(s1.getId(), assign1);
        course.addAssignment(s2.getId(), assign2);
        course.addAssignment(s3.getId(), assign3);
        double avgPercent = GradebookApp.courseAverageForAssignment(course,"lab1");
        assertEquals(expPercent, avgPercent);
    }

    @Test
    void testStudentAverageInOneCourse() {
        Course course = new Course(new String("CSC"), 251);
        Student s1 = new Student(new String("last1"), new String("first1"), 2);
        StudentBody sb = StudentBody.getInstance();
        sb.add(s1);
        course.addStudent(s1.getId());
        Assignment assign1 = new Assignment("lab1", 85,80);
        Assignment assign2 = new Assignment("lab2", 100, 95);
        Assignment assign3 = new Assignment("lab3", 75, 60);
        double expavg = (double)(80 + 95 + 60)/(85+100+75);
        double expPercent = Math.round(expavg * 100);
        course.addAssignment(s1.getId(), assign1);
        course.addAssignment(s1.getId(), assign2);
        course.addAssignment(s1.getId(), assign3);
        double avgPercent = GradebookApp.calculateStudentAverageInOneCourse(course,s1.getId());
        assertEquals(expPercent, avgPercent);
    }

}