package fileParsers;

import java.io.IOException;

public class PacketMaker {

    public static void main(String[] args){
        setupSystemProperties();
        //TODO: Finish the core packet parsing and serialization algorithm.
        FileParser parser = new FileParser("c:\\Users\\luvai_kcrxbon\\Pictures\\Memes\\R.I.P Spongebob.jpg", 512);
        parser.parseFile();
        Serializer.printPackets();
        try{
            Serializer.serializePackets();
        } catch(IllegalPropertyException ip){
            System.err.println(ip.getMessage());
            System.out.println("Exiting serialization process.");
            System.exit(1);
        }
    }

    private static void setupSystemProperties(){
        String packetRepoPath = "c:\\Users\\luvai_kcrxbon\\Documents\\Packets";
        try{
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "mkdir " + packetRepoPath});
        } catch(IOException io){
            System.err.println(io.getMessage());
        }
        System.setProperty("PACKET_REPO_PATH", packetRepoPath);
        System.out.println("DEBUG: System set up.");
    }

}
