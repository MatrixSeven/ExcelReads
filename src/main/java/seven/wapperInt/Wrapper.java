package seven.wapperInt;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import seven.ExcelSuperInterface;
import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.callBack.imp.DefaultDataFilter;
import seven.callBack.imp.DefaultDataProFilter;
import seven.config.Config;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
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
public abstract class Wrapper implements Serializable {
    protected Config config = new Config();
    protected DecimalFormat df = new DecimalFormat("0");
    private static final String BLANK="";
    protected DataFilterInterface filter=new DefaultDataFilter();
    protected DataFilterProcessInterface process=new DefaultDataProFilter();
    protected List<String> filterColByKey =new ArrayList<>();
    protected List<String> filterColByValue =new ArrayList<>();
    protected Comparator<? super Object> c=null;
    protected String fs;
    protected File file;
    protected static final  boolean isMap=false;

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
    protected abstract <T> T refResWrapper(String fs, boolean isMap) throws Exception;
}