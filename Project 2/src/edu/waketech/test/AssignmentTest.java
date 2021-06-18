package edu.waketech.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.waketech.academic.Assignment;
import edu.waketech.common.StudentBody;

class AssignmentTest {

    // add for grading
    @BeforeEach
    void testClearStudentBody() {
        StudentBody.getInstance().clear();
    }

    @Test
    void testGetScorePercent() {
        Assignment lab = new Assignment(new String("l"), 5, 4);
        int actual = lab.getScorePercent();
        assertEquals(80, actual);
    }
}
