package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class MyCoursesCommand extends Command{
    private final String opCode = "11";

    public MyCoursesCommand(User user, String[] msg) {
        super.user = user;
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            String userStats = user.getCourses(); // check if the requested user is a student
            return new Message(ACK_OP_CODE,new String[]{opCode,userStats});
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
