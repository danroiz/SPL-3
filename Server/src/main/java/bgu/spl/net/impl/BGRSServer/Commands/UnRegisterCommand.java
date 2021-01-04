package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.InvalidCourseException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UnRegisterException;
import bgu.spl.net.impl.BGRSServer.Message;

public class UnRegisterCommand extends Command{
    private static final short opCode = 10;
    private final short courseID;

    /**
     * Constructor.
     */
    public UnRegisterCommand(User user, Message msg) {
        super.user = user;
        courseID = msg.getCourseID();
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            Course course = Database.getInstance().verifyValidCourse(courseID); // check if course exist
            user.unRegisterCourse(course);
            return new Message(ACK_OP_CODE,opCode);
        } catch (UnRegisterException  | InvalidCourseException | NotLoggedException | NotAuthorizedException e) {
            return new Message(ERROR_OP_CODE,opCode);
        }
    }
}
