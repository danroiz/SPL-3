package bgu.spl.net.impl.BGRSServer.Commands;


import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class LoginCommand extends Command {
    private final String opCode = "3";
    private String username;
    private String password;

    public LoginCommand(User user, String[] msg) {
        super.user = user;
        username = msg[0];
        password = msg[1];
    }

    @Override
    public Message execute() {
        try{
            if (user != null)
                throw new Exception("someone already logged in to user: " + user.getName());
            User tempUser = Database.getInstance().getUser(username);
            tempUser.LogIn(password);
            super.user = tempUser;
            return new Message(ACK_OP_CODE,new String[]{opCode});
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
