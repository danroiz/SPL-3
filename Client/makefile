CFLAGS:=-c -Wall -Weffc++ -g -std=c++11 -Iinclude
LDFLAGS:=-lboost_system

all: BGRSclient 
BGRSclient: bin/connectionHandler.o bin/KeyboardReader.o bin/SocketReader.o bin/BGU_Client.o

	@echo 'Building target: BGRSclient'
	@echo 'Invoking: c++ Linker'

	g++ -pthread -o bin/BGRSclient bin/BGU_Client.o bin/KeyboardReader.o bin/SocketReader.o bin/connectionHandler.o $(LDFLAGS)

	@echo 'Finished building target: BGRSclient'
	@echo ' '

bin/KeyboardReader.o: src/KeyboardReader.cpp
	g++ $(CFLAGS) -o bin/KeyboardReader.o src/KeyboardReader.cpp

bin/connectionHandler.o: src/connectionHandler.cpp
	g++ $(CFLAGS) -o bin/connectionHandler.o src/connectionHandler.cpp

bin/SocketReader.o: src/SocketReader.cpp
	g++ $(CFLAGS) -o bin/SocketReader.o src/SocketReader.cpp

bin/BGU_Client.o: src/BGU_Client.cpp
	g++ $(CFLAGS) -o bin/BGU_Client.o src/BGU_Client.cpp


.PHONY: clean
clean:
	rm -f bin/*
