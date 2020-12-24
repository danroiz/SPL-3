package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.Command;
import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.impl.BGRSServer.Database.User;

public class CourseRegistrationProtocol implements MessagingProtocol<String> {
    private boolean shouldTerminate = false;
    private User user;

    // map - for each op code - there is a command

    @Override
    public String process(String msg) {
        int opCode = Integer.parseInt(msg.substring(0,msg.indexOf(' ')));
        Command command = codes.get(opCode);

        if ((opCode >= 4) && (user.loggedInd))
            return command.execute(msg);
        return command.execute(msg.substring(msg.indexOf(' ')));
        // according to the op code of the msg, get the right command for it. do command.exectute.
        //
        // command.execute(rest of the message)
    }




    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }
}
