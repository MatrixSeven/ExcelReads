package seven.callBack.imp;

import seven.callBack.DataFilterInterface;

/**
 * [Github]https://github.com/MatrixSeven
 * [Bolg]https://matrixseven.github.io/
 * Created by seven on 2016/10/18.
 */
public class DefaultDataFilter implements DataFilterInterface<Object>{
    @Override
    public Boolean filter(Object o) {
        return true;
    }
}
