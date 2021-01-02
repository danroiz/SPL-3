cmake_minimum_required(VERSION 3.18)
project(Client_2)

set(CMAKE_CXX_STANDARD 14)

include_directories(include)
link_libraries("-lboost_system")
link_libraries(-pthread)
add_executable(Client_2
        include/connectionHandler.h
        include/KeyboardReader.h
        include/Protocol.h
        include/SocketReader.h
        src/BGU_Client.cpp
        src/connectionHandler.cpp
        src/KeyboardReader.cpp
        src/Protocol.cpp
        src/SocketReader.cpp)
