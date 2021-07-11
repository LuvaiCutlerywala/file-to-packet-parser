package fileParsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class FileParser {

    private int payloadSize;
    private File file;
    private FileInputStream inpStream;

    public FileParser(Path path, int payloadSize) throws IllegalArgumentException{
        loadFile(path.toFile());
        this.payloadSize = payloadSize;
    }

    public FileParser(File file, int payload) throws IllegalArgumentException{
        loadFile(file);
        this.payloadSize = payloadSize;
    }

    public FileParser(String filename, int payloadSize) throws IllegalArgumentException{
        loadFile(new File(filename));
        this.payloadSize = payloadSize;
    }

    public FileParser(){}

    public int getpayloadSize(){
        return this.payloadSize;
    }

    public void setpayloadSize(int payloadSize){
        this.payloadSize = payloadSize;
    }

    //TODO: Test the file parsing algorithm.

    public void parseFile(){
        byte[] buffer = new byte[this.payloadSize];
       try{
           inpStream = new FileInputStream(file.toString());
           int dataRead = 0;
           int sequenceNumber = 0;
           do{
               dataRead = inpStream.read(buffer);
               Serializer.createPacket(sequenceNumber, buffer);
               buffer = new byte[this.payloadSize];
               sequenceNumber++;
           } while(dataRead != -1);
       } catch(IOException io){
           System.err.println(io.getMessage());
       }
        System.out.println("DEBUG: Packets parsed.");
    }

    public void loadFile(File file) {
        if(file.exists()){
            this.file = file;
        } else {
            throw new IllegalArgumentException("File: " + file.toString() + " does not exist.");
        }
    }
}
