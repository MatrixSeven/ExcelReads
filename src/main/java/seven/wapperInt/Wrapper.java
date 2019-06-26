package seven.wapperInt;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import seven.ExcelSuperInterface;
import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.config.Config;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/#\
//		 .' @          /###\
//		 :         ,   #####
//		  `-..__.-' _.-\###/
//		        `;_:    `"'
//		      .'"""""`.
//		     /,  ya ,  \\
//		    //狗神保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

/**
 * @author Seven<p>
 * 2016年4月12日-下午4:08:08
 */
@SuppressWarnings("all")
public abstract class Wrapper<T> implements Serializable, ExcelSuperInterface {
    protected Config config = new Config();
    protected DecimalFormat df = new DecimalFormat("0");
    private static final String BLANK="";

    protected String getCellFormatValue(Cell cell) {
        String cellValue;
        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC:
                    cellValue = df.format(cell.getNumericCellValue());
                    break;
                case FORMULA: {
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = sdf.format(date);
                    } else {
                        cellValue = String.valueOf(cell.getStringCellValue());
                    }
                    break;
                }
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case ERROR:
                case BLANK:
                default:
                    cellValue = BLANK;
            }
        } else {
            cellValue = BLANK;
        }
        return cellValue;
    }

    public Wrapper(Consumer<Config> consumer) {
        consumer.accept(this.config);
    }


    /**
     * 生成数据包，返回打包好的数据
     *
     * @return List
     * @throws Exception
     */
    public abstract List<T> Create() throws Exception;


    public abstract List<Map<String, String>> CreateMap() throws Exception;

    public abstract Map<String, Map<String, String>> CreateMap(String key) throws Exception;


    /**
     * 对要包装的数据进行过滤，对应实体Bean\n
     * 如果返回false将放弃此条数据
     *
     * @param filter {@link DataFilterInterface}
     * @return Wrapper
     */
    public abstract Wrapper<T> Filter(DataFilterInterface<? extends T> filter);

    /**
     * 此处传入每一行打包好的数据。对应一个实体\n
     * 在process方法里可对属性进行处理加工
     *
     * @param process {@link DataFilterProcessInterface}
     * @return Wrapper
     */
    public abstract Wrapper<T> Process(DataFilterProcessInterface<? extends T> process);

    /**
     * 对结果的List进行排序
     *
     * @param c
     * @return Wrapper
     */
    public abstract Wrapper<T> Sort(Comparator<? extends T> c);

    /**
     * 此处过滤Excel的列数据（列名）\n
     * 如果加入后，将不对实体进行赋值
     *
     * @param consumer {@link DataFilterColumnInterface}
     */
    public abstract Wrapper<T> FilterCol(Consumer<List<String>> consumer);

}