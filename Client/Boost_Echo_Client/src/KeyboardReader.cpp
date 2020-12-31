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
    while (true) { // add termination condition in connection handler
        const short bufsize = 1024;
        char buf[bufsize];

        std::cin.getline(buf, bufsize);
        std::string line(buf);
        cout << "we got a line = " << line << endl;
        vector<string> input;
        stringstream start(line);
        string word;
        while (getline(start, word, ' ')) { // creating a vector of the input from keyboard separated by space
            input.push_back(word);
        }

        char opCode_bytes[2];
        short opCode = opCodesMap[input.at(0)];
        cout << "opCode = " << opCode << endl;
        shortToBytes(opCode,opCode_bytes);


        // in case of OPCODE 1/2/3
        string username = input.at(1);
        string password = input.at(2);

        const char* username_bytes = username.c_str();
        const char* password_bytes = password.c_str();

        char message[2+username.length()+1+password.length()+1];
        message[0] = opCode_bytes[0];
        message[1] = opCode_bytes[1];
        int messageIndex = 2;
        for (int i = 0; i < username.length(); i++){
            message[messageIndex] = username_bytes[i];
            messageIndex++;
        }
        message[messageIndex] = '\0';
        messageIndex++;
        for (int i = 0; i < password.length(); i++){
            message[messageIndex] = password_bytes[i];
            messageIndex++;
        }
        message[messageIndex] = '\0';
        messageIndex++;

        // print it just to check
        for (int i = 0; i < 2+username.length()+1+password.length()+1; i++){
            cout << message[i];
        }


        //connectionHandler.sendBytes(message,messageIndex);


        short castback = bytesToShort(opCode_bytes);
        cout << "translated bytes to short, result: " << castback << endl;

//        char const *c = line.c_str();
//        std::vector<char> bytess(input.at(1).begin(), input.at(1).end());
//        bytess.push_back('\0');
//        char *cc = &bytes[0];
//        cout << (c[0] == bytes[0]);


//        stringstream start(line);
//        string tempWord;
//        while (getline(start, tempWord, ' ')) {
//            toFrame.push_back(tempWord);
//        }
//        protocol_->process(toFrame);
break;


    }
}

void KeyboardReader::shortToBytes( short num, char *bytesArr) {
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}

short KeyboardReader::bytesToShort(char *bytesArr) {
    short result = (short)((bytesArr[0] & 0xff) << 8);
    result += (short)(bytesArr[1] & 0xff);
    return result;
}





