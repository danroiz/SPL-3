package bgu.spl.net.impl.BGRSServer.Commands;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

public interface CommandSupplier {
    Command createCommand(User user, Message msg);
}
