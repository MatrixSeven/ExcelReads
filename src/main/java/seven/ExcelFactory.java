package seven;

import org.apache.poi.ss.formula.functions.T;
import seven.callBack.PackageDataInterface;
import seven.savewapper.SaveExcel;
import seven.savewapper.wapperRef.sysWppers.ResExportDBMap;
import seven.savewapper.wapperRef.sysWppers.ResExportDBObj;
import seven.savewapper.wapperRef.sysWppers.ResExportMap;
import seven.savewapper.wapperRef.sysWppers.ResExportObj;
import seven.wapperInt.Wrapper;
import seven.wapperInt.wapperRef.WrapperObj;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/#\
//		 .' @          /###\
//		 :         ,   #####
//		  `-..__.-' _.-\###/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,\\
//		    //狗神保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================
/**
 * @author Seven<p>
 * @date 2016年6月4日-下午4:08:19
 */
@SuppressWarnings("unchecked")
public class ExcelFactory {

    private ExcelFactory() {
    }

    /**
     * 读取Excel
     * @param FilePath 路径
     * @param r        包装类
     * @return
     * @throws Exception
     */
    public static <T> Wrapper<T> getBeans(String FilePath, WrapperObj<T> r) throws Exception {
        return r.init(FilePath);
    }

    /**
     * 保存Excel
     *
     * @param bean
     * @param FilePath
     * @return
     * @throws Exception
     */
    public static  <T> SaveExcel<T> saveExcel(List<? extends T> bean, String FilePath) throws Exception {
        if (bean.size() < 1) {
            throw new Exception("请传入数据");
        }
        if (bean.get(0) instanceof Map) {
            return (SaveExcel<T>) new ResExportMap((List<Map>)bean, FilePath);
        }
        return new ResExportObj(bean, FilePath);
    }

    /**
     * 保存Excel
     *
     * @param bean
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(List<? extends Object> bean) throws Exception {
        if (bean.size() < 1) {
            throw new Exception("请传入数据");
        }
        if (bean.get(0) instanceof Map) {
            return new ResExportMap((List<Map>) bean);
        }
        return new ResExportObj((List) bean);
    }


    /**
     * 保存Excel
     *
     * @param resultSet
     * @param FilePath
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet, String FilePath) throws Exception {
        return new ResExportDBMap(resultSet, FilePath);
    }

    /**
     * 保存Excel
     *
     * @param resultSet
     * @param FilePath
     * @param packageDataInterface
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet, String FilePath, PackageDataInterface packageDataInterface) throws Exception {
        return new ResExportDBObj(resultSet, FilePath, packageDataInterface);
    }

    /**
     * 保存Excel
     *
     * @param resultSet
     * @param type
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet, Class type) throws Exception {
        return new ResExportDBObj(resultSet, type);
    }


    /**
     * 保存Excel
     *
     * @param resultSet
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet) throws Exception {
        return new ResExportDBMap(resultSet);
    }
}
