package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.MessageEncoderDecoder;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class CourseRegistrationEncoderDecoder implements MessageEncoderDecoder<Message> {

    private static final short ACK_OP_CODE = 12;
    private byte[] shortByte = new byte[2];
    private byte[] stringByte = new byte[1 << 10];

    private int shortByteLen = 0;
    private int stringByteLen = 0;

    private short opCode = -1;
    private short courseID = -1;

    private String username;
    private String password;


    @Override
    public Message decodeNextByte(byte nextByte) {
        Message output = null;
        if (opCode == -1) {
            opCode = pushShortByte(nextByte);
            if (opCode == 4 || opCode == 11) {
                output = popMessage();
            }
        }
        else if ((opCode >= 1) && (opCode <= 3)) {
            if (username == null) {
                username = pushStringByte(nextByte);
            }
            else if (password == null) {
                password = pushStringByte(nextByte);
            }
            if (username != null && password != null) {
                output = popMessage();
            }
        }
        else if (opCode == 5 || opCode == 6 || opCode == 7 || opCode == 9 || opCode == 10) {
            courseID = pushShortByte(nextByte);
            if (courseID != -1)
                output = popMessage();
            }
        else if (opCode == 8) {
            if (username == null)
                username = pushStringByte(nextByte);
            if (username != null)
                output = popMessage();
        }
        return output; //not a line yet
    }

    private Message popMessage() {
        Message output = new Message(opCode,username,password,courseID);
        clear();
        return output;
    }

    private void clear () {
        username = null;
        password = null;
        opCode = -1;
        courseID = -1;
    }

    @Override
    public byte[] encode(Message message) {
        byte[] opCodeBytes = new byte[2];
        opCodeBytes[0] = (byte)((message.getOpCode() >> 8) & 0xFF);
        opCodeBytes[1] = (byte)(message.getOpCode() & 0xFF);

        byte[] commandBytes = new byte[2];
        commandBytes[0] = (byte)((message.getCommand() >> 8) & 0xFF);
        commandBytes[1] = (byte)(message.getCommand() & 0xFF);


        boolean hasAdditionalMsg = (message.getAdditionalMsg() != null);
        byte[] additionalBytes = new byte[0];
        if (hasAdditionalMsg) {
            additionalBytes = (message.getAdditionalMsg()).getBytes();
        }

        boolean isACK = (message.getOpCode() == ACK_OP_CODE);
        byte[] output;
        if (isACK)
            output = new byte[opCodeBytes.length+commandBytes.length+additionalBytes.length+1];
        else // ERR Message
            output = new byte[opCodeBytes.length+commandBytes.length];

        int index = 0;
        for (byte opCodeByte : opCodeBytes) output[index++] = opCodeByte;
        for (byte commandByte : commandBytes) output[index++] = commandByte;

        if (isACK) {
            for (byte additionalByte : additionalBytes) output[index++] = additionalByte;
            output[output.length-1] = '\0';
        }
        return output;
    }

    private short pushShortByte(byte nextByte) {
        shortByte[shortByteLen++] = nextByte;
        if (shortByteLen == 2) {
            short result = (short)((shortByte[0] & 0xff) << 8);
            result += (short)(shortByte[1] & 0xff);
            shortByteLen = 0;
            return result;
        }
        return -1; // didnt finish to read the 2bytes for the short number
    }

    private String pushStringByte(byte nextByte) {
        if (nextByte == '\0') {
            String result = new String(stringByte, 0, stringByteLen, StandardCharsets.UTF_8);
            stringByteLen = 0;
            return result;
        }
        if (stringByteLen >= stringByte.length)
            stringByte = Arrays.copyOf(stringByte, stringByteLen * 2);
        stringByte[stringByteLen++] = nextByte;
        return null; // didnt finish to read the string
    }
}
