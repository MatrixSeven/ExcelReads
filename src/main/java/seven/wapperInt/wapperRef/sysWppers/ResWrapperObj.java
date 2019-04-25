package seven.wapperInt.wapperRef.sysWppers;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import seven.anno.ExcelAnno;
import seven.util.ExcelTool;
import seven.util.RegHelper;
import seven.wapperInt.Config;
import seven.wapperInt.wapperRef.WrapperObj;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
public abstract class ResWrapperObj<T> extends WrapperObj<T> {

    @Override
	protected <T> T RefResWrapper(String fs, boolean isMap, String key) throws Exception {
		HashMap<String, Method> MeThodCaChe = new HashMap<String, Method>();
		HashMap<String,T> maps=null;
		List<T> list=null;

		Class<? extends Object> clazz = type.getClass();
		Field[] F = clazz.getDeclaredFields();
		ExcelAnno Ex = null;
		String[] reg = new String[F.length];
		Arrays.fill(reg, "Null");
		int reg_index = 0;
		Field Map_key=null;
		if(isMap){
			maps=  new HashMap<String,T>(config.getVocSize());
			Map_key=clazz.getDeclaredField(key);
			Map_key.setAccessible(true);
		}else {
			list = new ArrayList<T>(config.getVocSize());
		}
		for (Field f : F) {
			if ((Ex = f.getAnnotation(ExcelAnno.class)) != null&&!Ex.Pass()) {
				reg[reg_index++] = Ex.Required();
				MeThodCaChe.put(Ex.Value(),
						clazz.getMethod("set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1),
								new Class[] { String.class }));
			} else {
				MeThodCaChe.put(f.getName(),
						clazz.getMethod("set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1),
								new Class[] { String.class }));
			}
		}
		String[] titles;
		Sheet sheet;
		Row row;
		T o;
		String v;
		int start_sheet = config.getStartSheet();
		Workbook hhf =  ExcelTool.newInstance(fs,false);;
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
				throw new Exception("sheet范围不正确");
			}
		}
		for (; start_sheet < end_sheet; start_sheet++) {

			int start = config.getContentRowStart();
			for (int rowNum = sheet.getLastRowNum(); start < rowNum; start++) {
				row = sheet.getRow(start);
				if (null != row) {
					o = (T) type.getClass().newInstance();
					for (int j = 0, colNum = row.getPhysicalNumberOfCells(); j < colNum; j++) {
						if (MeThodCaChe.containsKey(titles[j])&&!this.filterColByKey.contains(titles[j])) {
							if (!reg[j].equals("Null")) {
								if (RegHelper.require(reg[j], v = getCellFormatValue(row.getCell((short) j)))) {
									MeThodCaChe.get(titles[j]).invoke(o,titles[j],v);
								} else {
//									System.err.println("数据格 " + titles[j] + " 式不符合规范---->行:" + start + " 列" + j);
								}
							} else {
								MeThodCaChe.get(titles[j]).invoke(o,getCellFormatValue(row.getCell((short) j)));
							}
						}
					}
					if(!this.filter.filter(o)){
						continue;
					}
					process.process(o);
					if(isMap){
						maps.put(Map_key.get(o).toString(),o);
					}else {
						list.add((T)o);
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

    private T type;

	public ResWrapperObj(Consumer<Config> consumer) {
		super(consumer);
		Type sType = getClass().getGenericSuperclass();
		Type[] generics = ((ParameterizedType) sType).getActualTypeArguments();
		Class<T> mTClass = (Class<T>) (generics[0]);
		try {
			type = mTClass.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
