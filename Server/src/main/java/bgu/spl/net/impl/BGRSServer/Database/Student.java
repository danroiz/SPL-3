package bgu.spl.net.impl.BGRSServer.Database;

import java.util.*;

public class Student extends User {
    private TreeMap<Integer,Integer> sortedRegisteredCoursesTree; // Key: LineNumber, Value: CourseID
    private HashSet<Integer> registeredCoursesSet;


    public Student(String userName, String password) {
        super(userName, password);
        registeredCoursesSet = new HashSet<>();
        sortedRegisteredCoursesTree = new TreeMap<Integer,Integer>();
    }

    @Override
    public void registerCourse(Course course) throws Exception {
        verifyNotRegistered(course.getCourseId());
        checkKdmas(course.getKdams()); // check if the student have all the kdams for the course
        course.register(getName()); // add the student to the course students list
        addCourse(course.getCourseId()); // add the student to the course students list
    }

    @Override
    public void courseStats() throws Exception {
        InvalidCommand("Course Stat");
    }

    @Override
    public String getCourses() {
        return sortedRegisteredCoursesTree.values().toString();
    }

    @Override
    public void statCommand(User checkUser) throws Exception {
        InvalidCommand("Student Stats");
    }

    @Override
    public String isRegistered(int courseID) {
        return (registeredCoursesSet.contains(courseID)?"REGISTERED":"NOT REGISTERED");
    }

    @Override
    public void unRegisterCourse(Course course) throws Exception {
        removeCourse(course.getCourseId());
        course.unRegister(getName());
    }

    private void removeCourse(Integer courseId) throws Exception {
        if (!registeredCoursesSet.contains(courseId))
            throw new Exception("student + " + getName() + " is not registered to " + courseId);
        registeredCoursesSet.remove(courseId);
        sortedRegisteredCoursesTree.remove(courseId);
    }

    private void addCourse(int courseID) {
        registeredCoursesSet.add(courseID);
        int courseLineNumber = Database.getInstance().getCourseLineNumber(courseID);
        sortedRegisteredCoursesTree.put(courseLineNumber,courseID);
    }
    private void verifyNotRegistered(int courseID) throws Exception {
        if (registeredCoursesSet.contains(courseID)){
            throw new Exception("user " + super.getName() + " is already registered to course "+ courseID);
        }
    }


    private void checkKdmas(ArrayList<Integer> kdams) throws Exception {
        for (Integer kdam : kdams)
            if (!registeredCoursesSet.contains(kdam))
                throw new Exception("the student " + super.getName() + " is missing the kdam " + kdam);
    }
    private void InvalidCommand(String CommandType) throws Exception {
        throw new Exception("Admin can not handle " + CommandType);
    }
}
