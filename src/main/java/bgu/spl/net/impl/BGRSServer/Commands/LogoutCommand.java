package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Message;


public class LogoutCommand extends Command {
    private static final short opCode = 4;

    public LogoutCommand(User user, Message msg) {
        super.user = user;
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            user.Logout();
            user = null;
            return new Message(ACK_OP_CODE,opCode);
        }
        catch (NotLoggedException e) {
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,opCode);
        }
    }
}
