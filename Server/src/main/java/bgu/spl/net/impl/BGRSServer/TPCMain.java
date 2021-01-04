package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.srv.Server;

import java.util.TreeSet;

public class TPCMain {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        Server.threadPerClient(
                port,
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


