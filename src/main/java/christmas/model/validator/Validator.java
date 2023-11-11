package christmas.model.validator;

public interface Validator<T> {
    T validate(String input);
}
