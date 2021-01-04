#ifndef BOOST_ECHO_CLIENT_KEYBOARDREADER_H
#define BOOST_ECHO_CLIENT_KEYBOARDREADER_H

#include "connectionHandler.h"
#include <unordered_map>
using namespace std;

class KeyboardReader {
private:
    ConnectionHandler& connectionHandler;
    unordered_map<string, short> opCodesMap;
    void shortToBytes(short num, char* bytesArr);
    int copyBytesArray(char *toArray , const char *fromArray, int to_begin, size_t from_length);

public:
    KeyboardReader(ConnectionHandler&);
    ~KeyboardReader()= default;
    KeyboardReader(const KeyboardReader&)= default;
    KeyboardReader &operator=(const KeyboardReader&)= default;

    void operator()();
};


#endif //BOOST_ECHO_CLIENT_KEYBOARDREADER_H

