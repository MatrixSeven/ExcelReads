package seven;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import seven.wapperInt.Wrapper;
import seven.wapperInt.wapperRef.WrapperObj;

import java.io.FileInputStream;


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
@SuppressWarnings("unchecked")
public class ExcelFactory {

	private ExcelFactory() {
	}

	public static Wrapper getBeans(String FilePath, WrapperObj r) throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(FilePath));
		return r.init(fs);
	}

}
