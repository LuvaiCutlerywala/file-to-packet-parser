package fileParsers;

public class IllegalPropertyException extends Exception {

    private final String message;

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
