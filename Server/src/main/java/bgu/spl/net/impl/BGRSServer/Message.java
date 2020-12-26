package bgu.spl.net.impl.BGRSServer;

public class Message {
    private int opCode;
    private String[] message;

    public Message(int opCode, String[] message){
        this.opCode = opCode;
        this.message = message;
    }

    public int getOpCode() {
        return opCode;
    }
    public String[] getMessage() {
        return message;
    }
}
