package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Message;

public class MyCoursesCommand extends Command {
    private static final short opCode = 11;

    /**
     * Constructor.
     */
    public MyCoursesCommand(User user, Message msg) {
        super.user = user;
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            String userStats = user.getCourses(); // check if the requested user is a student
            return new Message(ACK_OP_CODE, opCode, userStats);
        } catch (NotLoggedException | NotAuthorizedException e) {
            return new Message(ERROR_OP_CODE, opCode);
        }
    }
}
