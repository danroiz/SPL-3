#include "KeyboardReader.h"
using namespace std;
KeyboardReader::KeyboardReader(ConnectionHandler& connectionHandler, mutex &mtx, condition_variable& conditionVariable, bool& shouldTerminate): connectionHandler(connectionHandler), opCodesMap(), mtx(mtx),conditionVariable(conditionVariable), shouldTerminate(shouldTerminate) {
    opCodesMap.insert(pair<string , short>("ADMINREG", 1)); opCodesMap.insert(pair<string , short>("COURSESTAT", 7));
    opCodesMap.insert(pair<string , short>("STUDENTREG", 2)); opCodesMap.insert(pair<string , short>("STUDENTSTAT", 8));
    opCodesMap.insert(pair<string , short>("LOGIN", 3)); opCodesMap.insert(pair<string , short>("ISREGISTERED", 9));
    opCodesMap.insert(pair<string , short>("LOGOUT", 4)); opCodesMap.insert(pair<string , short>("UNREGISTER", 10));
    opCodesMap.insert(pair<string , short>("COURSEREG", 5)); opCodesMap.insert(pair<string , short>("MYCOURSES", 11));
    opCodesMap.insert(pair<string , short>("KDAMCHECK", 6));
}

void KeyboardReader::operator()() {
    string line;
    while (!shouldTerminate) {
        const short bufsize = 1024;
        char buf[bufsize];
        std::cin.getline(buf, bufsize);
        std::string line(buf);
        vector<string> input;
        stringstream start(line);
        string word;
        while (getline(start, word, ' ')) { // creating a vector of the input from keyboard separated by space
            input.push_back(word);
        }
        // translate short to byte array
        char opCodeBytes[2];
        if (opCodesMap.find(input.at(0)) == opCodesMap.end()) // checking if the command is valid
            continue;
        short opCode = opCodesMap[input.at(0)];
        shortToBytes(opCode,opCodeBytes);
        int begin_index = 0;
        if (opCode >= 1 && opCode <= 3) {
            string username = input.at(1);
            string password = input.at(2);
            const char *username_bytes = username.c_str();
            const char *password_bytes = password.c_str();
            char message[2 + username.length() + 1 + password.length() + 1];
            begin_index = copyBytesArray(message,opCodeBytes,begin_index,2);
            begin_index = copyBytesArray(message,username_bytes,begin_index,username.length());
            message[begin_index++] = '\0';
            begin_index = copyBytesArray(message,password_bytes,begin_index, password.length());
            message[begin_index++] = '\0';
            connectionHandler.sendBytes(message,begin_index);
        }
        else if (opCode == 4 || opCode == 11) {
            char message [2];
            begin_index = copyBytesArray(message,opCodeBytes,begin_index,2);
            connectionHandler.sendBytes(message,begin_index);
            if (opCode == 4){
                unique_lock<mutex> lock(mtx);
                conditionVariable.wait(lock); // wait until the SocketReader gets a reply and determine if shouldTerminate
            }
        }
        else if (opCode == 5 || opCode == 6 || opCode == 7 || opCode == 9 || opCode == 10) {
            char courseIdBytes[2];
            short courseID = atoi(input.at(1).c_str());
            shortToBytes(courseID, courseIdBytes);
            char message[4];
            begin_index = copyBytesArray(message,opCodeBytes,begin_index,2);
            begin_index = copyBytesArray(message,courseIdBytes,begin_index,2);
            connectionHandler.sendBytes(message,begin_index);
        }
        else if (opCode == 8) {
            string username = input.at(1);
            const char *username_bytes = username.c_str();
            char message[2 + username.length() + 1];
            begin_index = copyBytesArray(message,opCodeBytes,begin_index,2);
            begin_index = copyBytesArray(message,username_bytes,begin_index,username.length());
            message[begin_index++] = '\0';
            connectionHandler.sendBytes(message, begin_index);
        }
    }
}


// copy all the bytes from "fromArray" to "toArray" starting index begin in "toArray"
int KeyboardReader::copyBytesArray(char *toArray , const char *fromArray, int begin, size_t bytesToCopy) {
    for (size_t i = 0; i < bytesToCopy; i++){
        toArray[begin++] = fromArray[i];
    }
    return begin;
}

void KeyboardReader::shortToBytes( short num, char *bytesArr) {
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}

