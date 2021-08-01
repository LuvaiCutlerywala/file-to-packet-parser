package fileReconstructor;

import entities.Packet;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Collector {

    private static final HashMap<Long, Packet> collectionQueue = new HashMap<>();

    public static void collectFiles() throws NullPointerException{
        File packetRepo = new File(System.getProperty("PACKET_REPO_PATH"));
        if(!packetRepo.isDirectory()){
            System.err.println("Packet Repository Path is not a directory, check environment variable value.");
            System.exit(1);
        }
        File[] packetFiles = packetRepo.listFiles();
        if(packetFiles == null){
            throw new NullPointerException("No packets present in packet directory.");
        }
        for(File packetFile: packetFiles){
            Deserializer.deserializePackets(packetFile);
        }
    }

    public static void collectPacket(Packet packet){
        collectionQueue.put(packet.getSequenceNumber(), packet);
    }

    public static Packet[] getOrderedPackets(){
        Set<Long> keys = collectionQueue.keySet();
        Long[] packetSequence = keys.toArray(new Long[0]);
        Arrays.sort(packetSequence);
        Packet[] packets = new Packet[keys.size()];
        for(int i = 0; i < packets.length; ++i){
            packets[i] = collectionQueue.get(packetSequence[i]);
        }
        return packets;
    }

}
