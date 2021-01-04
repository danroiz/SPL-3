package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Message;

public abstract class Command {
    protected static final short ACK_OP_CODE = 12;
    protected static final short ERROR_OP_CODE = 13;
    protected User user;

    /**
     *
     * @return an output Message object (ACK/ERROR) for each command
     */
    public abstract Message execute();

    /**
     *
     * @return user
     */
    public User getUser () {
        return user;
    }

    /**
     *
     * @throws NotLoggedException if the user is not logged in
     */
    protected void checkLoggedIn() throws NotLoggedException {
        if (user == null)
            throw new NotLoggedException("tried to logout when not logged in");
    }

}
