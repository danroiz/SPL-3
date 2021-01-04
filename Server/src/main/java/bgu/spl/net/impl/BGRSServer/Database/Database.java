package bgu.spl.net.impl.BGRSServer.Database;
import bgu.spl.net.impl.BGRSServer.Exceptions.AmbiguousUsernameException;
import bgu.spl.net.impl.BGRSServer.Exceptions.InvalidCourseException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UserNotExistException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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

    private static final int DEFAULT_COURSE_ID_POSITION = 0;
    private static final int DEFAULT_COURSE_NAME_POSITION = 1;
    private static final int DEFAULT_COURSE_KDAMS_POSITION = 2;
    private static final int DEFAULT_COURSE_SEATS_POSITION = 3;
    private static final String DEFAULT_COURSES_PATH = "./Courses.txt";

    private final ConcurrentHashMap<String,User> users;
    private final HashMap<Integer,Course> courses;
    private final HashMap<Integer, Integer> coursesOrder; // (courseID,lineNumber)

    /**
     * Private Constructor
     */
    private Database() {
        users = new ConcurrentHashMap<>();
        courses = new HashMap<>();
        coursesOrder = new HashMap<>();
        initialize(DEFAULT_COURSES_PATH);
    }

    /**
     * Thread safe singleton
     */
    private static class DatabaseHolder {
        private static final Database instance = new Database();
    }

    /**
     * Retrieves the single instance of this class.
     */
    public static Database getInstance() {
        return DatabaseHolder.instance;
    }

    /**
     * Creating new admin with 'username' and 'password'
     * @param username = username
     * @param password = password
     * @throws AmbiguousUsernameException if this username is already in use
     */

    public void createAdmin(String username, String password) throws AmbiguousUsernameException {
        User user = new Admin(username,password);
        if (users.putIfAbsent(username, user) != null)
            throw new AmbiguousUsernameException("The username: " + username + " already exists");
    }

    /**
     * Creating new student with 'username' and 'password'
     * @param username = username
     * @param password = password
     * @throws AmbiguousUsernameException if this username is already in use
     */

    public void createStudent(String username, String password) throws AmbiguousUsernameException {
        User user = new Student(username,password);
        if (users.putIfAbsent(username, user) != null)
            throw new AmbiguousUsernameException("The username: " + username + " already exists");
    }

    /**
     * Checking if the courseID is a valid course
     * @param courseID = course ID
     * @return course Object matching the courseID
     * @throws InvalidCourseException if the courseID doesn't exist
     */

    public Course verifyValidCourse(int courseID) throws InvalidCourseException {
        Course course = courses.get(courseID);
        if (course == null)
            throw new InvalidCourseException("Course: " + courseID + " does not exist");
        return course;
    }

    /**
     * loades the courses from the file path specified
     * into the Database, returns true if successful.
     */
    boolean initialize(String coursesFilePath) {
        List<String> lines; // parsing the Courses.txt file
        try {
            lines = Files.readAllLines(Paths.get(coursesFilePath));
        } catch (IOException e) {
            e.getStackTrace();
            return false;
        }
        List<Integer> coursesIDs = parseToCourses(lines);
        boolean success = (coursesIDs != null); // checking if the parsing was successful
        if (success)
            sortKdams(coursesIDs);
        return success;
    }

    private void sortKdams(List<Integer> coursesIDs) { // sorting the kdams according to their appearance in the courses.txt file
        for (Integer courseID : coursesIDs) {
            Course course = courses.get(courseID);
            ArrayList<Integer> kdams = course.getKdams();
            kdams.sort(Comparator.comparingInt(coursesOrder::get));
            course.setKdams(kdams);
        }
    }

    private List<Integer> parseToCourses(List<String> lines) { // Creating all the courses according to the lines list
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

    private Course createCourse(String[] courseInfo,int line) { // creating a new course with a given course parameters from the Course.txt file
        int courseId = Integer.parseInt(courseInfo[DEFAULT_COURSE_ID_POSITION]); // parse courseId
        String courseName = courseInfo[DEFAULT_COURSE_NAME_POSITION]; // parse courseName
        String kdamsText = courseInfo[2].substring(1,courseInfo[DEFAULT_COURSE_KDAMS_POSITION].length()-1); //parse kdams list
        String[] splitKdams = kdamsText.split(",");
        ArrayList<Integer> kdams = new ArrayList<>();
        for (String splitKdam : splitKdams) { // initializing the kdams of the course
            if (!splitKdam.equals(""))
                kdams.add(Integer.parseInt(splitKdam));
        }
        int seats = Integer.parseInt(courseInfo[DEFAULT_COURSE_SEATS_POSITION]); //parse seats
        coursesOrder.put(courseId,line);
        return new Course(courseId,courseName,kdams,seats);
    }


    /**
     *
     * @param username = required username
     * @return User object matching the username
     * @throws UserNotExistException if the user doesn't exist
     */
    public User getUser(String username) throws UserNotExistException {
        User user = users.get(username);
        if (user == null)
            throw new UserNotExistException("No such username");
        return user;
    }

    /**
     * Returns the line number of the courseID in the Courses.txt file
     * @param courseID = course ID
     * @return line number from Courses.txt
     */
    public int getCourseLineNumber (int courseID) {
        return coursesOrder.get(courseID);
    }
}
