package bgu.spl.net.impl.BGRSServer.Commands;


import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class LoginCommand extends Command {
    String username;
    String password;

    public LoginCommand(User user, String[] msg) {

        username = msg[0];
        password = msg[1];
    }

//    @Override
//    public String execute(String arg) {
//        String[] data = arg.split(" ");
//        try{
//            User user = Database.getInstance().getUser(data[0]);
//            user.LogIn(data[1]);
//            return "12 "+getOpCode();
//        }
//        catch (Exception e){
//            return "13 " + getOpCode();
//        }
//    }
//    public int getOpCode () {
//        return 3;
//    }

    @Override
    public Message execute() {
        return null;
    }
}
