package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UserNotExistException;
import bgu.spl.net.impl.BGRSServer.Message;

public class StudentStatCommand extends Command{
    private static final short opCode = 8;
    private String username;

    public StudentStatCommand(User user, Message msg) {
        super.user = user;
        username = msg.getUsername();
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            User checkUser = Database.getInstance().getUser(username); // check if the user exists
            user.statCommand(checkUser); // check if logged in user is an admin
            String userStats = checkUser.getCourses(); // check if the requested user is a student
            return new Message(ACK_OP_CODE,opCode,"Student: " + checkUser.getName() + "\n" + "Courses: " + userStats);
        }
        catch (UserNotExistException | NotLoggedException | NotAuthorizedException e) {
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE, opCode);
        }
    }
}
