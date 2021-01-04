package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.InvalidCourseException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Message;

import java.util.stream.Collectors;

public class KdamCheckCommand extends Command{
    private static final short opCode = 6;
    private short courseID;

    public KdamCheckCommand(User user, Message msg) {
        super.user = user;
        courseID = msg.getCourseID();
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            String kdams = user.KdamCheck(course);
            return new Message(ACK_OP_CODE,opCode,kdams);
        }
        catch (InvalidCourseException | NotLoggedException | NotAuthorizedException e) {
        //    System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,opCode);
        }
    }
}
