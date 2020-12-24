package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.api.Command;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;



public class LogoutCommand implements Command<String> {
    @Override
    public String execute(String arg) {
        String[] data = arg.split(" ");
        try{
            User user = Database.getInstance().getUser(data[0]);
            user.Logout();
            return "12 "+getOpCode();
        }
        catch (Exception e){
            return "13 " + getOpCode();
        }
    }
    public int getOpCode () {
        return 4;
    }
}
