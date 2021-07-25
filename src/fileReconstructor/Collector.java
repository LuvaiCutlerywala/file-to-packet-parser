package fileReconstructor;

import entities.Packet;

import java.io.File;
import java.util.ArrayList;

public class Collector {

    private static final ArrayList<Packet> collectionQueue = new ArrayList<>();

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
            //TODO: Write a method in Deserializer class to deserialize the packet from file and add it to the queue in the Collector class.
        }
    }

}