# Multi-Thread Java Server & C++ Client Project
# University Course Registartion System

---------------------------------------------------------

This is an implementation of a “Course Registration System” server and client.
The communication between the server and the client(s) will be performed using a binary communication protocol.

The implementation of the server is based on the Thread-Per-Client (TPC) and Reactor servers.

The messages in this protocol are binary numbers, composed of an opcode which indicates the command,
and the data needed for this command (in various lengths).

----------------------------------------------------------

# To run use:

cd Client
make
cd ../Server
mvn clean
mvn compile

Reactor server execute:

mvn exec:java -Dexec.mainClass=&ldquo;bgu.spl.net.impl.BGRSServer.ReactorMain&ldquo; -Dexec.args=&ldquo;&#60;port&#62; &#60;number of thread&#62;&ldquo;

Thread per client server execute:

mvn exec:java -Dexec.mainClass=&ldquo;bgu.spl.net.impl.BGRSServer.TPCMain&ldquo; -Dexec.args=&ldquo;&#60;port&#62;&ldquo;

Client execute:

BGRSclient &#60;ip&#62; &#60;port&#62;

