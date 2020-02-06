package seven.wapperInt.wapperRef.sysWppers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import seven.anno.ExcelAnno;
import seven.callBack.ConvertInterface;
import seven.config.Config;
import seven.handler.InPutHandler;
import seven.handler.HandlerFactory;
import seven.util.ExcelTool;
import seven.util.RegHelper;
import seven.wapperInt.wapperRef.WrapperObj;

import java.lang.reflect.*;
import java.util.*;
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

    private static <T, R> R Try(Class<T> clazz) {
        try {
            return (R) clazz.newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        logger.warn("数据转化注册失败,{},放弃处理", clazz);
        return null;
    }

    @Override
    protected <T> T refResWrapper(String fs, boolean isMap) throws Exception {
        config.check();
        HashMap<String, Field> FieldCaChe = new HashMap<>();
        List<T> data = null;
        Map<String, List<T>> map = new HashMap<>();
        Constructor[] constructors = type.getConstructors();
        Field[] F = type.getDeclaredFields();
        ExcelAnno Ex = null;
        String[] reg = new String[F.length];
        Arrays.fill(reg, "Null");
        int reg_index = 0;
        Field Map_key = null;
        data = new ArrayList<T>(config.getVocSize());
        Map<String, ConvertInterface> convertMap = new HashMap<>();
        for (Field f : F) {
            f.setAccessible(true);
            if ((Ex = f.getAnnotation(ExcelAnno.class)) != null && !Ex.Pass()) {
                reg[reg_index++] = Ex.Required();
                FieldCaChe.put(Ex.Value(), f);
                if (Ex.Convert() != null) {
                    convertMap.put(Ex.Value(), Try(Ex.Convert()));
                }
            } else {
                FieldCaChe.put(f.getName(), f);
            }
        }
        config.getConvertMap().forEach((k, v) -> {
            convertMap.put(k, Try(v));
        });
        config.getConvertMapImpl().forEach((k, v) -> {
            convertMap.put(k, v);
        });
        Constructor<?> declaredConstructor = type.getDeclaredConstructor();
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
        if (config.getIsLoopSheet()) {
            map = new HashMap<>();
            end_sheet = config.getEndSheet() == null ? hhf.getNumberOfSheets() : config.getEndSheet();
            if (end_sheet <= 0 || end_sheet > hhf.getNumberOfSheets()) {
                logger.error("sheet范围不正确");
                throw new Exception("sheet范围不正确");
            }
        }
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
            sheet = hhf.getSheetAt(start_sheet);
            int start = config.getContentRowStart();
            for (int rowNum = sheet.getLastRowNum(); start <= rowNum; start++) {
                row = sheet.getRow(start);
                if (null != row) {
                    o = (T) declaredConstructor.newInstance();
                    for (int j = 0, colNum = row.getPhysicalNumberOfCells(); j < colNum; j++) {
                        if (FieldCaChe.containsKey(titles[j]) && !this.filterColByKey.contains(titles[j])) {
                            ConvertInterface orDefault = convertMap.get(titles[j]);
                            Field field = FieldCaChe.get(titles[j]);
                            String cellFormatValue = getCellFormatValue(row.getCell((short) j));
                            InPutHandler handlerType = HandlerFactory.getInPutHandler(field.getType());
                            if (!reg[j].equals("Null")) {
                                if (RegHelper.require(reg[j], v = cellFormatValue)) {
                                    field.set(o, handlerType.Handler(v));
//                                    if (Objects.isNull(orDefault)) {
//                                        field.set(o, handlerType.Handler(v));
//                                    } else {
//                                        field.set(o, orDefault.convert(v));
//                                    };
                                } else {
                                    logger.warn("数据格  {} 式不符合规范---->行:{} 列:{}", titles[j], start, j);
                                }
                            } else {
                                field.set(o, handlerType.Handler(cellFormatValue));
//                                if (Objects.isNull(orDefault)) {
//                                    field.set(o, handlerType.Handler(cellFormatValue));
//                                } else {
//                                    field.set(o, orDefault.convert(cellFormatValue));
//                                };
                            }
                        }
                    }
                    if (!this.filter.test(o)) {
                        continue;
                    }
                    process.accept(o);
                    data.add((T) o);
                }
            }

            map.put(sheet.getSheetName(), data);
            data = new ArrayList<>();


        }

        if (c != null) {
            data.sort(c);
        }
        if (config.getIsLoopSheet()) {
            return (T) map;
        }
        return (T) data;


    }

    private Class type;

    public ResWrapperObj(Class clazz, Consumer<Config> consumer) {
        super(consumer);
        this.type = clazz;

    }

    //public ResWrapperObj(Consumer<Config> consumer) {
    //	super(consumer);
    //	Type sType = getClass().getGenericSuperclass();
    //	Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
    //	Class<T> mTClass = (Class<T>) (generics[0]);
    //	try {
    //		type = mTClass.newInstance();
    //	} catch (Exception e) {
    //		e.printStackTrace();
    //	}
    //}
}
