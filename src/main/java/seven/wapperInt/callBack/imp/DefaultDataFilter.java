package seven.wapperInt.callBack.imp;

import seven.wapperInt.callBack.DataFilterInterface;

/**
 * [Github]https://github.com/MatrixSeven
 * [Bolg]https://matrixseven.github.io/
 * Created by seven on 2016/10/18.
 */
public class DefaultDataFilter<T> implements DataFilterInterface<T>{
    @Override
    public Boolean filter(T o) {
        return true;
    }
}
