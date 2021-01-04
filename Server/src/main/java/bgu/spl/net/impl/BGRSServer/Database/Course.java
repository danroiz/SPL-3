package bgu.spl.net.impl.BGRSServer.Database;
import bgu.spl.net.impl.BGRSServer.Exceptions.CourseFullException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

public class Course {
    private final String courseName;
    private final int courseId;
    private ArrayList<Integer> kdams;
    private final int seats;
    private final ConcurrentSkipListSet<String> registeredUsers;

    /**
     *Constructor.
     */
    public Course(int courseId, String courseName, ArrayList<Integer> kdams, int seats){
        this.courseId = courseId;
        this.courseName = courseName;
        this.kdams = kdams;
        this.seats = seats;
        registeredUsers = new ConcurrentSkipListSet<>();
    }

    /**
     * Register the studentName to this course
     * @param studentName
     * @throws CourseFullException
     */
    public synchronized void register(String studentName) throws CourseFullException {
        if (registeredUsers.size() == seats){
            throw new CourseFullException("No available seats at this course. try next semester. course: " + courseName);
        }
        registeredUsers.add(studentName);
    }

    /**
     * Unregister studentName from this course
     * @param studentName
     */
    public synchronized void unRegister(String studentName) {
        registeredUsers.remove(studentName);
    }

    /**
     * Setting the kdams of this course to be:
     * @param kdams
     */
    public void setKdams(ArrayList<Integer> kdams) {
        this.kdams = kdams;
    }

    /**
     * Getters
     */
    public ArrayList<Integer> getKdams () {
        return kdams;
    }
    public String getRegisteredUsers () {
        return registeredUsers.toString().replaceAll("\\s", "");
    }
    public int getFreeSeats () {
        return seats - registeredUsers.size();
    }
    public int getTotalSeats () {
        return seats;
    }
    public Integer getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }
        public String getStats() {
        return "Course: (" + courseId + ") " + getCourseName() + "\n" + "Seats Available: " + getFreeSeats() + "/" + getTotalSeats() +"\n"+ "Students Registered: " + getRegisteredUsers();
    }
}
