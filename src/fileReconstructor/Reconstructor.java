package fileReconstructor;

import entities.Packet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Reconstructor {

    private File file;

    public Reconstructor(File file){
        this.file = new File(System.getProperty("RECONSTRUCTED_FILE_PATH") + "\\" + file.toString());
    }

    public Reconstructor(String filename){
        this.file = new File(System.getProperty("RECONSTRUCTED_FILE_PATH") + "\\" +filename);
    }

    public void setFilename(File file){
        this.file = new File(System.getProperty("RECONSTRUCTED_FILE_PATH") + "\\" + file.toString());
    }

    public File getFilename(){
        return file;
    }

    public static boolean validatePackets(Packet[] packets){
        for(Packet packet: packets){
            if(!verifyUnixTimeStamp(packet.getUnixTimeStamp()) && !verifyCheckSum(packet)){
                return false;
            }
        }
        return true;
    }

    private static boolean verifyUnixTimeStamp(long unixTimeStamp){
        return unixTimeStamp > 0 && unixTimeStamp < (System.currentTimeMillis() / 1000L);
    }

    private static boolean verifyCheckSum(Packet packet){
        String checkSum = packet.getCheckSum();
        String hashedTimeStamp = packet.generateCheckSum();
        return checkSum.equals(hashedTimeStamp);
    }

    public void reconstructFile(Packet[] packets){
        byte[][] fileData = extractFileData(packets);
        try(FileOutputStream outStream = new FileOutputStream(this.file)){
            for(byte[] data: fileData){
                outStream.write(data);
            }
        } catch(IOException io){
            System.err.println(io.getMessage());
        }
    }

    private static byte[][] extractFileData(Packet[] packets){
        byte[][] fileData = new byte[packets.length][packets[0].getPayloadSize()];
        for(int i = 0; i < packets.length; i++){
            fileData[i] = packets[i].getPayload();
        }
        return fileData;
    }

}
