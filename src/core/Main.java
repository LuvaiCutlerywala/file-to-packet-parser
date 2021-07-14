package core;

import fileParser.FileParser;
import fileParser.IllegalPropertyException;
import fileParser.Serializer;

import java.io.IOException;

/*
* This class is meant to be the main run point for the file to packet conversion pipeline. It really only should any methods
* for setup and shutdown of the process, and of course the main conversion pipeline. Nothing more should be added to this
* class for code splitting's sake.
* */
public class Main {

    /*
    * This is the base algorithm, and to note this is the conversion pipeline. So, if you do have anything to improve,
    * please do open a PR and definitely let me know. The application is supposed to be a CLI tool, and the arguments for
    * using it is are as follows:
    *  - args[0] : The path to the target file to be parsed. (Make sure file path has no spaces).
    *  - args[1] : The packet size. (As an integer).
    * Support for multi-file parsing might be added in the future.
    * */
    public static void main(String[] args){
        setupSystemProperties();
        FileParser parser = new FileParser(args[0], Integer.parseInt(args[1]));
        parser.parseFile();
        try{
            Serializer.serializePackets();
        } catch(IllegalPropertyException ip){
            System.err.println(ip.getMessage());
            System.out.println("Exiting serialization process.");
            System.exit(1);
        }
        Serializer.printPackets();
    }

    /*
    * Sets up the system prerequisites for the application to function correctly. Although, if one does want to modify it to
    * provide support for systems such as linux and macOS, then please modify the following as necessary. In the future,
    * support for running on macOS and linux as binaries might be added.
    * Only sets up the destination path for the packets to be serialized and makes sure that the directory exists.
    * */
    private static void setupSystemProperties(){
        String packetRepoPath = "c:\\Users\\" + System.getProperty("user.name") + "\\Documents\\Packets";
        try{
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "mkdir " + packetRepoPath});
        } catch(IOException io){
            System.err.println(io.getMessage());
        }
        System.setProperty("PACKET_REPO_PATH", packetRepoPath);
        System.out.println("DEBUG: System set up.");
    }

}
