package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class StudentStatCommand extends Command{
    private final String opCode = "8";
    private String username;

    public StudentStatCommand(User user, String[] msg) {
        super.user = user;
        username = msg[0];
    }


    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            User checkUser = Database.getInstance().getUser(username); // check if the user exists
            user.statCommand(checkUser); // check if logged in user is an admin
            String userStats = checkUser.getCourses(); // check if the requested user is a student
            return new Message(ACK_OP_CODE,new String[]{opCode,checkUser.getName(),userStats});
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
