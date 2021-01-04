package bgu.spl.net.impl.BGRSServer;

/**
 * This class represents the data transferred from client to server or from server to client
 */
public class Message {
    private final short opCode;
    private short command;
    private short courseID;
    private String username;
    private String password;
    private String additionalMsg;

    /**
     * Constructor for output Message
     * @param opCode - represent an ERR/ACK op code
     * @param command - the command op code that has succeed/failed
     * @param additionalMsg - additional info resulting from the command
     */
    public Message (short opCode, short command,String additionalMsg) {
        this.opCode = opCode;
        this.command = command;
        this.additionalMsg = "\n"+additionalMsg;
    }

    /**
     * Constructor for output Message
     * @param opCode - represent an ERR/ACK op code
     * @param command - the command op code that has succeed/failed
     */
    public Message (short opCode, short command) {
        this.opCode = opCode;
        this.command = command;
    }

    /**
     * Constructor for input Message
     * @param opCode - represent the command opCode
     * @param username - user to login to
     * @param password - password for the user
     * @param courseID - course to act on
     */
    public Message(short opCode, String username, String password, short courseID) {
        this.opCode = opCode;
        this.username = username;
        this.password = password;
        this.courseID = courseID;
    }


    /**
     * Getters
     */

    public short getOpCode() {
        return opCode;
    }

    public String getAdditionalMsg() {
        return additionalMsg;
    }

    public short getCommand() {
        return command;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public short getCourseID(){
        return courseID;
    }
}
