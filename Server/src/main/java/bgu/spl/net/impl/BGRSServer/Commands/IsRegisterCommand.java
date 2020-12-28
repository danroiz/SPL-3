package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class IsRegisterCommand extends Command{
    private final short opCode = 9;
    private short courseID;

    public IsRegisterCommand(User user, Message msg) {
        super.user = user;
        courseID = msg.getCourseID();
    }

    @Override
    public Message execute() {
        try {
            checkLoggedIn();
            String isRegister = user.isRegistered(courseID);
            return new Message(ACK_OP_CODE,opCode,"\n"+isRegister);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,opCode,null);}

    }
}
