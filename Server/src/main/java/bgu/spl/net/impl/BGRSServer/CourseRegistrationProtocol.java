package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.BGRSServer.Commands.*;
import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;

import java.util.HashMap;
import java.util.stream.Collectors;

public class CourseRegistrationProtocol implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;
    private User user;
    HashMap<Integer, CommandSupplier> commandSupplierHashMap;

    public CourseRegistrationProtocol() {
        commandSupplierHashMap = new HashMap<>();
        commandSupplierHashMap.put(1, AdminRegCommand::new);
        commandSupplierHashMap.put(2, StudentRegCommand::new);
        commandSupplierHashMap.put(3, LoginCommand::new);
        commandSupplierHashMap.put(4, LogoutCommand::new);
        commandSupplierHashMap.put(5, CourseRegCommand::new);
        commandSupplierHashMap.put(6, KdamCheckCommand::new);
        commandSupplierHashMap.put(7, CourseStatCommand::new);
        commandSupplierHashMap.put(8, StudentStatCommand::new);
        commandSupplierHashMap.put(9, IsRegisterCommand::new);
        commandSupplierHashMap.put(10, UnRegisterCommand::new);
        commandSupplierHashMap.put(11, UnRegisterCommand::new);
    }

    @Override
    public Message process(Message msg) {
        int opCode = msg.getOpCode();
        CommandSupplier commandSupplier = commandSupplierHashMap.get(opCode);
        Command command = commandSupplier.createCommand(user, msg.getMessage());
        Message message = command.execute();
        checkChangedLoggedCondition(command);
        return message;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    private void checkChangedLoggedCondition(Command command) {
        if (command.getUser() != user) {
            if (user != null)
                shouldTerminate = true;
            user = command.getUser();
        }
    }
}

//    public String Login(String username,String password) {
//        try{
//            if (user != null)
//                throw new Exception("someone already logged in to user: " + user.getName());
//            User tempUser = Database.getInstance().getUser(username);
//            tempUser.LogIn(password);
//            user = tempUser;
//            return "12 "+"3";
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            return "13 " + "3";
//        }
//    }

//    public String AdminReg(String username, String password) {
//        try {
//            Database.getInstance().createAdmin(username, password);
//            return "12 " + "1";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "13 " + "1";
//        }
//    }

//    public String StudentReg(String username, String password) {
//        try {
//            Database.getInstance().createStudent(username, password);
//            return "12 " + "2";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "13 " + "2";
//        }
//    }

//    public String Logout(){
//
//        try{
//            checkLoggedIn();
//            user.Logout();
//            user = null;
//            return "12" + "4";
//        }
//        catch (Exception e){
//            return "13 " + "4";
//        }
//    }

//    public String courseRegister(int courseID){
//        // check if logged in
//        try {
//            checkLoggedIn();
//            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
//            user.registerCourse(course);
//            return "12" + "5";
//
//        }
//        catch (Exception e){
//            return "13" + "5";
//        }
//    }

//    public String kdamCheck(int courseID){
//        // check if logged in
//        try {
//            checkLoggedIn();
//            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
//            String listString = course.getKdams().stream().map(Object::toString)
//                    .collect(Collectors.joining(", "));
//            return "12" + "6 " + listString; // check: how to return the course.getKdams
//        }
//        catch (Exception e){
//            return "13" + "6";
//        }
//    }


//    public String courseStat(int courseID){
//        try {
//            checkLoggedIn();
//            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
//            user.courseStats();
//            return "12" + "7 " + course.getStats(); // check: how to return the course.getKdams
//        }
//        catch (Exception e){
//            return "13" + "7";
//        }
//    }
//    public String studentStat(String username){
//        try {
//            checkLoggedIn();
//            User checkUser = Database.getInstance().getUser(username); // check if the user exists
//            user.statCommand(checkUser); // check if logged in user is an admin
//            String userStats = checkUser.getCourses(); // check if the requested user is a student
//            return "12 " + "8 " + checkUser.getName() + " "+ userStats; // check: how to return the course.getKdams
//        }
//        catch (Exception e){
//            return "13 " + "8";
//        }
//    }

//    public String isRegistered(int courseID){
//        try {
//            checkLoggedIn();
//            String isRegister = user.isRegistered(courseID);
//            return "12" + " 9 " + isRegister; // check: how to return the course.getKdams
//        }
//        catch (Exception e){
//            return "13" + " 9";
//        }
//    }

//    public String unRegister(int courseID){
//        try {
//            checkLoggedIn();
//            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
//            user.unRegisterCourse(course);
//            return "12 " + "10";
//
//        }
//        catch (Exception e){
//            return "13 " + "10";
//        }
//    }
//
//    public String myCourses(){
//        try {
//            checkLoggedIn();
//            String userStats = user.getCourses(); // check if the requested user is a student
//            return "12 " + "11 " + userStats; // check: how to return the course.getKdams
//        }
//        catch (Exception e) {
//            return "13 " + "11";
//        }
//    }

//
//    public String ACK (int opCode, String additionalMsg) {
//        return "12 " + opCode + additionalMsg;
//    }

//
//
//
//
//    public void checkLoggedIn() throws Exception {
//        if (user == null)
//            throw new Exception("tried to logout when not logged in");
//    }

