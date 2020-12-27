package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class StudentRegCommand extends Command {
    private final String opCode = "2";
    private String username;
    private String password;

    public StudentRegCommand(User user, String[] msg){
        super.user = user;
        username = msg[0];
        password = msg[1];
    }


    @Override
    public Message execute() {
        try {
            Database.getInstance().createStudent(username, password);
            return new Message(ACK_OP_CODE,new String[]{opCode});
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,new String[]{opCode});
        }
    }
}
