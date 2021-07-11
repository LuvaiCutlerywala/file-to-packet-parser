package fileParsers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Serializer {

    private static final ArrayList<Packet> packetList = new ArrayList<>();

    protected static void createPacket(int sequenceNumber, byte[] buffer){
        Packet packet = new Packet(sequenceNumber, buffer);
        packetList.add(packet);
    }

    //Debug method
    protected static void printPackets(){
        for(Packet packet: packetList){
            System.out.println("Sequence Number: " + packet.getSequenceNumber());
            System.out.println("Payload: " + packet.getPayload());
        }
    }

    protected static void serializePackets() throws IllegalPropertyException{
        try {
            for (int i = 0; i < packetList.size(); ++i) {
                Packet packet = packetList.get(i);
                File file = new File(System.getProperty("PACKET_REPO_PATH") + "\\packet" + i + ".packet");
                FileOutputStream outStream = new FileOutputStream(file);
                outStream.write((';' + packet.getSequenceNumber() + "; \n").getBytes());
                outStream.write((';' + packet.getUnixTimeStamp() + "; \n").getBytes());
                outStream.write(validateCheckSum(packet.getCheckSum()));
                outStream.write(packet.getPayload());
            }
        } catch(IOException io){
            System.err.println(io.getMessage());
        }
        System.out.println("Packets serialized");
    }

    private static byte[] validateCheckSum(String checkSum) throws IllegalPropertyException{
        byte[] byteArr = null;
        if(checkSum.equals("")){
            throw new IllegalPropertyException("CheckSum missing from packet.");
        } else {
            byteArr = (';' + checkSum + "; \n").getBytes();
        }
        return byteArr;
    }

}
