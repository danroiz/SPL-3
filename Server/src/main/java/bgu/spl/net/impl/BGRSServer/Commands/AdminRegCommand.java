package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.api.Command;
import bgu.spl.net.impl.BGRSServer.Database.Database;

public class AdminRegCommand implements Command<String> {

    @Override
    public String execute(String arg) {
        String[] data = arg.split(" ");
        try {
            Database.getInstance().createAdmin(data[0], data[1]);
            return "12 " + "1";
        } catch (Exception e) {
            return "13 " + "1";
        }
    }

    public int getOpCode() {
        return 1;
    }
}
