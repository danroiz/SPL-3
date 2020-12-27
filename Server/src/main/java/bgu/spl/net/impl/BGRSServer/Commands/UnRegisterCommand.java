package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class UnRegisterCommand extends Command{
    private final String opCode = "10";
    private int courseID;

    public UnRegisterCommand(User user, String[] msg) {
        super.user = user;
        courseID = Integer.parseInt(msg[0]);
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            user.unRegisterCourse(course);
            return new Message(ACK_OP_CODE,new String[]{opCode});

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
