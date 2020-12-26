package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public class AdminRegCommand extends Command {

    public AdminRegCommand(User user, String[] msg){

    }
//
//    @Override
//    public String execute(String arg) {
//        String[] data = arg.split(" ");
//        try {
//            Database.getInstance().createAdmin(data[0], data[1]);
//            return "12 " + "1";
//        } catch (Exception e) {
//            return "13 " + "1";
//        }
//    }

    public int getOpCode() {
        return 1;
    }

    @Override
    public Message execute() {
        return null;
    }
}
