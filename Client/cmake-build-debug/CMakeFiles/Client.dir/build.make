# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /opt/clion-2020.2.4/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /opt/clion-2020.2.4/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/dorei/Projects/SPL-3/Client

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/dorei/Projects/SPL-3/Client/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Client.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Client.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Client.dir/flags.make

CMakeFiles/Client.dir/src/BGU_Client.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/BGU_Client.cpp.o: ../src/BGU_Client.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/dorei/Projects/SPL-3/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Client.dir/src/BGU_Client.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/BGU_Client.cpp.o -c /home/dorei/Projects/SPL-3/Client/src/BGU_Client.cpp

CMakeFiles/Client.dir/src/BGU_Client.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/BGU_Client.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/dorei/Projects/SPL-3/Client/src/BGU_Client.cpp > CMakeFiles/Client.dir/src/BGU_Client.cpp.i

CMakeFiles/Client.dir/src/BGU_Client.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/BGU_Client.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/dorei/Projects/SPL-3/Client/src/BGU_Client.cpp -o CMakeFiles/Client.dir/src/BGU_Client.cpp.s

CMakeFiles/Client.dir/src/connectionHandler.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/connectionHandler.cpp.o: ../src/connectionHandler.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/dorei/Projects/SPL-3/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/Client.dir/src/connectionHandler.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/connectionHandler.cpp.o -c /home/dorei/Projects/SPL-3/Client/src/connectionHandler.cpp

CMakeFiles/Client.dir/src/connectionHandler.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/connectionHandler.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/dorei/Projects/SPL-3/Client/src/connectionHandler.cpp > CMakeFiles/Client.dir/src/connectionHandler.cpp.i

CMakeFiles/Client.dir/src/connectionHandler.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/connectionHandler.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/dorei/Projects/SPL-3/Client/src/connectionHandler.cpp -o CMakeFiles/Client.dir/src/connectionHandler.cpp.s

CMakeFiles/Client.dir/src/KeyboardReader.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/KeyboardReader.cpp.o: ../src/KeyboardReader.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/dorei/Projects/SPL-3/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/Client.dir/src/KeyboardReader.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/KeyboardReader.cpp.o -c /home/dorei/Projects/SPL-3/Client/src/KeyboardReader.cpp

CMakeFiles/Client.dir/src/KeyboardReader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/KeyboardReader.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/dorei/Projects/SPL-3/Client/src/KeyboardReader.cpp > CMakeFiles/Client.dir/src/KeyboardReader.cpp.i

CMakeFiles/Client.dir/src/KeyboardReader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/KeyboardReader.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/dorei/Projects/SPL-3/Client/src/KeyboardReader.cpp -o CMakeFiles/Client.dir/src/KeyboardReader.cpp.s

CMakeFiles/Client.dir/src/SocketReader.cpp.o: CMakeFiles/Client.dir/flags.make
CMakeFiles/Client.dir/src/SocketReader.cpp.o: ../src/SocketReader.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/dorei/Projects/SPL-3/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Building CXX object CMakeFiles/Client.dir/src/SocketReader.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/Client.dir/src/SocketReader.cpp.o -c /home/dorei/Projects/SPL-3/Client/src/SocketReader.cpp

CMakeFiles/Client.dir/src/SocketReader.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Client.dir/src/SocketReader.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/dorei/Projects/SPL-3/Client/src/SocketReader.cpp > CMakeFiles/Client.dir/src/SocketReader.cpp.i

CMakeFiles/Client.dir/src/SocketReader.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Client.dir/src/SocketReader.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/dorei/Projects/SPL-3/Client/src/SocketReader.cpp -o CMakeFiles/Client.dir/src/SocketReader.cpp.s

# Object files for target Client
Client_OBJECTS = \
"CMakeFiles/Client.dir/src/BGU_Client.cpp.o" \
"CMakeFiles/Client.dir/src/connectionHandler.cpp.o" \
"CMakeFiles/Client.dir/src/KeyboardReader.cpp.o" \
"CMakeFiles/Client.dir/src/SocketReader.cpp.o"

# External object files for target Client
Client_EXTERNAL_OBJECTS =

Client: CMakeFiles/Client.dir/src/BGU_Client.cpp.o
Client: CMakeFiles/Client.dir/src/connectionHandler.cpp.o
Client: CMakeFiles/Client.dir/src/KeyboardReader.cpp.o
Client: CMakeFiles/Client.dir/src/SocketReader.cpp.o
Client: CMakeFiles/Client.dir/build.make
Client: CMakeFiles/Client.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/dorei/Projects/SPL-3/Client/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_5) "Linking CXX executable Client"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/Client.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Client.dir/build: Client

.PHONY : CMakeFiles/Client.dir/build

CMakeFiles/Client.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/Client.dir/cmake_clean.cmake
.PHONY : CMakeFiles/Client.dir/clean

CMakeFiles/Client.dir/depend:
	cd /home/dorei/Projects/SPL-3/Client/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/dorei/Projects/SPL-3/Client /home/dorei/Projects/SPL-3/Client /home/dorei/Projects/SPL-3/Client/cmake-build-debug /home/dorei/Projects/SPL-3/Client/cmake-build-debug /home/dorei/Projects/SPL-3/Client/cmake-build-debug/CMakeFiles/Client.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Client.dir/depend

