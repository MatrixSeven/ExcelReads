package seven.wapperInt.wapperRef.sysWppers;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import seven.util.ExcelTool;
import seven.util.RegHelper;
import seven.wapperInt.wapperRef.WrapperObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public abstract class ResWrapperMap extends WrapperObj<T> {

    @Override
    protected <T> T RefResWapper(String fs, boolean isMap, String key) throws Exception {
        if (config == null) {
            throw new Exception("配置类为空");
        }
        HashMap<String,Map> maps=null;
        List<Map> list=null;
        if(isMap){
            maps=  new HashMap<String,Map>(config.getVoc_size());
        }else {
            list = new ArrayList<Map>(config.getVoc_size());
        }

        Map<String, String> map;
        String[] titles = null;
        Sheet sheet;
        Row row;
        String _Require[] = config.getRequire();
        Workbook hhf = ExcelTool.newInstance(fs,false);
        int start_sheet = config.getStart_sheet();
        int end_sheet = start_sheet + 1;
        if (config.getIs_loop_sheet()) {
            end_sheet = config.getEnd_sheet() == null ? hhf.getNumberOfSheets() : config.getEnd_sheet();
            if (end_sheet <= 0 || end_sheet > hhf.getNumberOfSheets()) {
                throw new Exception("sheet范围不正确");
            }
        }
        for (; start_sheet < end_sheet; start_sheet++) {

            sheet = hhf.getSheetAt(start_sheet);
            row = sheet.getRow(config.getTitle_row());
            titles = new String[row.getPhysicalNumberOfCells()];
            for (int i = 0, rows = row.getPhysicalNumberOfCells(); i < rows; i++) {
                titles[i] = getCellFormatValue(row.getCell((short) i));
            }
            try {
                if (row.getPhysicalNumberOfCells() == 0)
                    throw new Exception("列表头获取失败");
            } catch (Exception e) {
                if (config.getError_log())
                    System.err.println("列表头获取失败,sheet:" + start_sheet + " " + sheet.getSheetName());
                continue;
            }

            int start = config.getContent_row_start();
            for (int rowNum = sheet.getLastRowNum(); start < rowNum; start++) {
                row = sheet.getRow(start);
                String Map_key="";
                if (null != row) {
                    map = new HashMap<String, String>();
                    try {
                        if (titles.length < row.getPhysicalNumberOfCells()) {
                            throw new Exception("列表长度小于实际列长度");
                        }
                        if (_Require != null && (titles.length != _Require.length)) {
                            throw new Exception("验证规则长度不对");
                        }
                    } catch (Exception e) {
                        if (config.getError_log())
                            System.err.println(
                                    "列表长度小于实际列长度 sheet:" + start_sheet + " " + sheet.getSheetName() + "行:" + rowNum);
                        continue;
                    }
                    for (int j = 0, colNum = row.getPhysicalNumberOfCells(); j < colNum; j++) {
                        if (_Require != null && !(_Require[j].equals("Null")) && filterColBy_key.contains(titles[j])) {
                            if (RegHelper.require(_Require[j], getCellFormatValue(row.getCell((short) j)))) {
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
