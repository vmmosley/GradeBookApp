package edu.waketech.academic;


/**
 * An assignment (such as a lab, test, flogging, etc) for a particular student.
 * <p>
 * An assignment consists of a name, the total possible points
 * of the assignment, and the score the associated student achieved on this assignment
 * </p>
 * <p>
 * Assignments are associated with a particular student implicitly.  See Course.
 * </p>
 *
 *
 *
 * @author parks
 *
 */
public class Assignment {

    private String name;
    private int possiblePoints;
    private int score;

    /**
     * Constructor with assignment name, the total number of points possible,
     * and the actual number of points earned.
     *
     * Note that if name is null or the empty String, an IllegalArgumentException is thrown.
     * If possiblePoints is negative, an IllegalArgumentException is thrown.
     *
     * @param name of the assignment.  A null value or empty name will cause an IllegalArgumentException to be thrown.
     * @param possiblePoints the maximum points available for the assignment.  Must be non-negative.
     * A negative value will cause an IllegalArgumentException to be thrown.
     * @param score the student's actual score on this assignment
     */
    public Assignment(String name, int possiblePoints, int score) {
        if (name == null || "".equals(name) || possiblePoints < 0) {
            throw new IllegalArgumentException("Must have valid name and non-negative points");
        }
        this.name = name;
        this.possiblePoints = possiblePoints;
        this.score = score;
    }

    /**
     * Test if this Assignment has the given name
     *
     * @param name the name to be tested
     *
     * @return true if this Assignment's name equals the parameter, false otherwise.
     */
    public boolean isNamed(String name) {
        return this.name.equals(name);
    }

    /**
     * The score expressed as a percentage of the possiblePoints.  The percentage
     * is rounded.
     *
     * @return percentage of score divided by possiblePoints.
     */
    public int getScorePercent() {
        double s = ((double) score) / possiblePoints;
        return (int) (Math.round(s * 100));
    }

    /**
     *
     * Simple getter for assignment name
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Simple setter for the assignment name
     *
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Must have valid name");
        }
        this.name = name;
    }

    /**
     * Simple getter for the possible points of this assignment.
     *
     * @return the possiblePoints
     */
    public int getPossiblePoints() {
        return possiblePoints;
    }

    /**
     * Setter for the possible points of this assignment.
     *
     * Assignments must have non-negative possible points.  A negative value
     * for the parameter will cause an IllegalArgumentException to be thrown.
     *
     * @param possiblePoints
     *            the possiblePoints to set, must be &gt;= 0
     */
    public void setPossiblePoints(int possiblePoints) {
        if (possiblePoints < 0) {
            throw new IllegalArgumentException("Must have non-negative points");
        }
        this.possiblePoints = possiblePoints;
    }

    /**
     * The score achieved by the associated student on this assignment
     *
     * @return the Assignment's score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * Set the score achieved by the associated student for this assignment
     *
     * @param score set the Assignment's score
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Assignment [name=" + name + ", possiblePoints=" + possiblePoints + ", score=" + score + "]";
    }

    // haven't done hashCode
    // @Override
    // public int hashCode() {
    // final int prime = 31;
    // int result = 1;
    // result = prime * result + ((name == null) ? 0 : name.hashCode());
    // return result;
    // }

    /**
     * Test equality of this object and another.
     * <br>
     * Equality is based only on Assignment name.
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Assignment other = (Assignment) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

}

