package seven;

import seven.callBack.PackageDataInterface;
import seven.savewapper.SaveExcel;
import seven.savewapper.wapperRef.sysWppers.ResExprotDBMap;
import seven.savewapper.wapperRef.sysWppers.ResExprotDBObj;
import seven.savewapper.wapperRef.sysWppers.ResExprotMap;
import seven.savewapper.wapperRef.sysWppers.ResExprotObj;
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
 * @date   2016年6月4日-下午4:08:19
 */
@SuppressWarnings("unchecked")
public class ExcelFactory {

	private ExcelFactory() {
	}

    /**
     * 读取Excel
     * @param FilePath  路径
     * @param r 包装类
     * @return
     * @throws Exception
     */
	public static Wrapper   getBeans(String FilePath, WrapperObj r) throws Exception {
		return (Wrapper)r.init(FilePath);
	}

    /**
     * 保存Excel
     * @param bean
     * @param FilePath
     * @return
     * @throws Exception
     */
	public static SaveExcel saveExcel(List<? extends Object> bean, String FilePath) throws Exception {
            if (bean.size() < 1) {
                throw new Exception("请传入数据");
            }
            if (bean.get(0) instanceof Map) {
               return new ResExprotMap((List<Map>) bean,FilePath);
            }
         return new ResExprotObj((List)bean, FilePath);
	}

    /**
     * 保存Excel
     * @param bean
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(List<? extends Object> bean) throws Exception {
        if (bean.size() < 1) {
            throw new Exception("请传入数据");
        }
        if (bean.get(0) instanceof Map) {
            return new ResExprotMap((List<Map>) bean);
        }
        return new ResExprotObj((List)bean);
    }


    /**
     * 保存Excel
     * @param resultSet
     * @param FilePath
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet, String FilePath) throws Exception {
            return new ResExprotDBMap(resultSet,FilePath);
    }

    /**
     * 保存Excel
     * @param resultSet
     * @param FilePath
     * @param packageDataInterface
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet, String FilePath, PackageDataInterface packageDataInterface) throws Exception {
        return new ResExprotDBObj(resultSet,FilePath,packageDataInterface);
    }

    /**
     * 保存Excel
     * @param resultSet
     * @param type
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet,Class type) throws Exception {
        return new ResExprotDBObj(resultSet,type);
    }


    /**
     * 保存Excel
     * @param resultSet
     * @return
     * @throws Exception
     */
    public static SaveExcel saveExcel(ResultSet resultSet) throws Exception {
        return new ResExprotDBMap(resultSet);
    }
}
