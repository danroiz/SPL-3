package bgu.spl.net.impl.BGRSServer;
import bgu.spl.net.srv.Server;

public class TPCMain {
    public static void main(String[] args) {
        int port = Integer.parseInt(args[0]);
        Server.threadPerClient(
                port,
                CourseRegistrationProtocol::new, //protocol factory
                CourseRegistrationEncoderDecoder::new //message encoder decoder factory
        ).serve();
    }
    // Added comments
    /*

    IGNORE

     */
}


