package fileReconstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Deserializer {

    private static final byte[] delimiter = ";".getBytes();
    private static final byte[] lineBreak = "\n".getBytes();

    public static void deserializePackets(File packetFile){
        try(FileInputStream inpStream = new FileInputStream(packetFile)){

        } catch(IOException io){
            System.err.println(io.getMessage());
        }
    }

}
