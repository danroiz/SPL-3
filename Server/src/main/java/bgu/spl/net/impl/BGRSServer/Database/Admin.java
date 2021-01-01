package bgu.spl.net.impl.BGRSServer.Database;

import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;

public class Admin extends User {
    public Admin(String userName, String password) {
        super(userName, password);
    }

    @Override
    public void registerCourse(Course course) throws NotAuthorizedException {
        invalidCommand("Course Register");
    }

    @Override
    public void courseStats(){

    }

    @Override
    public String getCourses() throws NotAuthorizedException {
        invalidCommand("Admin does not have stats");
        return null;
    }

    @Override
    public void statCommand(User checkUser) { }

    @Override
    public String isRegistered(int courseID) throws NotAuthorizedException {
        invalidCommand("Is Registered");
        return null;
    }

    @Override
    public void unRegisterCourse(Course course) throws NotAuthorizedException {
        invalidCommand("UNREGISTER");
    }

    private void invalidCommand(String CommandType) throws NotAuthorizedException {
        throw new NotAuthorizedException("Admin can not handle " + CommandType);
    }
}