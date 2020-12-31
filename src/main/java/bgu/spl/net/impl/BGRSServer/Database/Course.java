package bgu.spl.net.impl.BGRSServer.Database;
import bgu.spl.net.impl.BGRSServer.Exceptions.CourseFullException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class Course {
    private String courseName;
    private int courseId;
    private ArrayList<Integer> kdams;
    private int seats;
    private TreeSet<String> registeredUsers;

    public Course(int courseId, String courseName, ArrayList<Integer> kdams, int seats){
        this.courseId = courseId;
        this.courseName = courseName;
        this.kdams = kdams;
        this.seats = seats;
        registeredUsers = new TreeSet<>();
    }

    public void setKdams(ArrayList<Integer> kdams) {
        this.kdams = kdams;
    }

    public ArrayList<Integer> getKdams () {
        return kdams;
    }

    public String getRegisteredUsers () {
        return registeredUsers.toString();
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

    public synchronized void register(String studentName) throws CourseFullException {
        if (registeredUsers.size() == seats){
            throw new CourseFullException("No available seats at this course. try next semester. course: " + courseName);
        }
        registeredUsers.add(studentName);
    }

    public String getStats() {
        return getCourseName() + "\n" + getFreeSeats() +"\n"+ getRegisteredUsers();
    }

    public synchronized void unRegister(String username) {
        registeredUsers.remove(username);
    }
}
