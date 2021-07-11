package fileParsers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Packet {

    private long sequenceNumber;
    private byte[] payload;
    private long unixTimeStamp;
    private String checkSum;

    protected Packet(long sequenceNumber, byte[] payload){
        this.sequenceNumber = sequenceNumber;
        this.payload = payload;
        this.unixTimeStamp = System.currentTimeMillis() / 1000L;
        this.checkSum = generateCheckSum();
    }

    protected long getSequenceNumber() {
        return sequenceNumber;
    }

    protected void setSequenceNumber(long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    protected byte[] getPayload() {
        return payload;
    }

    protected void setPayload(byte[] payload) {
        this.payload = payload;
    }

    protected long getUnixTimeStamp() {
        return unixTimeStamp;
    }

    protected String getCheckSum() {
        return this.checkSum;
    }

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
