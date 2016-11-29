package seven.wapperInt.callBack;

/**
 * [Github]https://github.com/MatrixSeven
 * [Bolg]https://matrixseven.github.io/
 * Created by seven on 2016/10/19.
 * 此处过滤Excel的列数据（列名）
 * 如果加入后，将不对实体进行赋值
 */
public interface DataFilterColumnInterface {
    /**
     * 此处过滤Excel的列数据（列名）
     * 如果加入后，将不对实体进行赋值
     * @return
     */
    public String[] filter();
}
