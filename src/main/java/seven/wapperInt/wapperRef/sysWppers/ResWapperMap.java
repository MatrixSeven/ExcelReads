package seven.wapperInt.wapperRef.sysWppers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import seven.wapperInt.anno.RegHelper;
import seven.wapperInt.wapperRef.WapperMap;

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
 * @date   2016年4月12日-下午4:07:04
 */
public abstract class ResWapperMap extends WapperMap {

	@Override
	@SuppressWarnings({ "resource", "deprecation" })
	public List<Map<String, String>> RefResWapper(POIFSFileSystem fs) throws Exception {
		if (config == null) {
			throw new Exception("配置类为空");
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>(config.getVoc_size());
		Map<String, String> map;
		String[] titles = null;
		HSSFSheet sheet;
		HSSFRow row;
		String _Require[] = config.getRequire();
		HSSFWorkbook hhf = new HSSFWorkbook(fs);
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
						if (_Require != null && !(_Require[j].equals("Null"))) {
							if (RegHelper.require(_Require[j], getCellFormatValue(row.getCell((short) j)))) {
								map.put(titles[j], getCellFormatValue(row.getCell((short) j)));
							} else {
							}
						} else {
							map.put(titles[j], getCellFormatValue(row.getCell((short) j)));
						}
					}
					if (!isNull(map))
						list.add(map);
				}
			}
		}
		return list;
	}

}
