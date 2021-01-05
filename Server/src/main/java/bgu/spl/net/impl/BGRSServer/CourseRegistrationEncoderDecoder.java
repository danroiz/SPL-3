package bgu.spl.net.impl.BGRSServer;
import bgu.spl.net.api.MessageEncoderDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class CourseRegistrationEncoderDecoder implements MessageEncoderDecoder<Message> {
    private static final short DEFAULT_NOT_ENCODED = -1;
    private static final short ACK_OP_CODE = 12;
    private final byte[] shortByte = new byte[2];
    private byte[] stringByte = new byte[1 << 10]; // 1KB

    private int shortByteLen = 0;
    private int stringByteLen = 0;

    private short opCode = DEFAULT_NOT_ENCODED; // default not encoded value
    private short courseID = DEFAULT_NOT_ENCODED; // default not encoded value

    private String username;
    private String password;


    @Override
    public Message decodeNextByte(byte nextByte) {
        Message output = null; // not a full message yet
        if (opCode == DEFAULT_NOT_ENCODED) {
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
            if (courseID != DEFAULT_NOT_ENCODED)
                output = popMessage();
            }
        else if (opCode == 8) {
            if (username == null)
                username = pushStringByte(nextByte);
            if (username != null)
                output = popMessage();
        }
        return output;
    }

    private Message popMessage() {
        Message output = new Message(opCode,username,password,courseID);
        clear();
        return output;
    }

    // reset the data fields to be ready for next message
    private void clear () {
        username = null;
        password = null;
        opCode = DEFAULT_NOT_ENCODED;
        courseID = DEFAULT_NOT_ENCODED;
    }

    @Override
    public byte[] encode(Message message) {
        // encode the opcode short and the command short
        byte[] opCodeBytes = new byte[2];
        byte[] commandBytes = new byte[2];
        shortToBytes(message.getOpCode(), opCodeBytes);
        shortToBytes(message.getCommand(), commandBytes);
        // encode additional message
        byte[] additionalBytes = new byte[0];
        if (message.getAdditionalMsg() != null) { // message has additional message to send
            additionalBytes = (message.getAdditionalMsg()).getBytes();
        }

        // append all the bytes arrays together
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

    private void shortToBytes(short num, byte[] shortBytes) {
        shortBytes[0] = (byte)((num >> 8) & 0xFF);
        shortBytes[1] = (byte)(num & 0xFF);
    }

    // returns a short after reading 2 bytes
    private short pushShortByte(byte nextByte) {
        shortByte[shortByteLen++] = nextByte;
        if (shortByteLen == 2) {
            short result = (short)((shortByte[0] & 0xff) << 8);
            result += (short)(shortByte[1] & 0xff);
            shortByteLen = 0; // reset
            return result;
        }
        return DEFAULT_NOT_ENCODED; // didn't finish to read the 2bytes for the short number
    }
    // read bytes until /0 appeared. return the string
    private String pushStringByte(byte nextByte) {
        if (nextByte == '\0') { // end of string
            String result = new String(stringByte, 0, stringByteLen, StandardCharsets.UTF_8);
            stringByteLen = 0; // reset
            return result;
        }
        if (stringByteLen >= stringByte.length) // size is not enough
            stringByte = Arrays.copyOf(stringByte, stringByteLen * 2);
        stringByte[stringByteLen++] = nextByte; // read next byte
        return null; // didnt finish to read the string
    }
}
