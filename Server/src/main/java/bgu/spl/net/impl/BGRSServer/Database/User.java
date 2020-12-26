package bgu.spl.net.impl.BGRSServer.Database;

public abstract class User {
    private boolean loggedIn;
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        loggedIn = false;
    }

    public void LogIn(String password) throws Exception {
        if (loggedIn)
            throw new Exception("mis chang already logged in");
        if (!this.password.equals(password))
            throw new Exception("wrong password for user: " + userName);
        loggedIn = true;
    }

    public void Logout() throws Exception {
//        if (!loggedIn)
//            throw new Exception("the user: " + userName + " is already logged in");
        loggedIn = false;
    }

    public String getName() {
        return userName;
    }


    public abstract void registerCourse(Course course) throws Exception;

    public abstract void courseStats() throws Exception;

    public abstract String getCourses() throws Exception;

    public abstract void statCommand(User checkUser) throws Exception;

    public abstract String isRegistered(int courseID) throws Exception;

    public abstract void unRegisterCourse(Course course) throws Exception;
}
