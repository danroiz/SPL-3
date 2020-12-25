package bgu.spl.net.impl.BGRSServer.Database;

public class Admin extends User {
    public Admin(String userName, String password) {
        super(userName, password);
    }




    @Override
    public void registerCourse(Course course) throws Exception {
        InvalidCommand("Course Register");
    }

    @Override
    public void CourseStats() throws Exception {
    }

    private void InvalidCommand(String CommandType) throws Exception {
        throw new Exception("Admin can not handle " + CommandType);
    }
}