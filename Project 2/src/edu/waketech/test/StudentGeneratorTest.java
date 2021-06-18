package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.waketech.common.Student;
import edu.waketech.common.StudentBody;

class StudentGeneratorTest {


    // add for grading
    @BeforeEach
    void testClearStudentBody() {
        StudentBody.getInstance().clear();
    }

    @Test
    void testGenStudent() {
        int lo = 100;
        int hi = 200;
        boolean foundLo = false;
        boolean foundHi = false;
        for (int i = 0; i < 10000; i++) {
            Student s = StudentGenerator.genStudent();
            String f = s.getFirstName();
            String l = s.getLastName();
            int id = s.getId();
            if (id == lo)
                foundLo = true;
            if (id == hi)
                foundHi = true;
            assertTrue(id >= lo && id <= hi, "out of range: " + id);
        }
        assertTrue(foundLo, "Did not find low value");
        assertTrue(foundHi, "Did not find high value");
    }




}
