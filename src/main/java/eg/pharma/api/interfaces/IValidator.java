package eg.pharma.api.interfaces;

public interface IValidator<T> {
    void validate(T entity);
}
