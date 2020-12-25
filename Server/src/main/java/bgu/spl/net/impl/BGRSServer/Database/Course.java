package bgu.spl.net.impl.BGRSServer.Database;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private int courseId;
    private ArrayList<Integer> kdams;
    private int seats;
    private ArrayList<String> registeredUsers;

    public Course(int courseId, String courseName, ArrayList<Integer> kdams, int seats){
        this.courseId = courseId;
        this.courseName = courseName;
        this.kdams = kdams;
        this.seats = seats;
        registeredUsers = new ArrayList<>();
    }

    public void setKdams(ArrayList<Integer> kdams) {
        this.kdams = kdams;
    }
    public ArrayList<Integer> getKdams () {
        return kdams;
    }
    public ArrayList<String> getRegisteredUsers () {
        return registeredUsers;
    }
    public int getFreeSeats () {
        return seats - registeredUsers.size();
    }

    public Integer getCourseId() {
        return courseId;
    }
    public String getCourseName() {
        return courseName;
    }

    public synchronized void register(String studentName) throws Exception {
        if (registeredUsers.size() == seats){
            throw new Exception("No available seats at this course. try next semester. course: " + courseName);
        }
        registeredUsers.add(studentName);
    }

    public String getStats() {
        return courseName + " " + getFreeSeats() + registeredUsers;
    }
}
