package Exception;

public class MyArraySizeException extends IllegalArgumentException{

    public MyArraySizeException(String s) {
        super(s);
    }

    public MyArraySizeException() {
    }
}
