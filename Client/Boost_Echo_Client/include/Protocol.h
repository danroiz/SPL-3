//
// Created by dorei on 30/12/2020.
//

#ifndef BOOST_ECHO_CLIENT_PROTOCOL_H
#define BOOST_ECHO_CLIENT_PROTOCOL_H


#include "connectionHandler.h"

class Protocol {
private:
    ConnectionHandler& connectionHandler;
public:
    Protocol(ConnectionHandler&);
};


#endif //BOOST_ECHO_CLIENT_PROTOCOL_H
