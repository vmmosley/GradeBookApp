package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;

class StudentBodyTest {


    // add for grading
    @BeforeEach
    void testClearStudentBody() {
        StudentBody.getInstance().clear();
    }
    //
    @Test
    void testGetInstance() {
        Student soph = new Student(new String("the first"), new String("Sophie"), 11);
        Student sally = new Student(new String("Walmars"), new String("Sally"), 14);
        Student jack = new Student("longhair", new String("Jack"), 20);

        StudentBody sb1 = StudentBody.getInstance();
        StudentBody sb2 = StudentBody.getInstance();

        assertTrue(sb1 == sb2, "different instances of StudentBody exist");
    }

    @Test
    void testGet() {
        Student soph = new Student(new String("the first"), new String("Sophie"), 11);
        Student sally = new Student(new String("Walmars"), new String("Sally"), 14);
        Student jack = new Student("longhair", new String("Jack"), 20);

        StudentBody sb1 = StudentBody.getInstance();

        Student addedSoph = sb1.add(soph);
        Student addedSally = sb1.add(sally);
        Student addedJack = sb1.add(jack);

        Student fetchSally = sb1.get(14);

        assertNull(addedSally, "incorrect obj returned from add ");
        assertEquals(sally, fetchSally, "incorrect obj fetched ");
    }

    @Test
    void testGet1() {
        Student soph = new Student(new String("the first"), new String("Sophie"), 11);
        Student sally = new Student(new String("Walmars"), new String("Sally"), 14);
        Student jack = new Student("longhair", new String("Jack"), 20);

        StudentBody sb1 = StudentBody.getInstance();

        Student addedSoph = sb1.add(soph);
        Student addedSally = sb1.add(sally);
        Student addedJack = sb1.add(jack);
        sally.setFirstName("Champion Sally");
        addedSally = sb1.add(sally);

        assertEquals(sally, addedSally, "incorrect obj returned from add ");
    }

    @Test
    void testKeySet() {
        Student soph = new Student(new String("the first"), new String("Sophie"), 11);
        Student sally = new Student(new String("Walmars"), new String("Sally"), 14);
        Student jack = new Student("longhair", new String("Jack"), 20);

        StudentBody sb1 = StudentBody.getInstance();

        Student addedSoph = sb1.add(soph);
        Student addedSally = sb1.add(sally);
        Student addedJack = sb1.add(jack);

        var keys = sb1.keySet();

        assertTrue(keys.contains(11));
        assertTrue(keys.contains(14));
        assertTrue(keys.contains(20));
        assertEquals(3, keys.size(), "keyset wrong size: " + keys.toString());
    }

    @Test
    void testValues() {
        Student soph = new Student(new String("the first"), new String("Sophie"), 11);
        Student sally = new Student(new String("Walmars"), new String("Sally"), 14);
        Student jack = new Student(new String("longhair"), new String("Jack"), 20);

        StudentBody sb1 = StudentBody.getInstance();

        Student addedSoph = sb1.add(soph);
        Student addedSally = sb1.add(sally);
        Student addedJack = sb1.add(jack);

        var vals = sb1.values();

        assertTrue(vals.contains(soph));
        assertTrue(vals.contains(sally));
        assertTrue(vals.contains(jack));
        assertEquals(3, vals.size(), "vals wrong size " + vals.toString());


    }

}

