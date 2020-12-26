package bgu.spl.net.impl.BGRSServer.Database;

public class Admin extends User {
    public Admin(String userName, String password) {
        super(userName, password);
    }




    @Override
    public void registerCourse(Course course) throws Exception {
        invalidCommand("Course Register");
    }

    @Override
    public void courseStats() throws Exception {
    }

    @Override
    public String getCourses() throws Exception {
        invalidCommand("Admin does not have stats");
        return null;
    }

    @Override
    public void statCommand(User checkUser) { }

    @Override
    public String isRegistered(int courseID) throws Exception {
        invalidCommand("Is Registered");
        return null;
    }

    @Override
    public void unRegisterCourse(Course course) throws Exception {
        invalidCommand("UNREGISTER");
    }

    private void invalidCommand(String CommandType) throws Exception {
        throw new Exception("Admin can not handle " + CommandType);
    }
}