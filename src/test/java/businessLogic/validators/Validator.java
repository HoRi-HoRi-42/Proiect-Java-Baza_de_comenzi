package businessLogic.validators;

import model.Produs;

public interface Validator<T> {

    public void validate(T t);
}
