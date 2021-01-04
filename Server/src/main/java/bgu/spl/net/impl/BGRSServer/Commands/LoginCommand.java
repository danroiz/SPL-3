package bgu.spl.net.impl.BGRSServer.Commands;


import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Exceptions.DoubleLoginException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UserLoginException;
import bgu.spl.net.impl.BGRSServer.Exceptions.UserNotExistException;
import bgu.spl.net.impl.BGRSServer.Message;

public class LoginCommand extends Command {
    private static final short opCode = 3;
    private String username;
    private String password;

    public LoginCommand(User user, Message msg) {
        super.user = user;
        username = msg.getUsername();
        password = msg.getPassword();
    }

    @Override
    public Message execute() {
        try{
            if (user != null)
                throw new DoubleLoginException("someone already logged in to user: " + user.getName());
            User tempUser = Database.getInstance().getUser(username);
            tempUser.LogIn(password);
            super.user = tempUser;
            return new Message(ACK_OP_CODE,opCode);
        }
        catch (UserNotExistException | DoubleLoginException | UserLoginException e) {
        //    System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,opCode);
        }
    }
}
