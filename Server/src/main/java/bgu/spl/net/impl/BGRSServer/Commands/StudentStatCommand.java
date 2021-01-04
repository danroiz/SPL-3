package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotAuthorizedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UserNotExistException;
import bgu.spl.net.impl.BGRSServer.Message;

public class StudentStatCommand extends Command{
    private static final short opCode = 8;
    private final String username;

    /**
     * Constructor.
     */
    public StudentStatCommand(User user, Message msg) {
        super.user = user;
        username = msg.getUsername();
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            User checkUser = Database.getInstance().getUser(username); // check if the user exists
            user.studentStats(checkUser);
            String userStats = checkUser.getCourses();
            return new Message(ACK_OP_CODE,opCode,"Student: " + checkUser.getName() + "\n" + "Courses: " + userStats);
        }
        catch (UserNotExistException | NotLoggedException | NotAuthorizedException e) {
            return new Message(ERROR_OP_CODE, opCode);
        }
    }
}
