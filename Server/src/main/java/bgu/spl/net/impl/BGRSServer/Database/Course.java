package bgu.spl.net.impl.BGRSServer.Database;
import java.util.ArrayList;

public class Course {
    private String courseName;
    private int courseId;
    private int[] kdams;
    private int seats;
    private ArrayList<String> registeredUsers;

    public Course(int courseId, String courseName, int[] kdams, int seats){
        this.courseId = courseId;
        this.courseName = courseName;
        this.kdams = kdams;
        this.seats = seats;
        registeredUsers = new ArrayList<>();
    }


    public Integer getCourseId() {
        return courseId;
    }
}
