package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.impl.BGRSServer.Database.Database;
import bgu.spl.net.srv.Server;

import java.util.TreeSet;

public class TPCMain {
    public static void main(String[] args) {
        /* TO DO:
         * fix thread safe for database (course class)
         * add exceptions
         * fix encoding of ERR (not need \0 at the end)
         */

        boolean succ = Database.getInstance().initialize("C:\\Users\\Danro\\Documents\\Semester_C\\SPL\\SPL-3\\Server\\Courses.txt");  //one shared object
        System.out.println("Initialize database: " + succ);

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


