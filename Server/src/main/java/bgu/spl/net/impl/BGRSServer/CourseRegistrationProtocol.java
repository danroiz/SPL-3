package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.Command;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;

import java.util.stream.Collectors;

public class CourseRegistrationProtocol implements MessagingProtocol<String> {
    private boolean shouldTerminate = false;
    private User user;
    // map - for each op code - there is a command

    @Override
    public String process(String msg) {
        int opCode = Integer.parseInt(msg.substring(0,msg.indexOf(' ')));
//        Command command = codes.get(opCode);
//
//        if ((opCode >= 4) && (user.loggedInd))
//            return command.execute(msg.split(" "));
//        return command.execute(msg.substring(msg.indexOf(' ')));
        // according to the op code of the msg, get the right command for it. do command.exectute.
        //
        // command.execute(rest of the message)
        return "miss chang";
    }

    public String Login(String username,String password) {
        try{
            if (user != null)
                throw new Exception("someone already logged in to user: " + user.getName());
            User tempUser = Database.getInstance().getUser(username);
            tempUser.LogIn(password);
            user = tempUser;
            return "12 "+"3";
        }
        catch (Exception e){
            e.printStackTrace();
            return "13 " + "3";
        }
    }

    public String AdminReg(String username, String password) {
        try {
            Database.getInstance().createAdmin(username, password);
            return "12 " + "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "13 " + "1";
        }
    }

    public String StudentReg(String username, String password) {
        try {
            Database.getInstance().createStudent(username, password);
            return "12 " + "2";
        } catch (Exception e) {
            e.printStackTrace();
            return "13 " + "2";
        }
    }

    public String Logout(){

        try{
            checkLoggedIn();
            user.Logout();
            user = null;
            return "12" + "4";
        }
        catch (Exception e){
            return "13 " + "4";
        }
    }

    public String CourseRegister(int courseID){
        // check if logged in
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            user.registerCourse(course);
            return "12" + "5";

        }
        catch (Exception e){
            return "13" + "5";
        }
    }

    public String kdamCheck(int courseID){
        // check if logged in
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            String listString = course.getKdams().stream().map(Object::toString)
                    .collect(Collectors.joining(", "));
            return "12" + "5 " + listString; // check: how to return the course.getKdams
        }
        catch (Exception e){
            return "13" + "5";
        }
    }
    public String courseStat(int courseID){
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            user.CourseStats();
            return "12" + "5 " + course.getStats(); // check: how to return the course.getKdams
        }
        catch (Exception e){
            return "13" + "5";
        }
    }
    public String studentStat(String username){
        try {
            checkLoggedIn();
            User checkUser = Database.getInstance().getUser(username); // check if the user exists
            user.StatCommand(checkUser);
            String userStats =
            return "12" + "5 " + userStats; // check: how to return the course.getKdams
        }
        catch (Exception e){
            return "13" + "5";
        }
    }





    public void checkLoggedIn() throws Exception {
        if (user == null)
            throw new Exception("tried to logout when not logged in");
    }


    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
