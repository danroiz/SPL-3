//
// Created by dorei on 30/12/2020.
//

#include "KeyboardReader.h"
using namespace std;
KeyboardReader::KeyboardReader(ConnectionHandler & connectionHandler): connectionHandler(connectionHandler){
    opCodesMap.insert(pair<string , short>("ADMINREG", 1)); opCodesMap.insert(pair<string , short>("COURSESTAT", 7));
    opCodesMap.insert(pair<string , short>("STUDENTREG", 2)); opCodesMap.insert(pair<string , short>("STUDENTSTAT", 8));
    opCodesMap.insert(pair<string , short>("LOGIN", 3)); opCodesMap.insert(pair<string , short>("ISREGISTERED", 9));
    opCodesMap.insert(pair<string , short>("LOGOUT", 4)); opCodesMap.insert(pair<string , short>("UNREGISTER", 10));
    opCodesMap.insert(pair<string , short>("COURSEREG", 5)); opCodesMap.insert(pair<string , short>("MYCOURSES", 11));
    opCodesMap.insert(pair<string , short>("KDAMCHECK", 6));
}
void KeyboardReader::operator()() {
    string line;
    while (!connectionHandler.terminate1) { // add termination condition in connection handler
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
        if (opCodesMap.find(input.at(0)) == opCodesMap.end())
            continue;
        short opCode = opCodesMap[input.at(0)];

        shortToBytes(opCode,opCodeBytes);

        if (opCode >= 1 && opCode <= 3) {
            string username = input.at(1);
            string password = input.at(2);
            const char *username_bytes = username.c_str();
            const char *password_bytes = password.c_str();

            char message[2 + username.length() + 1 + password.length() + 1];
            message[0] = opCodeBytes[0];
            message[1] = opCodeBytes[1];
            int messageIndex = 2;
            for (int i = 0; i < username.length(); i++) {
                message[messageIndex] = username_bytes[i];
                messageIndex++;
            }
            message[messageIndex] = '\0';
            messageIndex++;
            for (int i = 0; i < password.length(); i++) {
                message[messageIndex] = password_bytes[i];
                messageIndex++;
            }
            message[messageIndex] = '\0';
            messageIndex++;
            connectionHandler.sendBytes(message,messageIndex);

        }
        else if (opCode == 4 || opCode == 11) {
            char message [2];
            message[0] = opCodeBytes[0];
            message[1] = opCodeBytes[1];

            connectionHandler.sendBytes(message,2);
            cout << "sending logout" << endl;
            connectionHandler.terminate1 = true;
            sleep(1);
            // send only the op-code
        }
        else if (opCode == 5 || opCode == 6 || opCode == 7 || opCode == 9 || opCode == 10) {
            char courseIdBytes[2];
            short courseID = atoi(input.at(1).c_str());
            shortToBytes(courseID, courseIdBytes);


            char message[4];
            message[0] = opCodeBytes[0];
            message[1] = opCodeBytes[1];
            message[2] = courseIdBytes[0];
            message[3] = courseIdBytes[1];

            connectionHandler.sendBytes(message,4);

        }
        else if (opCode == 8) {
            string username = input.at(1);
            const char *username_bytes = username.c_str();

            char message[2 + username.length() + 1];
            message[0] = opCodeBytes[0];
            message[1] = opCodeBytes[1];
            int messageIndex = 2;
            for (int i = 0; i < username.length(); i++) {
                message[messageIndex] = username_bytes[i];
                messageIndex++;
            }
            message[messageIndex] = '\0';
            messageIndex++;

            connectionHandler.sendBytes(message, messageIndex);

        }
    }
}

void KeyboardReader::shortToBytes( short num, char *bytesArr) {
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}






