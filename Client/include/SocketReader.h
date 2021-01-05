//
// Created by dorei on 30/12/2020.
//

#ifndef BOOST_ECHO_CLIENT_SOCKETREADER_H
#define BOOST_ECHO_CLIENT_SOCKETREADER_H

#include <mutex>
#include <condition_variable>
#include "connectionHandler.h"

class SocketReader {
private:
    ConnectionHandler& connectionHandler;
    std::mutex& mtx;
    std::condition_variable& conditionVariable;
    bool& shouldTerminate;
public:
    SocketReader(ConnectionHandler&, std::mutex& mtx, std::condition_variable&,bool&);
    ~SocketReader()= default;
    SocketReader(const SocketReader&)= default;
    SocketReader &operator=(const SocketReader&)= default;
    void operator()();
    short bytesToShort(char *bytesArr);
};


#endif //BOOST_ECHO_CLIENT_SOCKETREADER_H
