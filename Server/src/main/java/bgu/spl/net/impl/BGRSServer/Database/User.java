package bgu.spl.net.impl.BGRSServer.Database;
import bgu.spl.net.impl.BGRSServer.Exceptions.*;

public abstract class User {
    private boolean loggedIn;
    private final String userName;
    private final String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        loggedIn = false;
    }

    /**
     * Logging to the user
     * @param password - the password for the user
     * @throws UserLoginException: in case user already logged in or mismatch password
     */
    public synchronized void LogIn(String password) throws UserLoginException {
        if (loggedIn)
            throw new UserLoginException("miss chang already logged in");
        if (!this.password.equals(password))
            throw new UserLoginException("wrong password for user: " + userName);
        loggedIn = true;
    }

    /**
     * Logging out
     */
    public synchronized void Logout()  {
        loggedIn = false;
    }


    public String getName() {
        return userName;
    }

    /**
     * register @this User to Course course
     * @param course - the course to register to
     * @throws NotAuthorizedException -  user not authorized to register
     * @throws RegisterException - user already registered to the course
     * @throws CourseFullException - course doesn't have available seats
     */
    public abstract void registerCourse(Course course) throws NotAuthorizedException, RegisterException, CourseFullException;

    /**
     *  checks permission for COURSE STATS command
     * @throws NotAuthorizedException - user not authorized to check course stats
     */
    public abstract void courseStats() throws NotAuthorizedException;

    /**
     * @return all the courses the user is registered to
     * @throws NotAuthorizedException - user not authorized to get courses
     */
    public abstract String getCourses() throws NotAuthorizedException;

    /**
     * get all the stats of a user
     * @param checkUser the user to get stats from
     * @throws NotAuthorizedException - @this user not authorized to check user stats
     */
    public abstract void studentStats(User checkUser) throws NotAuthorizedException;

    /**
     * check if user is registered to course
     * @param courseID - the course to check if @this user registered to
     * @return - "REGISTERED" if true, else "NOT REGISTERED"
     * @throws NotAuthorizedException - @this user not authorized to check if registered to courseID
     */
    public abstract String isRegistered(int courseID) throws NotAuthorizedException;

    /**
     * un register from a course
     * @param course - the course to unregister from
     * @throws NotAuthorizedException - @this user not authorized to unregister from a course
     * @throws UnRegisterException - user is not registered in first place, or course id is not valid
     */
    public abstract void unRegisterCourse(Course course) throws NotAuthorizedException, UnRegisterException;

    /**
     * @param course - the course to get his kdams
     * @return - string representing all the course kdams
     * @throws NotAuthorizedException - @this user not authorized to check course kdams
     */
    public abstract String KdamCheck(Course course) throws NotAuthorizedException;
}
