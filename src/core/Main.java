package core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

public class Main {
    /*
    * The main CLI endpoint which will be the user interface for the tool. Any methods that are ubiquitous across both the
    * functionalities will be added to this class, whereas any methods that are local to only one functionality should only
    * be added to the class containing that component's algorithm. The flags are GNU-style.
    * The options for utilising the tool are as such:
    *  - args[0] : Which component to start up. For file reconstructor, use '-r' or '-R', and for packet serializer, use
    *              '-s' or '-S'.
    *  - args[1] : If the serializer is being used, then the file path to the file to be parsed, enclosed in double quotes.
    *              If the reconstructor is being used, then the file name for the file to be reconstructed from the packets.
    *              Also enclosed in double quotes.
    *  - args[2] : ***Only needed if the packet serializer is being used.*** The size of the packets' payload.
    * */
    public static void main(String[] args){
        printPage("Introduction.txt");
        setupSystemProperties();
        try{
            validateOptions(args);
        } catch(IllegalArgumentException ia){
            System.out.println("The switch used does not exist.");
        }
    }

    /*
     * Sets up the system prerequisites for the application to function correctly. Although, if one does want to modify it
     * to provide support for systems such as linux and macOS, then please modify the following as necessary. In the future,
     * support for running on macOS and linux as binaries might be added.
     * Sets up the path for the packets from the file to be serialized as well as the path for the file to be reconstructed
     * from the packets directory.
     * */
    private static void setupSystemProperties(){
        final String PACKET_REPO_PATH = "c:\\Users\\" + System.getProperty("user.name") + "\\Documents\\Packets";
        final String RECONSTRUCTED_FILE_PATH = "c:\\Users\\" + System.getProperty("user.name") + "\\Documents\\ReconstructedFiles";
        try{
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "mkdir " + PACKET_REPO_PATH});
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "mkdir" + RECONSTRUCTED_FILE_PATH});
        } catch(IOException io){
            System.err.println(io.getMessage());
        }
        System.setProperty("PACKET_REPO_PATH", PACKET_REPO_PATH);
        System.setProperty("RECONSTRUCTED_FILE_PATH", RECONSTRUCTED_FILE_PATH);
    }

    private static void validateOptions(String[] args) throws IllegalArgumentException{
        if(args.length == 0){
            printPage("HelpPage.txt");
        } else if(args[0].equals("-r") || args[0].equals("-R")){
            //FileReconstructor.main(args);
            System.out.println("DEBUG: Reconstructor called.");
        } else if(args[0].equals("-s") || args[0].equals("-S")) {
            PacketMaker.main(args);
            System.out.println("DEBUG: Serializer called.");
        } else {
            throw new IllegalArgumentException("Invalid Switch.");
        }
    }

    private static void printPage(String pageName){
        pageName = "resources\\" + pageName;
        String path = Paths.get(pageName).toAbsolutePath().toString();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String content = reader.readLine();
            while(content != null){
                System.out.println(content);
                content = reader.readLine();
            }
        } catch (IOException io){
            System.err.println(io.getMessage());
        }
    }
}
