//
// Created by dorei on 30/12/2020.
//

#ifndef BOOST_ECHO_CLIENT_SOCKETREADER_H
#define BOOST_ECHO_CLIENT_SOCKETREADER_H


#include "connectionHandler.h"

class SocketReader {
private:
    ConnectionHandler& connectionHandler;
    bool shouldTerminate;
public:
    SocketReader(ConnectionHandler&);
    ~SocketReader()= default;
    SocketReader(const SocketReader&)= default;
    SocketReader &operator=(const SocketReader&)= default;
    void operator()();
    short bytesToShort(char *bytesArr);

};


#endif //BOOST_ECHO_CLIENT_SOCKETREADER_H
