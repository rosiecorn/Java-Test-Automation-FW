package exception;

public class CustomException extends RuntimeException {

     final String message;

    public CustomException(String message)
    {
        this.message =message;
    }

    public String toString(String message)
    {
        return "Exception reason: [" + message + "]";
    }
}

