package seven.wapperInt.callBack;

/**
 * [Github]https://github.com/MatrixSeven
 * [Bolg]https://matrixseven.github.io/
 * Created by seven on 2016/10/18.
 * 对要包装的数据进行过滤，对应实体Bean，如果返回false将放弃此条数据
 * T为实体Bean类型
 */
@FunctionalInterface
public interface DataFilterInterface<T> {
    /**
     * 对要包装的数据进行过滤，对应实体Bean\n
     * 如果返回false将放弃此条数据
     * @param t 实体Bean类型
     * @return
     */
    Boolean filter(T t);
}
