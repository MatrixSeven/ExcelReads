package seven.wapperInt.callBack;

/**
 * [Github]https://github.com/MatrixSeven
 * [Bolg]https://matrixseven.github.io/
 * Created by seven on 2016/10/18.
 */
@FunctionalInterface
public interface DataFilterInterface<T> {
    Boolean filter(T t);
}
