package seven.savewapper;
//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/!\
//		 .' @          /!!!\
//		 :         ,    !!!!
//		  `-..__.-' _.-\!!!/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑\\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

import org.apache.poi.ss.formula.functions.T;
import seven.ExcelSuperInterface;
import seven.callBack.CellStyleInterface;
import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;

import java.io.OutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public interface SaveExcel<T>  {

    /**
     * 请使用Flush
     *
     * @throws Exception
     */
    @Deprecated
    void  Save() throws Exception;

    /**
     * 对要包装的数据进行过滤，对应实体Bean\n
     * 如果返回false将放弃此条数据
     *
     * @param filter {@link DataFilterInterface}
     * @return
     */
    SaveExcel<T> Filter(DataFilterInterface<T> filter);

    /**
     * 此处传入每一行打包好的数据。对应一个实体\n
     * 在process方法里可对属性进行处理加工
     *
     * @param process {@link DataFilterProcessInterface}
     * @return
     */
    SaveExcel<T> Process(DataFilterProcessInterface<T> process);


    /**
     * 对结果的List进行排序
     *
     * @param c
     * @return
     */
    SaveExcel<T> Sort(Comparator<? super T> c);

    /**
     * 此处过滤Excel的列数据（列名）\n
     * 如果加入后，只保留对应数据
     *
     * @param consumer {@link DataFilterColumnInterface}
     */
    SaveExcel<T> FilterCol(Consumer<List<String>> consumer);

    /**
     * 此处过滤Excel的列数据（列名）\n
     * 如果加入后，删除这些列
     *
     * @param consumer {@link DataFilterColumnInterface}
     */
    SaveExcel<T> AnyCol(Consumer<List<String>> consumer);

    /**
     * 网页输出流
     *
     * @param stream
     * @return
     */
    SaveExcel<T> SetOutputStream(OutputStream stream) throws Exception;

    /**
     * @throws Exception
     */
    void Flush() throws Exception;

    /**
     * 设置保存路径
     *
     * @param path
     */
    SaveExcel<T> SetPath(String path);

    /**
     * 转换字段名称
     *
     * @param title
     * @param newTitle
     * @return
     */
    SaveExcel<T> ConvertName(String title, String newTitle);

    /**
     * 转换字段
     *
     * @param titleMapping
     * @return
     */
    SaveExcel<T> ConvertName(HashMap<String, String> titleMapping);

    /**
     * 转换字段
     *
     * @param titleMapping
     * @return
     */
    SaveExcel<T> ConvertName(HashMap<String, String> titleMapping, Boolean isInit);

    /**
     * 设置风格.必须保证wk不能为null。
     *
     * @param name
     * @param cellStyle
     * @return
     */
    SaveExcel<T> SetCellStyle(String name, CellStyleInterface cellStyle);
}
