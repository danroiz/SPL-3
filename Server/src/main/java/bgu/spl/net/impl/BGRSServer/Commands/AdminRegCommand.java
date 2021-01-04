package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.AmbiguousUsernameException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UserLoginException;
import bgu.spl.net.impl.BGRSServer.Message;

public class AdminRegCommand extends Command {
    private final static short opCode = 1;
    private final String username;
    private final String password;

    /**
     * Constructor.
     */
    public AdminRegCommand(User user, Message msg){
        super.user = user;
        username = msg.getUsername();
        password = msg.getPassword();
    }

    @Override
    public Message execute() {
        try {
            if (user != null)
                throw new UserLoginException("cannot register an admin after logged in");
            Database.getInstance().createAdmin(username, password);
            return new Message(ACK_OP_CODE,opCode);
        } catch (AmbiguousUsernameException | UserLoginException e) {
            return new Message(ERROR_OP_CODE,opCode);
        }
    }

}
