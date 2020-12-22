package bgu.spl.net.impl.BGRSServer.Database;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {

    private HashMap<String,User> users;
    private HashMap<Integer,Course> courses;

    //to prevent user from creating new Database
    private Database() {
        users = new HashMap<>();
        courses = new HashMap<>();
    }

    private static class DatabaseHolder {
        private static Database instance = new Database();
    }

    /**
     * Retrieves the single instance of this class.
     */
    public static Database getInstance() {
        return DatabaseHolder.instance;
    }

    /**
     * loades the courses from the file path specified
     * into the Database, returns true if successful.
     */
    boolean initialize(String coursesFilePath) { // make sure database is initialized before client can connect to the server

        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(coursesFilePath));
        } catch (IOException e) {
           return false;
        }

        for (String line : lines) {
            String[] courseInfo = line.split("\\|");
            try {
                Course course = createCourse(courseInfo);
                // sort the kdams for each course by order of appear of the text
                courses.put(course.getCourseId(), course);
            } catch (Exception e) {
               return false;
            }

        }

        return true;
    }

    private Course createCourse(String[] courseInfo) {
        // parse courseId
        int courseId = Integer.parseInt(courseInfo[0]);
        // parse courseName
        String courseName = courseInfo[1];
        //parse kdams list
        String kdamsText = courseInfo[2].substring(1,courseInfo[2].length()-1);
        String[] splitKdams = kdamsText.split(",");
        int[] kdams = new int[splitKdams.length];
        for (int i = 0; i < splitKdams.length; i++){
            kdams[i] = Integer.parseInt(splitKdams[i]);
        }
        //parse seats
        int seats = Integer.parseInt(courseInfo[3]);

        return new Course(courseId,courseName,kdams,seats);
    }


}
