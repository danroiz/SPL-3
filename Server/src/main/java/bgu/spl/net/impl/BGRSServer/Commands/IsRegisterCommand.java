package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class IsRegisterCommand extends Command{
    private final String opCode = "9";
    private int courseID;

    public IsRegisterCommand(User user, String[] msg) {
        super.user = user;
        courseID = Integer.parseInt(msg[0]);
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            String isRegister = user.isRegistered(courseID);
            return new Message(ACK_OP_CODE,new String[]{opCode,isRegister});
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
