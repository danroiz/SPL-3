//
// Created by dorei on 30/12/2020.
//

#ifndef BOOST_ECHO_CLIENT_SOCKETREADER_H
#define BOOST_ECHO_CLIENT_SOCKETREADER_H


#include "connectionHandler.h"

class SocketReader {
private:
    ConnectionHandler& connectionHandler;
public:
    SocketReader(ConnectionHandler&);
    void operator()();
    short bytesToShort(char *bytesArr);

};


#endif //BOOST_ECHO_CLIENT_SOCKETREADER_H
