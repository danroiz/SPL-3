cmake_minimum_required(VERSION 3.17)
project(Boost_Echo_Client)

set(CMAKE_CXX_STANDARD 14)

link_libraries("-lboost_system")
link_libraries(-pthread)
include_directories(include)

add_executable(Boost_Echo_Client
        include/connectionHandler.h
        src/connectionHandler.cpp
        src/BGU_Client.cpp src/Protocol.cpp include/Protocol.h src/KeyboardReader.cpp include/KeyboardReader.h src/SocketReader.cpp include/SocketReader.h)
