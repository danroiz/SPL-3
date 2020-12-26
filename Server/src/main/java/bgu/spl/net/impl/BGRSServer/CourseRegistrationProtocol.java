package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.BGRSServer.Commands.AdminRegCommand;
import bgu.spl.net.impl.BGRSServer.Commands.Command;
import bgu.spl.net.impl.BGRSServer.Commands.CommandSupplier;
import bgu.spl.net.impl.BGRSServer.Commands.LoginCommand;
import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;

import java.util.HashMap;
import java.util.stream.Collectors;

public class CourseRegistrationProtocol implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;
    private User user;
    // map - for each op code - there is a command
    HashMap<Integer, CommandSupplier> commandSupplierHashMap;

    public CourseRegistrationProtocol(){
        user = null;
        commandSupplierHashMap = new HashMap<>();
        commandSupplierHashMap.put(1, msg -> new AdminRegCommand(user,msg));
        commandSupplierHashMap.put(3, msg -> new LoginCommand(user,msg));

    }
    @Override
    public Message process(Message msg) {
        int opCode = msg.getOpCode();
        CommandSupplier commandSupplier = commandSupplierHashMap.get(opCode);
        Command command = commandSupplier.createCommand(msg.getMessage());
        command.execute();



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

    public String courseRegister(int courseID){
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
            return "12" + "6 " + listString; // check: how to return the course.getKdams
        }
        catch (Exception e){
            return "13" + "6";
        }
    }
    public String courseStat(int courseID){
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            user.courseStats();
            return "12" + "7 " + course.getStats(); // check: how to return the course.getKdams
        }
        catch (Exception e){
            return "13" + "7";
        }
    }
    public String studentStat(String username){
        try {
            checkLoggedIn();
            User checkUser = Database.getInstance().getUser(username); // check if the user exists
            user.statCommand(checkUser); // check if logged in user is an admin
            String userStats = checkUser.getCourses(); // check if the requested user is a student
            return "12 " + "8 " + checkUser.getName() + " "+ userStats; // check: how to return the course.getKdams
        }
        catch (Exception e){
            return "13 " + "8";
        }
    }

    public String isRegistered(int courseID){
        try {
            checkLoggedIn();
            String isRegister = user.isRegistered(courseID);
            return "12" + " 9 " + isRegister; // check: how to return the course.getKdams
        }
        catch (Exception e){
            return "13" + " 9";
        }
    }

    public String unRegister(int courseID){
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            user.unRegisterCourse(course);
            return "12 " + "10";

        }
        catch (Exception e){
            return "13 " + "10";
        }
    }

    public String myCourses(){
        try {
            checkLoggedIn();
            String userStats = user.getCourses(); // check if the requested user is a student
            return "12 " + "11 " + userStats; // check: how to return the course.getKdams
        }
        catch (Exception e) {
            return "13 " + "11";
        }
    }
    public String ACK (int opCode, String additionalMsg) {
        return "12 " + opCode + additionalMsg;
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
