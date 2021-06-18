package edu.waketech.common;

/**
 * A student at Wake Tech.  Students have a last name, first name and id number.
 * <br>
 * Students are naturally ordered by id number.
 * <br>
 * Note that students being ordered by id number implies that
 * <em>
 * only
 * </em>
 *  id number is used for equality comparisons.
 *
 * @author parks
 *
 */
public class Student implements Comparable<Student> {

    private String lastName;
    private String firstName;
    private int id;

    /**
     * Constructor of fully-specified student
     *
     * @param lastName student's last name
     * @param firstName student's first name
     * @param id wake tech id number
     */
    public Student(String lastName, String firstName, int id) {
        if ((id <= 0) || (lastName == null || "".equals(lastName)) || (firstName == null)) {
            if (id == 0) {
                throw new IllegalArgumentException("id must be greater than zero");
            }
            if (lastName == null || "".equals(lastName)) {
                throw new IllegalArgumentException("Last name must be supplied");
            }
            if (firstName == null) {
                throw new IllegalArgumentException("First name must not be null");
            }
        }
        this.lastName = lastName;
        this.firstName = firstName;
        this.id = id;
    }

    /**
     * Tests whether this student has the given id
     *
     * @param id String containing a number to be tested
     * @return true if Integer.parseInt(id) == this student's id.  False otherwise
     */
    public boolean equalsId(String id) {
        return equalsId(Integer.parseInt(id));
    }

    /**
     * Tests whether this student has the given id
     *
     *
     * @param id to be compared to this student's id
     * @return true if the given id equals this student's id, false otherwise
     */
    public boolean equalsId(int id) {
        return this.id == id;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Student o) {
        return this.id - o.getId();
    }

    // We haven't covered hashCode, yet
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    // @Override
    // public int hashCode() {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + id;
    // return result;
    // }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /**
     * Simple getter
     *
     * @return this student's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for last name.
     * <br>
     * If the parameter is null or the empty String, an IllegalArgumentException is thrown
     *
     * @param lastName of student
     */
    public void setLastName(String lastName) {
        if (lastName == null || "".equals(lastName)) {
            throw new IllegalArgumentException("Last name must be supplied");
        }
        this.lastName = lastName;
    }

    /**
     * Getter for first name of student
     *
     * @return first name of student
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for student's first name.
     * <br>
     * Note that if the parameter is null, an IllegalArgumentException is thrown
     *
     * @param firstName new first name for student
     */
    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("First name must not be null");
        }
        this.firstName = firstName;
    }

    /**
     * Simple getter for student id
     * @return student id number
     */
    public int getId() {
        return id;
    }

    /**
     *
     * String form of this student
     *
     * @return String of the form
     * <em>
     * "lastName=" + lastName + ", firstName=" + firstName + ", id=" + id
     * </em>
     */
    @Override
    public String toString() {
        return  lastName + ", " + firstName + " studentid=" + id;
    }

}
