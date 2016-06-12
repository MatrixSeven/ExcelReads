package seven.wapperInt.wapperRef.sysWppers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import seven.wapperInt.anno.ExcelAnno;
import seven.wapperInt.anno.RegHelper;
import seven.wapperInt.wapperRef.WapperObj;
import java.lang.reflect.ParameterizedType;

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
 * @author Seven
 *         <p>
 * @date 2016年6月4日-下午4:07:14
 */
public abstract class ResWapperObj<T> extends WapperObj<T> {

	@Override
	@SuppressWarnings({ "resource", "deprecation", "unchecked" })
	public List<T> RefResWapper(POIFSFileSystem fs) throws Exception {
		List<T> list = new ArrayList<T>(config.getVoc_size());
		HashMap<String, Method> MeThodCaChe = new HashMap<String, Method>();
		Class<? extends Object> clazz = type.getClass();
		Field[] F = clazz.getDeclaredFields();
		ExcelAnno Ex = null;
		String reg[] = new String[F.length];
		Arrays.fill(reg, "Null");
		int reg_index = 0;
		for (Field f : F) {
			if ((Ex = f.getAnnotation(ExcelAnno.class)) != null) {
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
		HSSFSheet sheet;
		HSSFRow row;
		T o;
		String v;
		int start_sheet = config.getStart_sheet();
		HSSFWorkbook hhf = new HSSFWorkbook(fs);
		int end_sheet = start_sheet + 1;
		sheet = hhf.getSheetAt(start_sheet);
		row = sheet.getRow(config.getTitle_row());
		titles = new String[row.getPhysicalNumberOfCells()];
		for (int i = 0, rows = row.getPhysicalNumberOfCells(); i < rows; i++) {
			titles[i] = getCellFormatValue(row.getCell((short) i));
		}

		if (config.getIs_loop_sheet()) {
			end_sheet = config.getEnd_sheet() == null ? hhf.getNumberOfSheets() : config.getEnd_sheet();
			if (end_sheet <= 0 || end_sheet > hhf.getNumberOfSheets()) {
				throw new Exception("sheet范围不正确");
			}
		}
		for (; start_sheet < end_sheet; start_sheet++) {
			int start = config.getContent_row_start();
			sheet = hhf.getSheetAt(start_sheet);
			for (int rowNum = sheet.getLastRowNum(); start < rowNum; start++) {
				row = sheet.getRow(start);
				if (null != row) {
					o = (T) type.getClass().newInstance();
					for (int j = 0, colNum = row.getPhysicalNumberOfCells(); j < colNum; j++) {
						if (MeThodCaChe.containsKey(titles[j])) {
							if (!reg[j].equals("Null")) {
								if (RegHelper.require(reg[j], v = getCellFormatValue(row.getCell((short) j)))) {
									MeThodCaChe.get(titles[j]).invoke(o, v);
								} else {
									// System.err.println("数据格 " + titles[j] + "
									// 式不符合规范---->行:" + start + " 列" + j);
								}
							} else {
								MeThodCaChe.get(titles[j]).invoke(o, getCellFormatValue(row.getCell((short) j)));
							}
						}
					}
					list.add(o);
				}
			}
		}
		return list;
	}

	private T type;

	@SuppressWarnings("unchecked")
	public ResWapperObj() {
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
