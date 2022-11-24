package exception;

public class StateException extends Throwable {
        private final String message;

    public StateException(String message) {
        this.message = message;
    }

    public void message() {
        System.out.println("Такого поля в классе " + message + "  не существует");
    }

}
