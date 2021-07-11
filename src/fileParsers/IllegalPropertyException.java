package fileParsers;

/*
* This class is built specially for the properties of a packet being missing. The implementation of IllegalArgumentException
* can be used, but the original intention is for the code to be self-documenting, which requires the names of the classes to
* be as accurate as possible as well. This code is meant to be used as an example for understanding the parsing process that
* the files on a network undergo before transmission.
* */
public class IllegalPropertyException extends Exception {

    //Internal message, as there is no need for anything else.
    private final String message;

    //Created for debugging purposes.
    public IllegalPropertyException(){
        this.message = "Reason unknown";
    }

    public IllegalPropertyException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
