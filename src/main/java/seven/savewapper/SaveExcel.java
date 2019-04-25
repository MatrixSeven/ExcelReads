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

import seven.ExcelSuperInterface;
import seven.callBack.CellStyleInterface;
import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;

import java.io.OutputStream;
import java.util.Comparator;
import java.util.HashMap;

/**
 * [Zhihu]https://www.zhihu.com/people/Sweets07
 * [Github]https://github.com/MatrixSeven
 * Created by seven on 2016/11/30.
 */
public interface SaveExcel extends ExcelSuperInterface {

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
    SaveExcel Filter(DataFilterInterface<?> filter);

    /**
     * 此处传入每一行打包好的数据。对应一个实体\n
     * 在process方法里可对属性进行处理加工
     *
     * @param process {@link DataFilterProcessInterface}
     * @return
     */
    SaveExcel Process(DataFilterProcessInterface<?> process);


    /**
     * 对结果的List进行排序
     *
     * @param c
     * @return
     */
    SaveExcel Sort(Comparator<? super Object> c);

    /**
     * 此处过滤Excel的列数据（列名）\n
     * 如果加入后，将不对实体进行赋值
     *
     * @param df {@link DataFilterColumnInterface}
     */
    SaveExcel FilterCol(DataFilterColumnInterface df);

    /**
     * 此处过滤Excel的列数据（列名）\n
     * 如果加入后，只要这些列
     *
     * @param df {@link DataFilterColumnInterface}
     */
    SaveExcel AnyCol(DataFilterColumnInterface df);

    /**
     * 网页输出流
     *
     * @param strea
     * @return
     */
    SaveExcel SetOutputStream(OutputStream stream) throws Exception;

    /**
     * @throws Exception
     */
    void Flush() throws Exception;

    /**
     * 设置保存路径
     *
     * @param path
     */
    SaveExcel SetPath(String path);

    /**
     * 转换字段名称
     *
     * @param title
     * @param new_title
     * @return
     */
    SaveExcel ConvertName(String title, String new_title);

    /**
     * 转换字段
     *
     * @param title_mapping
     * @return
     */
    SaveExcel ConvertName(HashMap<String, String> title_mapping);

    /**
     * 转换字段
     *
     * @param title_mapping
     * @return
     */
    SaveExcel ConvertName(HashMap<String, String> title_mapping, Boolean is_init);

    /**
     * 设置风格.必须保证wk不能为null。
     *
     * @param name
     * @param cellStyle
     * @return
     */
    SaveExcel SetCellStyle(String name, CellStyleInterface cellStyle);
}
