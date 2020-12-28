package bgu.spl.net.impl.BGRSServer.Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {

    private final int DEFAULT_COURSE_ID_POSITION = 0;
    private final int DEFAULT_COURSE_NAME_POSITION = 1;
    private final int DEFAULT_COURSE_KDAMS_POSITION = 2;
    private final int DEFAULT_COURSE_SEATS_POSITION = 3;

    private HashMap<Integer, Integer> coursesOrder;
    private ConcurrentHashMap<String,User> users;
    private HashMap<Integer,Course> courses;

    //to prevent user from creating new Database
    private Database() {
        users = new ConcurrentHashMap<>();
        courses = new HashMap<>();
        coursesOrder = new HashMap<>();
    }
    public User getUser(String username) throws Exception {
        User user = users.get(username);
        if (user == null)
            throw new Exception("No such username");
        return user;
    }
    public int getCourseLineNumber (int courseID) {
        return coursesOrder.get(courseID);
    }
    public void createAdmin(String username, String password) throws Exception {
        User user = new Admin(username,password);
        if (users.putIfAbsent(username, user) != null)
            throw new Exception("The username: " + username + " already exists");
    }
    public void createStudent(String username, String password) throws Exception {
        User user = new Student(username,password);
        if (users.putIfAbsent(username, user) != null)
            throw new Exception("The username: " + username + " already exists");
    }

    public Course verifyValidCourse(int courseID) throws Exception {
        Course course = courses.get(courseID);
        if (course == null)
            throw new Exception("Course: " + courseID + " does not exist");
        return course;
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
    // REMOVE THE PUBLIC FROM THIS COMMAND
    public boolean initialize(String coursesFilePath) { // make sure database is initialized before client can connect to the server


        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(coursesFilePath));
        } catch (IOException e) {
            e.getStackTrace();
           return false;
        }
        List<Integer> coursesID = parseToCourses(lines);
        boolean success = (coursesID != null);
        if (success)
            sortKdams(coursesID);

        return success;
    }

    private void sortKdams(List<Integer> coursesID) {
        for (Integer courseID : coursesID) {
            Course course = courses.get(courseID);
            ArrayList<Integer> kdams = course.getKdams();
            kdams.sort(Comparator.comparingInt(o -> coursesOrder.get(o)));
            course.setKdams(kdams);
        }
    }

    private List<Integer> parseToCourses(List<String> lines) {
        List<Integer> coursesID = new ArrayList<>();
        int lineNumber = 1;
        for (String line : lines) {
            String[] courseInfo = line.split("\\|");
            try {
                Course course = createCourse(courseInfo, lineNumber);
                courses.put(course.getCourseId(), course);
                coursesID.add(course.getCourseId());
                lineNumber++;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return coursesID;
    }

    private Course createCourse(String[] courseInfo,int line) {
        // parse courseId
        int courseId = Integer.parseInt(courseInfo[DEFAULT_COURSE_ID_POSITION]);
        // parse courseName
        String courseName = courseInfo[DEFAULT_COURSE_NAME_POSITION];
        //parse kdams list
        String kdamsText = courseInfo[2].substring(1,courseInfo[DEFAULT_COURSE_KDAMS_POSITION].length()-1);
        String[] splitKdams = kdamsText.split(",");
        ArrayList<Integer> kdams = new ArrayList<>();
        for (String splitKdam : splitKdams) {
            if (!splitKdam.equals(""))
                kdams.add(Integer.parseInt(splitKdam));
        }
        //parse seats
        int seats = Integer.parseInt(courseInfo[DEFAULT_COURSE_SEATS_POSITION]);
        coursesOrder.put(courseId,line);
        return new Course(courseId,courseName,kdams,seats);
    }
}
