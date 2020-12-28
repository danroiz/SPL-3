package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class AdminRegCommand extends Command {
    private final short opCode = 1;
    private String username;
    private String password;

    public AdminRegCommand(User user, Message msg){
        super.user = user;
        username = msg.getUsername();
        password = msg.getPassword();
    }

    @Override
    public Message execute() {
        try {
            Database.getInstance().createAdmin(username, password);
            return new Message(ACK_OP_CODE,opCode,null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Message(ERROR_OP_CODE,opCode,null);
        }
    }

}
