package fileReconstructor;

import java.nio.ByteBuffer;

public class Decoder {

    public static int decodePayloadSize(byte[] data){
        byte[] payloadSize = new byte[4];
        System.arraycopy(data, 1, payloadSize, 0, 4);
        ByteBuffer buffer = ByteBuffer.wrap(payloadSize);
        return buffer.getInt();
    }

    public static long decodeSequenceNumber(byte[] data){
        byte[] sequenceNumber = new byte[8];
        System.arraycopy(data, 9, sequenceNumber, 0, 8);
        ByteBuffer buffer = ByteBuffer.wrap(sequenceNumber);
        return buffer.getLong();
    }

    public static long decodeTimestamp(byte[] data){
        byte[] timestamp = new byte[8];
        System.arraycopy(data, 21, timestamp, 0, 8);
        ByteBuffer buffer = ByteBuffer.wrap(timestamp);
        return buffer.getLong();
    }

    public static String decodeCheckSum(byte[] data){
        byte[] checkSum = new byte[32];
        System.arraycopy(data, 33, checkSum, 0, 32);
        return new String(checkSum);
    }

}
