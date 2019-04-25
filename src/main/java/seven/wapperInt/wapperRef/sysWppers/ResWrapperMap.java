package seven.wapperInt.wapperRef.sysWppers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import seven.util.ExcelTool;
import seven.util.RegHelper;
import seven.wapperInt.Config;
import seven.wapperInt.wapperRef.WrapperObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

//=======================================================
//		          .----.
//		       _.'__    `.
//		   .--(^)(^^)---/#\
//		 .' @          /###\
//		 :         ,   #####
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
 */
@SuppressWarnings({"resource", "deprecation", "unchecked"})
public class ResWrapperMap extends WrapperObj<Map> {

    public ResWrapperMap(Consumer<Config> consumer) {
        super(consumer);
    }
    @Override
    protected <T> T RefResWrapper(String fs, boolean isMap, String key) throws Exception {
        if (config == null) {
            throw new Exception("配置类为空");
        }
        HashMap<String,Map> maps=null;
        List<Map> list=null;
        if(isMap){
            maps=  new HashMap<String,Map>(config.getVocSize());
        }else {
            list = new ArrayList<Map>(config.getVocSize());
        }

        Map<String, String> map;
        String[] titles = null;
        Sheet sheet;
        Row row;
        String[] require = config.getRequire();
        Workbook hhf = ExcelTool.newInstance(fs,false);
        int start_sheet = config.getStartSheet();
        int end_sheet = start_sheet + 1;
        if (config.getIsLoopSheet()) {
            end_sheet = config.getEndSheet() == null ? hhf.getNumberOfSheets() : config.getEndSheet();
            if (end_sheet <= 0 || end_sheet > hhf.getNumberOfSheets()) {
                throw new Exception("sheet范围不正确");
            }
        }
        for (; start_sheet < end_sheet; start_sheet++) {

            sheet = hhf.getSheetAt(start_sheet);
            row = sheet.getRow(config.getTitleRow());
            titles = new String[row.getPhysicalNumberOfCells()];
            for (int i = 0, rows = row.getPhysicalNumberOfCells(); i < rows; i++) {
                titles[i] = getCellFormatValue(row.getCell((short) i));
            }
            try {
                if (row.getPhysicalNumberOfCells() == 0)
                    throw new Exception("列表头获取失败");
            } catch (Exception e) {
                if (config.getErrorLog())
                    System.err.println("列表头获取失败,sheet:" + start_sheet + " " + sheet.getSheetName());
                continue;
            }

            int start = config.getContentRowStart();
            for (int rowNum = sheet.getLastRowNum(); start < rowNum; start++) {
                row = sheet.getRow(start);
                String Map_key="";
                if (null != row) {
                    map = new HashMap<String, String>();
                    try {
                        if (titles.length < row.getPhysicalNumberOfCells()) {
                            throw new Exception("列表长度小于实际列长度");
                        }
                        if (require != null && (titles.length != require.length)) {
                            throw new Exception("验证规则长度不对");
                        }
                    } catch (Exception e) {
                        if (config.getErrorLog())
                            System.err.println(
                                    "列表长度小于实际列长度 sheet:" + start_sheet + " " + sheet.getSheetName() + "行:" + rowNum);
                        continue;
                    }
                    for (int j = 0, colNum = row.getPhysicalNumberOfCells(); j < colNum; j++) {
                        if (require != null && !(require[j].equals("Null")) && filterColByKey.contains(titles[j])) {
                            if (RegHelper.require(require[j], getCellFormatValue(row.getCell((short) j)))) {
                                map.put(titles[j], getCellFormatValue(row.getCell((short) j)));
                            } else {
                            }
                        } else {
                            map.put(titles[j], getCellFormatValue(row.getCell((short) j)));
                        }
                    }
                    if (!isNull(map))
                        if (!this.filter.filter(map)) {
                            continue;
                        }
                    this.process.process(map);
                    if(isMap){
                        maps.put(map.get(key),map);
                    }else {
                        list.add(map);
                    }

                }
            }
        }
        if (!isMap){
            if(c!=null) {
                list.sort(c);
            }
            return (T)list;
        }
        return (T) maps;
    }
}
