package entities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
* Packet POJO entity. Makes the process of dealing with packets easier, as attributes such as the checksum and timestamp can
* be generated and managed without requiring other classes to provide for the packets.
* */
public class Packet {

    private long sequenceNumber;
    private byte[] payload;
    private long unixTimeStamp;
    private String checkSum;
    private long payloadSize;

    public Packet(long sequenceNumber, byte[] payload, long payloadSize){
        this.sequenceNumber = sequenceNumber;
        this.payload = payload;
        this.unixTimeStamp = System.currentTimeMillis() / 1000L;
        this.checkSum = generateCheckSum();
        this.payloadSize = payloadSize;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public long getUnixTimeStamp() {
        return unixTimeStamp;
    }

    public String getCheckSum() {
        return this.checkSum;
    }

    public long getPayloadSize(){
        return payloadSize;
    }

    public void setPayloadSize(long payloadSize){
        this.payloadSize = payloadSize;
    }

    /*
    * Uses basic checksum generation algorithm based on the SHA256 hashing algorithm. The message for hash is timestamp as
    * it more convenient to have the same checksum for all packets produced at certain time. The convenience shows in the
    * validation process.
    * */
    private String generateCheckSum(){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA256");
            byte[] digestedHash = md.digest(String.valueOf(unixTimeStamp).getBytes());
            BigInteger integer = new BigInteger(1, digestedHash);
            StringBuilder builder = new StringBuilder(integer.toString());
            while(builder.length() < 32){
                builder.append("0");
            }
            return builder.toString();
        } catch(NoSuchAlgorithmException nsa){
            System.err.println(nsa.getMessage());
        }
        return "";
    }
}
