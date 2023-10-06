 public class EmptyStringException extends Exception{
          @Override
    public String getMessage() {
        return super.getMessage();
    }

    public EmptyStringException(String message) {
        super(message);
    }
    }