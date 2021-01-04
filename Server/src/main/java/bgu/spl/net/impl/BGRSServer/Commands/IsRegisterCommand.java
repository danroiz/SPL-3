package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.InvalidCourseException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Message;

public class IsRegisterCommand extends Command{
    private static final short opCode = 9;
    private final short courseID;

    /**
     * Constructor.
     */
    public IsRegisterCommand(User user, Message msg) {
        super.user = user;
        courseID = msg.getCourseID();
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            Database.getInstance().verifyValidCourse(courseID);
            String isRegister = user.isRegistered(courseID);
            return new Message(ACK_OP_CODE,opCode,isRegister);
        }
        catch (NotLoggedException | NotAuthorizedException | InvalidCourseException e){
            return new Message(ERROR_OP_CODE,opCode);}
    }
}
