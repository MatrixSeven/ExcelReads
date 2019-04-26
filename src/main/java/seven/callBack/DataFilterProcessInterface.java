package seven.callBack;


import java.util.function.Consumer;

/**
 * [Github]https://github.com/MatrixSeven
 * [Bolg]https://matrixseven.github.io/
 * Created by seven on 2016/10/18.
 * 此处传入每一行打包好的数据。对应一个实体
 * T为实体Bean类型
 */
@FunctionalInterface
public interface DataFilterProcessInterface<T> extends Consumer<T> {

}
