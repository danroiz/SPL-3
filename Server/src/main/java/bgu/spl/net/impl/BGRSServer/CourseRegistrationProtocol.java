package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.MessagingProtocol;
import bgu.spl.net.impl.BGRSServer.Commands.*;
import bgu.spl.net.impl.BGRSServer.Database.User;
import java.util.HashMap;


public class CourseRegistrationProtocol implements MessagingProtocol<Message> {
    private boolean shouldTerminate = false;
    private User user;
    HashMap<Integer, CommandSupplier> commandSupplierHashMap;

    public CourseRegistrationProtocol() {
        commandSupplierHashMap = new HashMap<>();
        commandSupplierHashMap.put(1, AdminRegCommand::new);
        commandSupplierHashMap.put(2, StudentRegCommand::new);
        commandSupplierHashMap.put(3, LoginCommand::new);
        commandSupplierHashMap.put(4, LogoutCommand::new);
        commandSupplierHashMap.put(5, CourseRegCommand::new);
        commandSupplierHashMap.put(6, KdamCheckCommand::new);
        commandSupplierHashMap.put(7, CourseStatCommand::new);
        commandSupplierHashMap.put(8, StudentStatCommand::new);
        commandSupplierHashMap.put(9, IsRegisterCommand::new);
        commandSupplierHashMap.put(10, UnRegisterCommand::new);
        commandSupplierHashMap.put(11, MyCoursesCommand::new);
    }

    @Override
    public Message process(Message msg) {
        int opCode = msg.getOpCode();
        System.out.println("New message arrived " + msg.getOpCode());
        CommandSupplier commandSupplier = commandSupplierHashMap.get(opCode);
        Command command = commandSupplier.createCommand(user, msg);
        Message message = command.execute();
        checkChangedLoggedCondition(command);
        return message;
    }

    @Override
    public boolean shouldTerminate() {
        return shouldTerminate;
    }

    private void checkChangedLoggedCondition(Command command) {
        if (command.getUser() != user) {
            if (user != null)
                shouldTerminate = true;
            user = command.getUser();
        }
    }
}

