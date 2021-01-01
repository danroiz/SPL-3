//
// Created by dorei on 30/12/2020.
//

#ifndef BOOST_ECHO_CLIENT_SOCKETREADER_H
#define BOOST_ECHO_CLIENT_SOCKETREADER_H


#include "connectionHandler.h"
#include "Protocol.h"

class SocketReader {
private:
    ConnectionHandler& connectionHandler;
    Protocol& protocol;
public:
    SocketReader(ConnectionHandler&, Protocol&);
    void operator()();
    short bytesToShort(char *bytesArr);

};


#endif //BOOST_ECHO_CLIENT_SOCKETREADER_H
