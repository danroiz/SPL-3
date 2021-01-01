//
// Created by dorei on 30/12/2020.
//

#include "SocketReader.h"
using namespace std;
SocketReader::SocketReader(ConnectionHandler &connectionHandler, Protocol &protocol) : connectionHandler(connectionHandler), protocol(protocol) {}

void SocketReader::operator()()  {
//    while(!protocol->isSocketTermination()){
    while (!connectionHandler.terminate2) {
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
            if (command == 4)
                connectionHandler.terminate2 = true;
            commandStatus = "ACK ";
            connectionHandler.getLine(line);
        }
        else {
            connectionHandler.terminate1 = false;
            commandStatus = "ERROR ";
        }
        cout << commandStatus << command << line << endl;
    }
}





short SocketReader::bytesToShort(char *bytesArr) {
    short result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}