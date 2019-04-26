package seven.wapperInt.wapperRef.sysWppers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seven.anno.ExcelAnno;
import seven.util.ExcelTool;
import seven.util.RegHelper;
import seven.config.Config;
import seven.wapperInt.wapperRef.WrapperObj;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
//		     /,  ya ,\\
//		    //狗神保佑  \\
//		    `-._______.-'
//		    ___`. | .'___
//		   (______|______)
//=======================================================

/**
 * @author Seven<p>
 * 2016年6月4日-下午4:07:14
 */
@SuppressWarnings({"all"})
public class ResWrapperObj<T> extends WrapperObj<T> {
    private static final Logger logger = LoggerFactory.getLogger(ResWrapperObj.class);

    @Override
    protected <T> T RefResWrapper(String fs, boolean isMap, String key) throws Exception {
        config.check();
        HashMap<String, Method> MeThodCaChe = new HashMap<>();
        HashMap<String, T> maps = null;
        List<T> list = null;

        Class<? extends Object> clazz = type;
        Constructor[] constructors = clazz.getConstructors();
        Field[] F = clazz.getDeclaredFields();
        ExcelAnno Ex = null;
        String[] reg = new String[F.length];
        Arrays.fill(reg, "Null");
        int reg_index = 0;
        Field Map_key = null;
        if (isMap) {
            maps = new HashMap<String, T>(config.getVocSize());
            Map_key = clazz.getDeclaredField(key);
            Map_key.setAccessible(true);
        } else {
            list = new ArrayList<T>(config.getVocSize());
        }
        for (Field f : F) {
            Method method = clazz.getMethod("set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1),
                                            new Class[]{String.class});
            method.setAccessible(true);
            if ((Ex = f.getAnnotation(ExcelAnno.class)) != null && !Ex.Pass()) {
                reg[reg_index++] = Ex.Required();
                MeThodCaChe.put(Ex.Value(),method);
            } else {
                MeThodCaChe.put(f.getName(), method);
            }
        }
        Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        String[] titles;
        Sheet sheet;
        Row row;
        T o;
        String v;
        int start_sheet = config.getStartSheet();
        Workbook hhf = ExcelTool.newInstance(fs, false);
        ;
        int end_sheet = start_sheet + 1;
        sheet = hhf.getSheetAt(start_sheet);
        row = sheet.getRow(config.getTitleRow());
        titles = new String[row.getPhysicalNumberOfCells()];
        for (int i = 0, rows = row.getPhysicalNumberOfCells(); i < rows; i++) {
            titles[i] = getCellFormatValue(row.getCell((short) i));
        }

        if (config.getIsLoopSheet()) {
            end_sheet = config.getEndSheet() == null ? hhf.getNumberOfSheets() : config.getEndSheet();
            if (end_sheet <= 0 || end_sheet > hhf.getNumberOfSheets()) {
                logger.error("sheet范围不正确,sheet range:{}", config.getEndSheet());
                throw new Exception("sheet范围不正确");
            }
        }
        for (; start_sheet < end_sheet; start_sheet++) {
            int start = config.getContentRowStart();
            for (int rowNum = sheet.getLastRowNum(); start < rowNum; start++) {
                row = sheet.getRow(start);
                if (null != row) {
                    o = (T) declaredConstructor.newInstance();
                    for (int j = 0, colNum = row.getPhysicalNumberOfCells(); j < colNum; j++) {
                        if (MeThodCaChe.containsKey(titles[j]) && !this.filterColByKey.contains(titles[j])) {
                            if (!reg[j].equals("Null")) {
                                if (RegHelper.require(reg[j], v = getCellFormatValue(row.getCell((short) j)))) {
                                    MeThodCaChe.get(titles[j]).invoke(o, titles[j], v);
                                } else {
                                    logger.warn("数据格  {} 式不符合规范---->行:{} 列:{}", titles[j], start, j);
                                }
                            } else {
                                MeThodCaChe.get(titles[j]).invoke(o, getCellFormatValue(row.getCell((short) j)));
                            }
                        }
                    }
                    if (this.filter.test(o)) {
                        continue;
                    }
                    process.accept(o);
                    if (isMap) {
                        maps.put(Map_key.get(o).toString(), o);
                    } else {
                        list.add((T) o);
                    }
                }
            }
        }
        if (!isMap) {
            if (c != null) {
                list.sort(c);
            }
            return (T) list;
        }
        return (T) maps;
    }

    private Class type;

    public ResWrapperObj(Consumer<Config> consumer, Class<?> t) {
        super(consumer);
        type = t;
    }

//	public ResWrapperObj(Consumer<Config> consumer) {
//		super(consumer);
//		Type sType = getClass().getGenericSuperclass();
//		Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
//		Class<T> mTClass = (Class<T>) (generics[0]);
//		try {
//			type = mTClass.newInstance();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
