package core;

import fileParser.FileParser;
import fileParser.IllegalPropertyException;
import fileParser.Serializer;

/*
* This class is meant to be the main run point for the file to packet conversion pipeline. It really only should any methods
* for setup and shutdown of the process, and of course the main conversion pipeline. Nothing more should be added to this
* class for code splitting's sake.
* */
public class PacketMaker {

    /*
    * This is the base algorithm, and to note this is the conversion pipeline. So, if you do have anything to improve,
    * please do open a PR and definitely let me know. The application is supposed to be a CLI tool, and the arguments for
    * using it is are as follows:
    *  - args[0] : The path to the target file to be parsed. (Make sure file path has no spaces).
    *  - args[1] : The packet size. (As an integer).
    * Support for multi-file parsing might be added in the future.
    * */
    public static void main(String[] args){
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



}
