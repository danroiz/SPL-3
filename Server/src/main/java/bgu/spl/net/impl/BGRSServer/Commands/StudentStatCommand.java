package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class StudentStatCommand extends Command{
    private final short opCode = 8;
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
            return new Message(ACK_OP_CODE,opCode,"\n"+checkUser.getName()+"\n"+userStats);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,opCode,null);
        }
    }
}
