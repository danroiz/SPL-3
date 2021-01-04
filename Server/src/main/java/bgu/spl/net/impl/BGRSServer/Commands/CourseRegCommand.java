package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.*;
import bgu.spl.net.impl.BGRSServer.Exceptions.*;
import bgu.spl.net.impl.BGRSServer.Message;

public class CourseRegCommand extends Command{
    private static final short opCode = 5;
    private short courseID;

    public CourseRegCommand(User user, Message msg) {
        super.user = user;
        courseID = msg.getCourseID();
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            user.registerCourse(course);
            return new Message(ACK_OP_CODE,opCode);
        }
        catch (InvalidCourseException | NotLoggedException | NotAuthorizedException | RegisterException | CourseFullException e){
         //   System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,opCode);
        }
    }
}
