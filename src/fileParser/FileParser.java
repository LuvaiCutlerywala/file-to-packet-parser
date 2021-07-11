package fileParser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

/*
* The main class in this package, responsible for the parsing stage of the file to packet conversion pipeline.
* The entire point of this project is supposed to illustrate the parsing of files into packets before transmission to target
* computers and then their reconstruction there. To note, this is written with quite a basic algorithm and is supposed to be
* beginner friendly. But, if you do have some better algorithm, submit a PR and then we can see about merging the algorithms.
* */
public class FileParser {

    private int payloadSize;
    private File file;

    //For future use.
    public FileParser(Path path, int payloadSize) throws IllegalArgumentException{
        loadFile(path.toFile());
        this.payloadSize = payloadSize;
    }

    //For future use.
    public FileParser(File file, int payloadSize) throws IllegalArgumentException{
        loadFile(file);
        this.payloadSize = payloadSize;
    }

    public FileParser(String filename, int payloadSize) throws IllegalArgumentException{
        loadFile(new File(filename));
        this.payloadSize = payloadSize;
    }

    //For tests.
    public FileParser(){}

    //For changing the packet size during program execution.
    public int getPayloadSize(){
        return this.payloadSize;
    }

    public void setPayloadSize(int payloadSize){
        this.payloadSize = payloadSize;
    }

    /*
    * The main parsing algorithm. It really is supposed to be basic, so any improvements are welcome. The process, as
    * aforementioned, is quite simple, as it simply fills a buffer with the required data, then sends that data and the
    * sequence number of the packet to a procedure that produces a packet and links it to the serialization queue. Then,
    * clears the buffer and repeats. The space complexity used to be O(packetCount), yet now at the cost of execution time,
    * that has been solved.
    * */
    public void parseFile(){
        byte[] buffer = new byte[this.payloadSize];
       try{
           FileInputStream inpStream = new FileInputStream(file.toString());
           int dataRead = 0;
           int sequenceNumber = 0;
           do{
               dataRead = inpStream.read(buffer);
               Serializer.createPacket(sequenceNumber, buffer);
               Arrays.fill(buffer, (byte) 0);
               sequenceNumber++;
           } while(dataRead != -1);
       } catch(IOException io){
           System.err.println(io.getMessage());
       }
        System.out.println("DEBUG: Packets parsed.");
    }

    //Runs a simple check to ensure that the target file exists.
    public void loadFile(File file) {
        if(file.exists()){
            this.file = file;
        } else {
            throw new IllegalArgumentException("File: " + file.toString() + " does not exist.");
        }
    }
}
