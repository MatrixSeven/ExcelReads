package seven.callBack;

/**
 * [Github]https://github.com/MatrixSeven
 * [Bolg]https://matrixseven.github.io/
 * Created by seven on 2016/10/19.
 * *{@link seven.savewapper.SaveExcel} -->AnyCol和FilterCol
 */
@FunctionalInterface
public interface DataFilterColumnInterface {
    /**
     *{@link seven.savewapper.SaveExcel} -->AnyCol和FilterCol
     */
    public String[] filter();
}
