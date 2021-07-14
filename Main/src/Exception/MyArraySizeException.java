package Exception;

public class MyArraySizeException extends Exception {

    public MyArraySizeException() {
    }

    public MyArraySizeException(String s) {
        super(s);
    }

    public MyArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArraySizeException(Throwable cause) {
        super(cause);
    }
}
