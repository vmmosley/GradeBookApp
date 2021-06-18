package edu.waketech.common;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Singleton that stores all of the students in the college.
 *
 * @author parks
 *
 */
public class StudentBody {

    private static StudentBody me = new StudentBody();
    private Map<Integer, Student> students = new HashMap<>();

    public void clear() {
        students = new HashMap<>();
    }

    private StudentBody() {}

    /**
     * StudentBody.getInstance() is how a client acquires the (one and only)
     * StudentBody instance.
     *
     * @return the one and only StudentBody object.
     */
    public static StudentBody getInstance() {
        return me;
    }

    /**
     * Add a student to the student body
     *
     * @param student to be added to the store
     *
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public Student add(Student student) {
        return students.put(student.getId(), student);
    }

    /**
     * Fetch a Student from the store based on student id
     *
     * @param studentId student id of the Student to be fetched
     *
     * @return Student object whose id is studentId, or null if no such student object exists.
     */
    public Student get(int studentId) {
        return students.get(studentId);
    }

    /**
     * A set of studentId keys for all of the students in the store.
     *
     * @return all keys in the StudentBody
     */
    public Set<Integer> keySet() {
        return students.keySet();
    }

    /**
     * All values (student objects) in the StudentBody
     *
     * @return Collection of Student objects in the store.
     */
    public Collection<Student> values() {
        return students.values();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StudentBody [students=" + students + "]";
    }

}
