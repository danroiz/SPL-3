package bgu.spl.net.impl.BGRSServer.Database;

import bgu.spl.net.impl.BGRSServer.Exceptions.*;

import javax.security.auth.login.LoginException;

public abstract class User {
    private boolean loggedIn;
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        loggedIn = false;
    }

    public void LogIn(String password) throws UserLoginException {
        if (loggedIn)
            throw new UserLoginException("miss chang already logged in");
        if (!this.password.equals(password))
            throw new UserLoginException("wrong password for user: " + userName);
        loggedIn = true;
    }

    public void Logout()  {
        // in comment cause already checking in the command
//        if (!loggedIn)
//            throw new NotLoggedException("no user is logged in");
        loggedIn = false;
    }

    public String getName() {
        return userName;
    }


    public abstract void registerCourse(Course course) throws NotAuthorizedException, RegisterException, CourseFullException;

    public abstract void courseStats() throws NotAuthorizedException;

    public abstract String getCourses() throws NotAuthorizedException;

    public abstract void statCommand(User checkUser) throws NotAuthorizedException;

    public abstract String isRegistered(int courseID) throws NotAuthorizedException;

    public abstract void unRegisterCourse(Course course) throws NotAuthorizedException, UnRegisterException;
}
