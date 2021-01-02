package bgu.spl.net.impl.BGRSServer.Database;
import bgu.spl.net.impl.BGRSServer.Exceptions.CourseFullException;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;

public class Course {
    private String courseName;
    private int courseId;
    private ArrayList<Integer> kdams;
    private int seats;
    private ConcurrentSkipListSet<String> registeredUsers;

    public Course(int courseId, String courseName, ArrayList<Integer> kdams, int seats){
        this.courseId = courseId;
        this.courseName = courseName;
        this.kdams = kdams;
        this.seats = seats;
        registeredUsers = new ConcurrentSkipListSet<>();
    }

    public void setKdams(ArrayList<Integer> kdams) {
        this.kdams = kdams;
    }

    public ArrayList<Integer> getKdams () {
        return kdams;
    }

    // consider to be synco
    public String getRegisteredUsers () {
        return registeredUsers.toString().replaceAll("\\s", "");
    }

    // consider to be synco
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

    public synchronized void register(String studentName) throws CourseFullException {
        if (registeredUsers.size() == seats){
            throw new CourseFullException("No available seats at this course. try next semester. course: " + courseName);
        }
        registeredUsers.add(studentName);
    }

    public String getStats() {
        return "Course: (" + courseId + ") " + getCourseName() + "\n" + "Seats Available: " + getFreeSeats() + "/" + getTotalSeats() +"\n"+ "Students Registered: " + getRegisteredUsers();
    }

    public synchronized void unRegister(String username) {
        registeredUsers.remove(username);
    }
}
