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
        char bytes[2];
        const short bufsize = 1024;
        char buf[bufsize];

        std::cin.getline(buf, bufsize);
        std::string line(buf);
        cout << "we got a line = " << line << endl;
        vector<string> input;
        stringstream start(line);
        string word;
        while (getline(start, word, ' ')) {
            input.push_back(word);
        }
        short opCode = opCodesMap[input.at(0)];
        cout << "opCode = " << opCode << endl;
        shortToBytes(opCode,bytes);
        cout << "CHAR = " << bytes[0] << endl;

        char const *c = line.c_str();
        std::vector<char> bytess(input.at(1).begin(), input.at(1).end());
        bytess.push_back('\0');
        char *cc = &bytes[0];
        cout << (c[0] == bytes[0]);


//        stringstream start(line);
//        string tempWord;
//        while (getline(start, tempWord, ' ')) {
//            toFrame.push_back(tempWord);
//        }
//        protocol_->process(toFrame);
break;


    }
}

void KeyboardReader::shortToBytes(short num, char *bytesArr) {
    bytesArr[0] = ((num >> 8) & 0xFF);
    bytesArr[1] = (num & 0xFF);
}




