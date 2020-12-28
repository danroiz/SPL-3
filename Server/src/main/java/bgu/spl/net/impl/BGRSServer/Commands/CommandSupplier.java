package bgu.spl.net.impl.BGRSServer.Commands;

import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

import java.util.function.Supplier;

public interface CommandSupplier {
    public Command createCommand(User user, Message msg);
}
