package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

import java.util.stream.Collectors;

public class KdamCheckCommand extends Command{
    private final String opCode = "6";
    private int courseID;

    public KdamCheckCommand(User user, String[] msg) {
        super.user = user;
        courseID = Integer.parseInt(msg[0]);
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            String kdams = course.getKdams().stream().map(Object::toString)
                    .collect(Collectors.joining(", "));
            return new Message(ACK_OP_CODE,new String[]{opCode, kdams});
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
