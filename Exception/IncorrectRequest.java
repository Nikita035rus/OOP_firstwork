package Interview.OOP.Exception;

public class IncorrectRequest extends RuntimeException {
    public IncorrectRequest(String massage) {
        super(massage, null, false, false);
    }
}
