#ifndef BOOST_ECHO_CLIENT_KEYBOARDREADER_H
#define BOOST_ECHO_CLIENT_KEYBOARDREADER_H

#include "connectionHandler.h"
#include <map>
using namespace std;

class KeyboardReader {
private:
    ConnectionHandler& connectionHandler;
    map<string, short> opCodesMap;
    void shortToBytes(short num, char* bytesArr);

public:
    KeyboardReader(ConnectionHandler&);
    void operator()();

};


#endif //BOOST_ECHO_CLIENT_KEYBOARDREADER_H

