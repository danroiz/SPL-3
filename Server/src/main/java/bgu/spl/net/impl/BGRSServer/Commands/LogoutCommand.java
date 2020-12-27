package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;


public class LogoutCommand extends Command {
    private final String opCode = "4";

    public LogoutCommand(User user, String[] msg) {
        super.user = user;
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            user.Logout();
            user = null;
            return new Message(ACK_OP_CODE,new String[]{opCode});
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
