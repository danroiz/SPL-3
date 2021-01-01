package bgu.spl.net.impl.BGRSServer;


public class Message {
    private short opCode;
    private short command;
    private short courseID;
    private String username;
    private String password;
    private String additionalMsg;

    public Message (short opCode, short command,String additionalMsg) {
        this.opCode = opCode;
        this.command = command;
        this.additionalMsg = "\n"+additionalMsg;
    }

    public Message (short opCode, short command) {
        this.opCode = opCode;
        this.command = command;
    }

    public Message(short opCode, String username, String password, short courseID) {
        this.opCode = opCode;
        this.username = username;
        this.password = password;
        this.courseID = courseID;
    }

    public short getOpCode() {
        return opCode;
    }

    public String getAdditionalMsg() {
        return additionalMsg;
    }

    public short getCommand() {
        return command;
    }

    public String getUsername(){return username;}

    public String getPassword(){return password;}

    public short getCourseID(){return courseID;}
}
