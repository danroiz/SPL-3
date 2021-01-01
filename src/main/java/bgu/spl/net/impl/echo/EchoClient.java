package bgu.spl.net.impl.echo;

import bgu.spl.net.impl.BGRSServer.Database.Admin;
import bgu.spl.net.impl.BGRSServer.Database.User;
import bgu.spl.net.impl.BGRSServer.Message;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EchoClient {

    public static void main(String[] args) throws IOException {




        class B{

            public Message initUser(User user){
                user = new Admin("dan","123");
                return null;
            }
        }

        class A{
            private User user;
            public void checkB(){
                B b = new B();
                b.initUser(user);
                System.out.println(user.getName());
            }
        }

        A a = new A();
        a.checkB();

        System.out.println("*******************");

        TreeMap<Integer,Integer> sortedRegisteredCoursesList = new TreeMap<>();
        sortedRegisteredCoursesList.put(4,24);
        sortedRegisteredCoursesList.put(10,210   );sortedRegisteredCoursesList.put(14,214   );
        sortedRegisteredCoursesList.put(2,22   );
        sortedRegisteredCoursesList.put(8,28   );sortedRegisteredCoursesList.put(11,211   );
        String string = sortedRegisteredCoursesList.toString();
        String string2 = sortedRegisteredCoursesList.values().toString();
        List<Integer> list = new ArrayList<>(sortedRegisteredCoursesList.values());

        System.out.println(string2);

        if (args.length == 0) {
            args = new String[]{"localhost", "hello"};
        }

        if (args.length < 2) {
            System.out.println("you must supply two arguments: host, message");
            System.exit(1);
        }

        //BufferedReader and BufferedWriter automatically using UTF-8 encoding
        try (Socket sock = new Socket(args[0], 7777);
                BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()))) {

            System.out.println("sending message to server");
            out.write(args[1]);
            out.newLine();
            out.flush();

            System.out.println("awaiting response");
            String line = in.readLine();
            System.out.println("message from server: " + line);
        }
    }
}
