package bgu.spl.net.impl.newsfeed;

import bgu.spl.net.impl.BGRSServer.Database.Course;
import bgu.spl.net.impl.rci.RCIClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NewsFeedClientMain {

    public static void main(String[] args) throws Exception {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("C:\\Users\\Danro\\Desktop\\SPL-3\\Server\\Courses.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String line : lines) {
            String[] courseInfo = line.split("\\|");
            for (String field : courseInfo){
                System.out.print(field + " ");
            }
            System.out.println();
        }


        if (args.length == 0) {
            args = new String[]{"127.0.0.1"};
        }

//        System.out.println("running clients");
        runFirstClient(args[0]);
        runSecondClient(args[0]);
        runThirdClient(args[0]);
    }

    private static void runFirstClient(String host) throws Exception {
        try (RCIClient c = new RCIClient(host, 7777)) {
            c.send(new PublishNewsCommand(
                    "jobs",
                    "System Programmer, knowledge in C++, Java and Python required. call 0x134693F"));

            c.receive(); //ok

            c.send(new PublishNewsCommand(
                    "headlines",
                    "new SPL assignment is out soon!!"));

            c.receive(); //ok

            c.send(new PublishNewsCommand(
                    "headlines",
                    "THE CAKE IS A LIE!"));

            c.receive(); //ok
        }

    }

    private static void runSecondClient(String host) throws Exception {
        try (RCIClient c = new RCIClient(host, 7777)) {
            c.send(new FetchNewsCommand("jobs"));
            System.out.println("second client received: " + c.receive());
        }
    }

    private static void runThirdClient(String host) throws Exception {
        try (RCIClient c = new RCIClient(host, 7777)) {
            c.send(new FetchNewsCommand("headlines"));
            System.out.println("third client received: " + c.receive());
        }
    }
}
