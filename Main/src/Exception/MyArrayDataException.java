package Exception;

public class MyArrayDataException extends Exception {
    public MyArrayDataException() {
    }

    public MyArrayDataException(String s) {
        super(s);
    }

    public MyArrayDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArrayDataException(Throwable cause) {
        super(cause);
    }
}
