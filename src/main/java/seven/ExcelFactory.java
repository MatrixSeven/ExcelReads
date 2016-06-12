package seven;

import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import seven.wapperInt.wapperRef.WapperMap;
import seven.wapperInt.wapperRef.WapperObj;

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
 * @date   2016年6月4日-下午4:08:19
 */
public class ExcelFactory {

	private ExcelFactory() {
	}

	public static List<Map<String, String>> getBeans(String FilePath, WapperMap r) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(FilePath));
		return r.RefResWapper(fs);
	}
	public static <T> List<T> getBeans(String FilePath,WapperObj<T> r) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(FilePath));
		return r.RefResWapper(fs);
	}

}
