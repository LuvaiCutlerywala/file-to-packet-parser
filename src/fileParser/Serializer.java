package fileParser;

import entities.Packet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


/*
* The class is built as a separate stage in the conversion pipeline, and is created specifically to provide the separation
* from the parsing stage. Please do not merge the stages to keep their errors separate for debugging purposes. The algorithm
* is a very basic one, at least in case of the serialization method. Yet, this is code meant for demonstration purposes, and
* by extension does have to have the best algorithm. Although, if you do have a better algorithm, please do open a PR and
* let me know. I am interested in making this better in the future.
* */
public class Serializer {

    /*
    * Packets are serialized after they are parsed, this is to prevent the carrying of errors from parsing into the
    * serialization process for debugging processes.
    * */
    private static final ArrayList<Packet> packetList = new ArrayList<>();

    //Creates a packet with the given attributes and adds it to the collection.
    protected static void createPacket(int sequenceNumber, byte[] buffer){
        Packet packet = new Packet(sequenceNumber, buffer);
        packetList.add(packet);
    }

    /*
    * This method essentially only exists for outputting the parsed packets to the console for debugging purposes.
    * It does not actually contribute anything to the conversion pipeline, so if there's need to remove it for aiding the
    * reduction of program runtime, then please do so.
    * Another thing to note is that the implicit toString() call on the byte array returned by the packet.getPayload() is
    * supposed to be the reason why it outputs the bytes as raw, or else the express Arrays.toString() call leaves the
    * array as an array of integers.
    * */
    protected static void printPackets(){
        for(Packet packet: packetList){
            System.out.println("Sequence Number: " + packet.getSequenceNumber());
            System.out.println("Payload: " + packet.getPayload());
        }
    }

    /*
    * Serializes the packets from the collection, and the main property of this procedure is that supposed to be completely
    * isolated from the parsing process. So, please do not, in any kind of process, use the function as part of the parsing
    * process, as serialization is supposed to be a separate stage in the conversion pipeline.
    * Also, the order of the outStream.write() procedures is of careful order as they are representative of the
    * specifications for the .packet file format. For more information, see the Packet-File-Format.md doc in the resources
    * folder.
    * */
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

    /*
    * CheckSum validation is one of the most important parts, as it makes sure that the packet is serialized in a valid
    * state, where its validity can be verified during the reconstruction of the original file. Work will hopefully be done
    * in the future to improve the validity of this checksum.
    * The checking algorithm only requires to check for an empty string as the generation algorithm makes sure that in case
    * of failure to generate a proper checksum, it will most definitely return an empty string.
    * */
    private static byte[] validateCheckSum(String checkSum) throws IllegalPropertyException{
        byte[] byteArr;
        if(checkSum.isEmpty()){
            throw new IllegalPropertyException("CheckSum missing from packet.");
        } else {
            byteArr = (';' + checkSum + "; \n").getBytes();
        }
        return byteArr;
    }

}
