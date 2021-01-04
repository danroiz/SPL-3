#include <stdlib.h>
#include <connectionHandler.h>
#include <thread>
#include "KeyboardReader.h"
#include "SocketReader.h"

/**
* This code assumes that the server replies the exact text the client sent it (as opposed to the practical session example)
*/
using namespace std;

int main (int argc, char *argv[]) {
    if (argc < 3) {
        std::cerr << "Usage: " << argv[0] << " host port" << std::endl << std::endl;
        return -1;
    }

    std::string host = argv[1];
    short port = atoi(argv[2]);

    ConnectionHandler connectionHandler(host, port);
    if (!connectionHandler.connect()) {
        std::cerr << "Cannot connect to " << host << ":" << port << std::endl;
        return 1;
    }

    KeyboardReader keyboardReader(connectionHandler);
    SocketReader socketReader(connectionHandler);

    std::thread keyboardThread(std::ref(keyboardReader));
    std::thread socketThread(std::ref(socketReader));

    socketThread.join();
    exit(0);
}
