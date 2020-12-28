package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.srv.Server;

public class TPCMain {
    public static void main(String[] args) {
        Database.getInstance().initialize("/home/dorei/Desktop/Projects/SPL-3/Server/Courses.txt");  //one shared object


// you can use any server...
        Server.threadPerClient(
                7777, //port
                CourseRegistrationProtocol::new, //protocol factory
                CourseRegistrationEncoderDecoder::new //message encoder decoder factory
        ).serve();

//        Server.reactor(
//                Runtime.getRuntime().availableProcessors(),
//                7777, //port
//                () ->  new RemoteCommandInvocationProtocol<>(feed), //protocol factory
//                ObjectEncoderDecoder::new //message encoder decoder factory
//        ).serve();

    }
}


