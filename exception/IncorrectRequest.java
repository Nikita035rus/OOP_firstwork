package Interview.oop.exception;

public class IncorrectRequest extends RuntimeException {
    public IncorrectRequest(String massage) {
        super(massage, null, false, false);
    }
}
