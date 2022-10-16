package core;

import java.io.File;

public class FileReconstructor {

    public static void main(String[] args){
        checkFilePaths();
    }

    private static void checkFilePaths(){
        if(!new File(System.getProperty("PACKET_REPO_PATH")).isDirectory()){
            System.err.println("Packet Repository Path is not a directory, check environment variable value.");
            System.exit(1);
        }
    }

    private static void getReconstructedFilename(String[] args){
        char[] path = args[1].toCharArray();
        Character[] strippedPath = new Character[path.length - 2];
        System.arraycopy(path, 1, strippedPath, 0, path.length - 1);
    }
}
