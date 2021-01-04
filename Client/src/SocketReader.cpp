//
// Created by dorei on 30/12/2020.
//

#include "SocketReader.h"
using namespace std;
SocketReader::SocketReader(ConnectionHandler &connectionHandler) : connectionHandler(connectionHandler), shouldTerminate(false) {}

void SocketReader::operator()()  {
    while (!connectionHandler.shouldTerminate) {
        char opCodeBytes[4];
        short opCode= -1;
        short command = -1;
        string commandStatus;
        string line;
        if (connectionHandler.getBytes(opCodeBytes,4)) {
            opCode = bytesToShort(opCodeBytes);
            command = bytesToShort(&opCodeBytes[2]);
        }
        if (opCode == 12) {
            if (command == 4) // received ACK for Logout Command
                connectionHandler.shouldTerminate = true;

            commandStatus = "ACK ";
            connectionHandler.getLine(line);
        }
        else
            commandStatus = "ERROR ";
        cout << commandStatus << command << line << endl;
    }
}

short SocketReader::bytesToShort(char *bytesArr) {
    short result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}