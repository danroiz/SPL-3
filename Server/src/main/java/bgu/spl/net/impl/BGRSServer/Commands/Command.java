package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.NotLoggedException;
import bgu.spl.net.impl.BGRSServer.Message;

public abstract class Command {
    protected static final short ACK_OP_CODE = 12;
    protected static final short ERROR_OP_CODE = 13;
    protected User user;

    public abstract Message execute();

    public User getUser () {
        return user;
    }

    protected void checkLoggedIn() throws NotLoggedException {
        if (user == null)
            throw new NotLoggedException("tried to logout when not logged in");
    }

}
