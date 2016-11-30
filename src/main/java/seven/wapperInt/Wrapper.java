package seven.wapperInt;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import seven.callBack.DataFilterColumnInterface;
import seven.callBack.DataFilterInterface;
import seven.callBack.DataFilterProcessInterface;
import seven.util.RegHelper;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
 * @date   2016年4月12日-下午4:08:08
 */
public abstract class Wrapper implements Serializable{
	protected Config config=new Config();
	protected DecimalFormat df = new DecimalFormat("0");
	protected String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				cellvalue = df.format(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA: {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {
					cellvalue = String.valueOf(cell.getStringCellValue());
				}
				break;
			}
			case Cell.CELL_TYPE_BLANK:
				cellvalue = " ";
				break;
			case Cell.CELL_TYPE_STRING:
				cellvalue = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_ERROR:
				cellvalue = " ";
				break;
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}
	protected abstract void LoadConfig(Config config);
	public Wrapper() {
		LoadConfig(config);
	}
	public static class Config {
		Integer title_row = null;
		Integer content_row_start = null;
		Integer content_row_end = null;
		Boolean is_loop_sheet = false;
		Boolean error_log = true;
		Integer Voc_size = 10000;
		String Require[] = null;
		Integer start_sheet = 0;
		Integer end_sheet = null;

		public Config() {
		}

		public String[] getRequire() {
			return Require;
		}

		/**
		 * Map数据个校验数组
		 * <p>
		 * 支持正则表达式
		 * 
		 * @see RegHelper
		 * @return
		 */
		public void setRequire(String[] require) {
			Require = require;
		}

		public Integer getVoc_size() {
			return Voc_size;
		}

		/**
		 * Excel数据容器<br>
		 * 当数据大于5w时，最好初始化为大于或者等于当前excel行数 最好设置大于或者
		 * 
		 * @param voc_size
		 */
		public void setVoc_size(Integer voc_size) {
			Voc_size = voc_size;
		}

		public Boolean getError_log() {
			return error_log;
		}

		public void setError_log(Boolean error_log) {
			this.error_log = error_log;
		}

		public Config(Integer title_row, Integer content_row_start, Integer content_row_end) {
			super();
			this.title_row = title_row;
			this.content_row_start = content_row_start;
			this.content_row_end = content_row_end;
		}

		/**
		 * 内容开始行号
		 * 
		 */
		public Integer getTitle_row() {
			return title_row;
		}

		/**
		 * 标题行号
		 * 
		 */
		public void setTitle_row(Integer title_row) {
			this.title_row = title_row;
		}

		/**
		 * 内容开始行号
		 * 
		 */
		public Integer getContent_row_start() {
			return content_row_start;
		}

		/**
		 * 内容开始行号
		 * 
		 * @param content_row_start
		 */
		public void setContent_row_start(Integer content_row_start) {
			this.content_row_start = content_row_start;
		}

		/**
		 * 内容结束行号
		 * @return
		 */
		public Integer getContent_row_end() {
			return content_row_end;
		}

		/**
		 * 内容结束行号
		 * 
		 */
		public void setContent_row_end(Integer content_row_end) {
			this.content_row_end = content_row_end;
		}

		public Boolean getIs_loop_sheet() {
			return is_loop_sheet;
		}

		public void setIs_loop_sheet(Boolean is_loop_sheet) {
			this.is_loop_sheet = is_loop_sheet;
		}

		public Integer getStart_sheet() {
			return start_sheet;
		}

		public void setStart_sheet(Integer start_sheet) {
			this.start_sheet = start_sheet;
		}

		public Integer getEnd_sheet() {
			return end_sheet;
		}

		public void setEnd_sheet(Integer end_sheet) {
			this.end_sheet = end_sheet;
		}

	}

	/**
	 * 生成数据包，返回打包好的数据
	 * @return
	 * @throws Exception
	 */
	public abstract <T>List<T> Create() throws Exception;



	public abstract <T> T CreateMap(String key) throws Exception;


	/**
	 * 对要包装的数据进行过滤，对应实体Bean\n
	 * 如果返回false将放弃此条数据
	 * @param filter {@link DataFilterInterface}
	 * @return
	 */
	public abstract Wrapper Filter(DataFilterInterface<?> filter);

	/**
	 *此处传入每一行打包好的数据。对应一个实体\n
	 * 在process方法里可对属性进行处理加工
	 * @param process {@link DataFilterProcessInterface}
	 * @return
	 */
	public abstract Wrapper Process(DataFilterProcessInterface<?> process);

	/**
	 *	对结果的List进行排序
	 * @param c
	 * @return
	 */
	public abstract Wrapper Sort(Comparator<?> c);

	/**
	 * 此处过滤Excel的列数据（列名）\n
	 * 如果加入后，将不对实体进行赋值
	 * @param df {@link DataFilterColumnInterface}
	 */
	public abstract Wrapper FilterCol(DataFilterColumnInterface df);


//	public abstract Wrapper AsString(Comparator<? super T> c);
}
