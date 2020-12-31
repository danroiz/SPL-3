package bgu.spl.net.impl.BGRSServer.Database;

import bgu.spl.net.impl.BGRSServer.Exceptions.CourseFullException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.RegisterException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UnRegisterException;

import java.util.*;

public class Student extends User {
    private TreeMap<Integer,Integer> sortedRegisteredCoursesTree; // Key: LineNumber, Value: CourseID
    private HashSet<Integer> registeredCoursesSet;


    public Student(String userName, String password) {
        super(userName, password);
        registeredCoursesSet = new HashSet<>();
        sortedRegisteredCoursesTree = new TreeMap<>();
    }

    @Override
    public void registerCourse(Course course) throws CourseFullException, RegisterException {
        verifyNotRegistered(course.getCourseId());
        checkKdmas(course.getKdams()); // check if the student have all the kdams for the course
        course.register(getName()); // add the student to the course students list
        addCourse(course.getCourseId()); // add the student to the course students list
    }

    @Override
    public void courseStats() throws NotAuthorizedException {
        InvalidCommand("Course Stat");
    }

    @Override
    public String getCourses() {
        return sortedRegisteredCoursesTree.values().toString();
    }

    @Override
    public void statCommand(User checkUser) throws NotAuthorizedException {
        InvalidCommand("Student Stats");
    }

    @Override
    public String isRegistered(int courseID) {
        return (registeredCoursesSet.contains(courseID)?"REGISTERED":"NOT REGISTERED");
    }

    @Override
    public void unRegisterCourse(Course course) throws UnRegisterException {
        removeCourse(course.getCourseId());
        course.unRegister(getName());
    }

    private void removeCourse(Integer courseId) throws UnRegisterException {
        if (!registeredCoursesSet.contains(courseId))
            throw new UnRegisterException("student + " + getName() + " is not registered to " + courseId);
        registeredCoursesSet.remove(courseId);
        sortedRegisteredCoursesTree.remove(courseId);
    }

    private void addCourse(int courseID) {
        registeredCoursesSet.add(courseID);
        int courseLineNumber = Database.getInstance().getCourseLineNumber(courseID);
        sortedRegisteredCoursesTree.put(courseLineNumber,courseID);
    }

    private void verifyNotRegistered(int courseID) throws RegisterException {
        if (registeredCoursesSet.contains(courseID)){
            throw new RegisterException("user " + super.getName() + " is already registered to course "+ courseID);
        }
    }

    private void checkKdmas(ArrayList<Integer> kdams) throws RegisterException {
        for (Integer kdam : kdams)
            if (!registeredCoursesSet.contains(kdam))
                throw new RegisterException("the student " + super.getName() + " is missing the kdam " + kdam);
    }

    private void InvalidCommand(String CommandType) throws NotAuthorizedException {
        throw new NotAuthorizedException("Student can not handle " + CommandType);
    }
}
