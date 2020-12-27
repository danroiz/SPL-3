package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public abstract class Command {
    protected User user;
    protected final int ACK_OP_CODE = 12;
    protected final int ERROR_OP_CODE = 13;

    public abstract Message execute();
    public User getUser () { return user; }
    protected void checkLoggedIn() throws Exception {
        if (user == null)
            throw new Exception("tried to logout when not logged in");
    }

}
